package cc.chauncy.foc.JLA;

import java.util.Vector;

/**
 * 关键字
 * Created by 13969 on 2017/5/17.
 */
public class Keywords {
    /** 关键字 */
    private Vector<Token> keywords = new Vector<>();

    public Keywords() {
        for(Token t:Token.values()) {
            if(t.sortCode <= 30) {
                keywords.add(t);
            }
        }
    }

    /**
     * 根据字符串判断Token类型
     * @param name 要判断的字符串
     * @return token类型
     */
    public static Token key(String name) {
        Token token;
        try{
            token = Token.valueOf(name.toUpperCase());
        }catch (Exception e) {
            token = Token.IDENT;
        }
        return token;
    }
}
