package cc.chauncy.foc;

import cc.chauncy.foc.entity.Grammar;
import cc.chauncy.hoi4.GroovyIOTools;

import java.io.File;

/**
 *
 * Created by 13969 on 2017/5/17.
 */
public class Main {
    public static void main(String[] args) {
        String s = (String) GroovyIOTools.readAll("foc/src/res/test.txt");
        System.out.println(s);
        Grammar grammar = new Grammar(s);
        System.out.print(grammar);
    }
}
