package cc.chauncy.foc.JLA;

import java.util.LinkedList;
import java.util.List;

/**
 * 主要的扫描器
 * Created by 13969 on 2017/5/15.
 */
public class Scanner {
    /** 所有字符都缓存在这里 */
    private char[] buf;
    /** 当前字符 */
    private char ch;
    /** 当前字符所在位置 */
    private int bp =  -1;
    /** 上一个Token的结束位置 */
    private int preEndPos = -1;
    /** 当前扫描到的位置 */
    private int pos = -1;
    /** 当前Token结束位置 */
    private int endPos;
    /** 出错的位置 */
    private int errPos = -1;
    /** 当前的 Token */
    private Token token = null;
    /** 为了扫描到单词所设的缓冲区 */
    private char[] sbuf = new char[128];
    /** 当前字符 所在缓冲区的位置(该单词的第几个字符) */
    private int sp;
    /** 缓冲区的字符组成的字符串 */
    private String name;
    /** 是否为注释 */
    private boolean isNotes = false;
    /** 是否为字符串 */
    private boolean isString = false;
    /** 当前行号 */
    private int low = 1;
    /** 当前列号 */
    private int col = 1;
    /** 当前符号 在 符号表的 下标 */
    private int tableIndex;
    /**  符号表 */
    private SymbolTable symbolTable = new SymbolTable();
    private List<String> errors = new LinkedList<String>();


    /**
     * 构造函数
     * @param buf 要解析的字符串
     */
    public Scanner(char[] buf) {
        this.buf = buf;
        scanChar();
    }

    /** 扫描下一个字符 */
    private void scanChar() {
        if(bp == buf.length-1) {
            ch = '\0';
        } else {
            ch = buf[++bp];
            col++;
            // 行 增加
            if(ch == '\n') {
                low++;
                col = 1;
            }
        }
    }

    /**
     * 扫描 下一个 token
     */
    public void nextToken() {
        preEndPos = endPos;
        sp = 0;

        while (true) {
            pos = bp;
            switch (ch) {
                //空白字符
                case ' ':
                case '\t':
                    do {
                        scanChar();
                    }while (ch == ' ' || ch == '\t');
                    endPos = bp;
                    break;
                case '\n':
                    scanChar();
                    endPos = bp;
                    break;
                case '\r':
                    scanChar();
                    if (ch == '\n') {
                        scanChar();
                    }
                    endPos = bp;
                    break;
                //标识符或是关键字的开头
                case 'A': case 'B': case 'C': case 'D': case 'E':
                case 'F': case 'G': case 'H': case 'I': case 'J':
                case 'K': case 'L': case 'M': case 'N': case 'O':
                case 'P': case 'Q': case 'R': case 'S': case 'T':
                case 'U': case 'V': case 'W': case 'X': case 'Y':
                case 'Z':
                case 'a': case 'b': case 'c': case 'd': case 'e':
                case 'f': case 'g': case 'h': case 'i': case 'j':
                case 'k': case 'l': case 'm': case 'n': case 'o':
                case 'p': case 'q': case 'r': case 's': case 't':
                case 'u': case 'v': case 'w': case 'x': case 'y':
                case 'z':
                    //扫描标识符或是关键字
                    scanIdent();
                    return;
                //数字的开头
                case '1':case '2':case '3':case '4':case '5':
                case '6':case '7':case '8':case '9':
                    //扫描数字
                    scanNum();
                    return;
                //各种界符
                case '+':scanChar();token = Token.PLUS;return;
                case '-':scanChar();token = Token.SUB;return;
                case '=':scanChar();token = Token.EQ;return;
                case '(':scanChar();token = Token.LPAREN;return;
                case ')':scanChar();token = Token.RBRACKET;return;
                case '[':scanChar();token = Token.LBRACKET;return;
                case ']':scanChar();token = Token.RBRACKET;return;
                case ';':scanChar();token = Token.SEMI;return;
                case ',':scanChar();token = Token.COMMA;return;
                //需要扫描第二个字符
                case '*':
                    scanChar();
                    if(ch == '/'){
                        // */
                        token = Token.RNOTE;
                        scanChar();
                    }else {
                        // *
                        token = Token.STAR;
                    }
                    return;
                case '/':
                    scanChar();
                    if(ch == '*') {
                        // /*
                        // token = Token.LNOTE;
                        // 记住注释 左边的开始
                        int t = col;
                        isNotes = true;
                        while (ch != '\r' && ch != '\n' && bp < buf.length) {
                            scanChar();
                            if (ch == '*') {
                                scanChar();
                                if (ch == '/') {
                                    isNotes = false;
                                    break;
                                }
                            }
                        }
                        if(isNotes) {
                            logErr(low,t,"注释右边没有闭合");
                        }
                        scanChar();
                        break;
                    }else {
                        token = Token.SLASH;
                    }
                    return;
                case '>':
                    scanChar();
                    if(ch == '='){
                        // >=
                        token = Token.GTEQ;
                        scanChar();
                    } else {
                        // >
                        token = Token.GT;
                    }
                    return;
                case '<':
                    scanChar();
                    if(ch == '='){
                        // <=
                        token = Token.LTEQ;
                        scanChar();
                    }else if(ch == '>'){
                        // <>
                        token = Token.NEQ;
                        scanChar();
                    }else {
                        // <
                        token = Token.LT;
                    }
                    return;
                case '.':
                    scanChar();
                    if(ch == '.'){
                        // ..
                        token = Token.DDOT;
                        scanChar();
                    }else {
                        token = Token.DOT;
                    }
                    return;
                case ':':
                    scanChar();
                    if(ch == '='){
                        // :=
                        token = Token.ASS;
                        scanChar();
                    }else {
                        token = Token.COLON;
                    }
                    return;
                case '"':
                    // 字符串开始
                    scanChar();
                    //记住字符串开始的位置
                    int t = col;
                    while (ch != '\"' && ch != '\r' && ch != '\n' && bp < buf.length) {
                        putChar(ch);
                        scanChar();
                    }
                    if (ch != '\"') {
                        logErr(low,t,"字符串右边没有匹配引号");
                    }
                    name = new String(sbuf,0,sp);
                    token = Token.STRINGCONSTANT;
                    tableIndex = symbolTable.add(name,false);
                    scanChar();
                    return;
                case '\0':token = Token.EOF;return;
                default:
                    logErr(low,col,"非法字符");
                    scanChar();
                    break;
            }
        }
    }

    /**
     * 读取数字常量
     */
    private void scanNum() {
        do {
            putChar(ch);
            scanChar();
            switch (ch) {
                case '0':case '1':case '2':case '3':case '4':
                case '5':case '6':case '7':case '8':case '9':
                    break;
                case 'A': case 'B': case 'C': case 'D': case 'E':
                case 'F': case 'G': case 'H': case 'I': case 'J':
                case 'K': case 'L': case 'M': case 'N': case 'O':
                case 'P': case 'Q': case 'R': case 'S': case 'T':
                case 'U': case 'V': case 'W': case 'X': case 'Y':
                case 'Z':
                case 'a': case 'b': case 'c': case 'd': case 'e':
                case 'f': case 'g': case 'h': case 'i': case 'j':
                case 'k': case 'l': case 'm': case 'n': case 'o':
                case 'p': case 'q': case 'r': case 's': case 't':
                case 'u': case 'v': case 'w': case 'x': case 'y':
                case 'z':
                    logErr(low,col,"标识符错误");
                default:
                    name = new String(sbuf,0,sp);
                    token = Token.INTCONSTANT;
                    tableIndex = symbolTable.add(name,true);
                    return;
            }
        }while (true);
    }

    /**
     * 读取一个标识符，可能是关键字
     */
    private void scanIdent() {
        do {
            putChar(ch);
            scanChar();
            switch (ch) {
                case 'A': case 'B': case 'C': case 'D': case 'E':
                case 'F': case 'G': case 'H': case 'I': case 'J':
                case 'K': case 'L': case 'M': case 'N': case 'O':
                case 'P': case 'Q': case 'R': case 'S': case 'T':
                case 'U': case 'V': case 'W': case 'X': case 'Y':
                case 'Z':
                case 'a': case 'b': case 'c': case 'd': case 'e':
                case 'f': case 'g': case 'h': case 'i': case 'j':
                case 'k': case 'l': case 'm': case 'n': case 'o':
                case 'p': case 'q': case 'r': case 's': case 't':
                case 'u': case 'v': case 'w': case 'x': case 'y':
                case 'z':
                case '0':case '1':case '2':case '3':case '4':
                case '5':case '6':case '7':case '8':case '9':
                    break;
                default:
                    name = new String(sbuf,0,sp);
                    token = Keywords.key(name);
                    if(token == Token.IDENT)
                        tableIndex = symbolTable.add(name);
                    return;
            }
        }while (true);
    }


    /**
     * 向sbuf中添加一个字符
     * @param ch 要添加的字符
     */
    private void putChar(char ch) {
        //如果缓冲区的空间用完，双倍扩大缓冲区大小
        if(sp == sbuf.length) {
            char[] newbuf = new char[sbuf.length * 2];
            System.arraycopy(sbuf,0,newbuf,0,sbuf.length);
            sbuf = newbuf;
        }
        sbuf[sp++] = ch;
    }

    /**
     * 打印错误
     * @param str 错误信息
     */
    private void logErr(int low,int col,String str) {
        errors.add(String.format("ERROR(%d,%d):%s",low,col, str));
    }

    // getter
    public int getErrPos() {
        return errPos;
    }

    public Token getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public int getPreEndPos() {
        return preEndPos;
    }

    public int getPos() {
        return pos;
    }

    public int getEndPos() {
        return endPos;
    }

    public int getLow() {
        return low;
    }

    public int getCol() {
        return col;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<String> getErrors() {
        return errors;
    }
}
