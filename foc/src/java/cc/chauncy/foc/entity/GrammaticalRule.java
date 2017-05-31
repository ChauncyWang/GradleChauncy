package cc.chauncy.foc.entity;


/**
 * 文法规则
 * Created by 13969 on 2017/5/22.
 */
public class GrammaticalRule {
    /** 左侧的 非终结符 */
    private Symbol vn;
    /** 右侧的 产生式 */
    private Symbol[][] production;

    /**
     * 构造函数
     * @param S 非终结符
     * @param production 产生式
     */
    public GrammaticalRule(Symbol S,Symbol[][] production) {
        this.vn = S;
        this.production = production;
    }

    public Symbol getVn() {
        return vn;
    }

    public Symbol[][] getProduction() {
        return production;
    }

    @Override
    public String toString() {
        String str = vn + "->";
        for (Symbol[] ss:production) {
            for(Symbol s:ss) {
                str+=s;
            }
            str+="|";
        }
        return str.substring(0,str.length()-1);
    }
}
