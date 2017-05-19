package cc.chauncy.foc.entity;

/**
 * 文法
 * Created by 13969 on 2017/5/17.
 */
public class Grammar {
    public Grammar(char s,String p) {
        String[] ps = p.split("\n");
        String[] pss = ps[0].split("\\W->\\W");
        for(String str : pss) {
            System.out.println(str);
        }
    }
    private char S;
}
