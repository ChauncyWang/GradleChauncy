package cc.chauncy.foc;

import cc.chauncy.foc.entity.Grammar;

/**
 *
 * Created by 13969 on 2017/5/17.
 */
public class Main {
    public static void main(String[] args) {
        String s = "S -> A\nA -> 10";
        Grammar grammar = new Grammar('S',s);
    }
}
