package cc.chauncy.foc.JLA;

import java.util.LinkedList;
import java.util.List;

/**
 * 符号表
 * Created by 13969 on 2017/5/21.
 */
public class SymbolTable {
    /**
     * 符号表每一项节点
     */
    public static class Node {
        public enum Type{
            N("N"),//number
            C("C");//character
            Type(String str){
                this.str = str;
            }
            public String str;
        }
        public Integer pos;
        public Integer len;
        public String name;
        public Type type;
        public String val;
        public int index;

        @Override
        public String toString() {
            return  "("+pos +
                    ", " + len +
                    ", " + name +
                    ", " + type +
                    ", " + val +
                    ", " + index +
                    ")\n";
        }
    }
    private String chars = "";
    private List<Node> nodes;
    public SymbolTable() {
        nodes = new LinkedList<>();
    }

    /**
     * 添加 普通标识符
     * @param str 标识符名字
     * @return 当前标识符的位置
     */
    public int add(String str) {
        str = str.toUpperCase();
        int h = have(str);
        if(h != -1)
            return h;
        Node node = new Node();
        node.pos = chars.length() + 1;
        node.len = str.length();
        node.name = str;
        node.type = null;
        node.val = null;
        chars += str;
        nodes.add(node);
        node.index = nodes.size();
        return node.index;
    }

    /**
     * 添加 特殊 标识符
     * @param str 标识符值
     * @param isNum 是否为数字
     * @return 当前标识符的位置
     */
    public int add(String str, boolean isNum) {
        str = str.toUpperCase();
        int h = have(str);
        if(h != -1)
            return h;
        Node node = new Node();
        node.name = str;
        if(isNum) {
            node.pos = null;
            node.len = null;
            node.val =  str;
            node.type = Node.Type.N;
        }else {
            node.pos = chars.length()+1;
            node.len = str.length();
            node.val =  null;
            node.type = Node.Type.C;
            chars += str;
        }
        nodes.add(node);
        node.index = nodes.size();
        return node.index;
    }

    private int have(String str) {
        str = str.toUpperCase();
        for(Node node:nodes) {
            if(node.name.equals(str))
                return node.index;
        }
        return -1;
    }

    @Override
    public String toString() {
        return  nodes + " \n" ;
    }

    public String getChars() {
        return chars;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}