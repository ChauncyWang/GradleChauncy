package cc.chauncy.foc.entity;

import java.util.Vector;

/**
 * 文法
 * Created by 13969 on 2017/5/17.
 */
public class Grammar {
    public Grammar(String str) {
        // 为两个集合和文法规则集合申请空间
        this.Vn = new Vector<>();
        this.Vt = new Vector<>();
        this.P = new Vector<>();
        //将句子拆分 暂时保留在 vnt和vtt中
        String[] strs = str.split("\r\n");
        Vector<Character> vnt = new Vector<>();
        Vector<char[][]> vtt = new Vector<>();
        int len = strs.length;
        for(int i = 0;i < len;++i) {
            String[] pt = strs[i].split("\\W->\\W");
            vnt.add(pt[0].charAt(0));
            //是否存在或
            String[] ors = pt[1].split("\\|");
            char[][] t = new char[ors.length][];
            //对每个单位的或进行
            for(int j = 0;j < ors.length;++j){
                t[j] = ors[j].toCharArray();
            }
            vtt.add(t);
        }

        //文法开始的 非终结符
        S = new Symbol(vnt.get(0));
        S.setVn(true);

        // 将所有非终结符添加到Vn集合中
        for(Character ch:vnt) {
            Symbol symbol = new Symbol(ch);
            symbol.setVn(true);
            if(Vn.indexOf(symbol)==-1)
                Vn.add(symbol);
        }
        // 将终结符添加到Vt中并生成文法
        for(int i = 0;i < len;++i) {
            char[][] chs = vtt.get(i);
            // 文法左侧为非终结符
            Symbol left = new Symbol(vnt.get(i));
            left.setVn(true);
            // 为文法产生式申请空间
            Symbol[][] production = new Symbol[chs.length][];
            // 便利产生式
            for(int j = 0;j < chs.length;++j) {
                char[] ch = chs[j];
                production[j] = new Symbol[ch.length];
                for(int k = 0;k < ch.length;++k){
                    char c = ch[k];
                    production[j][k] = new Symbol(c);
                    //如果当前符号为非终结符
                    if(Vn.indexOf(production[j][k]) != -1) {
                        production[j][k].setVn(true);
                    }else {
                        // 当前符号位终结符,添加到终结符集合中
                        if(Vt.indexOf(production[j][k])==-1)
                            Vt.add(production[j][k]);
                    }
                }
            }
            this.P.add(new GrammaticalRule(left,production));
        }
    }
    /** 文法开始的 非终结符 */
    private Symbol S;
    /** 终结符集合 */
    private Vector<Symbol> Vn;
    /** 非终结符集合 */
    private Vector<Symbol> Vt;
    /** 文法规则的 集合 */
    private Vector<GrammaticalRule> P;

    @Override
    public String toString() {
        return "Grammar{" +
                "S=" + S +
                ", Vn=" + Vn +
                ", Vt=" + Vt +
                ", P=" + P +
                '}';
    }
}
