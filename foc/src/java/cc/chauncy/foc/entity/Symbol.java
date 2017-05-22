package cc.chauncy.foc.entity;

/**
 * 符号，每一个终结符 非终结符 都是 一个 符号
 * Created by 13969 on 2017/5/22.
 */
public class Symbol {
    /** 符号 */
    private Character symbol;
    /** 是否为非终结符 */
    private boolean isVn;

    /**
     * 标准的构造函数
     * @param value 符号
     */
    public Symbol(Character value) {
        this.symbol = value;
        this.isVn = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        return symbol.equals(symbol1.symbol);
    }

    @Override
    public String toString() {
        return ""+symbol;
    }

    /// getter and setter
    public Character getSymbol() {
        return symbol;
    }

    public boolean isVn() {
        return isVn;
    }

    public void setVn(boolean vn) {
        isVn = vn;
    }
}
