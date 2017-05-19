package cc.chauncy.foc.JLA;

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
    private int bp;
    /** 上一个Token的结束位置 */
    private int preEndPos;
    /** 当前扫描到的位置 */
    private int pos;
    /** 当前Token结束位置 */
    private int endPos;
    /** 出错的位置 */
    private int errPos;
    /** 当前的 Token */
    private Token token;
    /** 为了扫描到单词所设的缓冲区 */
    private char[] sbuf = new char[128];
    /** 当前字符 所在缓冲区的位置(该单词的第几个字符) */
    private int sp;
    /** 缓冲区的字符组成的字符串 */
    private String name;

    /**
     * 构造函数
     * @param buf 要解析的字符串
     */
    public Scanner(char[] buf) {
        this.buf = buf;
        preEndPos = -1;
        pos = -1;
        endPos = -1;
        errPos = -1;
        token = null;
        sp = -1;
        bp = -1;
    }

    /** 扫描下一个字符 */
    private void scanChar() {
        ch = buf[++bp];
    }

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
                    scanIdent();
                    break;
                case '1':case '2':case '3':case '4':case '5':
                case '6':case '7':case '8':case '9':
                    //数字
                    return;
                case '+':scanChar();token = Token.PLUS;return;
                case '-':scanChar();token = Token.SUB;return;
                case '=':scanChar();token = Token.EQ;return;
                case '(':scanChar();token = Token.LPAREN;return;
                case ')':scanChar();token = Token.RBRACKET;return;
                case '[':scanChar();token = Token.LBRACKET;return;
                case ']':scanChar();token = Token.RBRACKET;return;
                case ':':scanChar();token = Token.COLON;return;
                case ',':scanChar();token = Token.COMMA;return;
                //需要扫描第二个字符
                case '*':scanChar();token = Token.STAR;return;
                case '/':scanChar();token = Token.SLASH;return;
                case '>':scanChar();token = Token.GT;return;
                case '<':scanChar();token = Token.LT;return;
                case '.':scanChar();token = Token.DOT;return;
                case ';':scanChar();token = Token.SEMI;return;
                default:
                    scanChar();
                    return;
            }
        }
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
}
