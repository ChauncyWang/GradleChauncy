package cc.chauncy.foc.JLA;

public enum Token {
    //文件结束
    EOF(0),
    //保留字
    AND("and",1),
    ARRAY("array",2),
    BEGIN("begin",3),
    CALL("call",4),
    CASE("case",5),
    CHAR("char",6),
    CONSTANT("constant",7),
    DO("do",8),
    ELSE("else",9),
    END("end",10),
    FOR("for",11),
    IF("if",12),
    INPUT("input",13),
    INTEGER("integer",14),
    NOT("not",15),
    OF("of",16),
    OR("or",17),
    OUTPUT("output",18),
    PROCEDURE("procedure",19),
    PROGRAM("program",20),
    READ("read",21),
    REAL("real",22),
    REPEAT("repeat",23),
    SET("set",24),
    THEN("then",25),
    TO("to",26),
    UNTIL("until",27),
    VAR("var",28),
    WHILE("while",29),
    WRITE("write",30),
    //标识符
    IDENT(34),
	// 常量
    INTCONSTANT(36),
    STRINGCONSTANT(38),
	// 单界符
    PLUS("+",39),
    SUB("-",40),
    STAR("*",41),
    SLASH("/",42),
    EQ("=",43),
    GT(">",44),
    LT("<",45),
    LPAREN("(",46),
    RPAREN(")",47),
    LBRACKET("[",48),
    RBRACKET("]",49),
    COLON(":",50),
    DOT(".",51),
    SEMI(";",52),
    COMMA(",",53),
    // 双界符
    NEQ("<>",54),
    LTEQ("<=",55),
    GTEQ(">=",56),
    ASS(":=",57),
    DDOT("..",58),
    LNOTE("/*",59),
    RNOTE("*/",60);
	//构造
    Token(int sortCode) {
        this(null,sortCode);
    }
    Token(String name,int sortCode) {
        this.name = name;
        this.sortCode = sortCode;
    }

    public final String name;
	public final int sortCode;
}