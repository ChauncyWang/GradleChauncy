/*
 * Created by JFormDesigner on Sun May 21 16:55:27 CST 2017
 */

package cc.chauncy.foc.JLA;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Chauncy
 */
public class JLAMain extends JFrame {
    private Scanner scanner;
    private File selectedFile;
    public JLAMain() {
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,400);
        setVisible(true);
        initComponents();
    }

    /**
     * 打开文件
     */
    private void openFile(ActionEvent e) {
        // TODO add your code here
        JFileChooser jFileChooser = new JFileChooser(new File("."));
        jFileChooser.showDialog(this,"选择文件");
        selectedFile = jFileChooser.getSelectedFile();
        char[] chars = Tools.readAll(selectedFile);
        textArea1.setText(String.valueOf(chars));
    }

    /**
     * 词法分析
     */
    private void lexicalAnalysis(ActionEvent e) {
        // TODO add your code here
        if(textArea1.getText().equals(" ")){
            JOptionPane.showMessageDialog(null, "要分析的内容为空!","错误:",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        scanner = new Scanner(textArea1.getText().toCharArray());
        int i = 0;
        String[] strings = new String[5];
        DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
        DefaultTableModel tableModelError = (DefaultTableModel) tableError.getModel();
        DefaultTableModel tableModelSymbol = (DefaultTableModel) tableSymbol.getModel();
        DefaultTableModel tableModelSymbol2 = (DefaultTableModel) tableSymbol2.getModel();
        tableModel.setRowCount(0);
        tableModelError.setRowCount(0);
        tableModelSymbol.setRowCount(0);
        tableModelSymbol2.setRowCount(0);

        // 添加 二元式
        while (true) {
            scanner.nextToken();
            if (scanner.getToken() == Token.EOF) {
                break;
            }
            Token token = scanner.getToken();
            String str = String.format("(%2d,%2s)",token.sortCode,(token.name == null ? scanner.getTableIndex() : "-"));
            strings[i%5] = str;
            if(i%5 == 4) {
                tableModel.addRow(strings.clone());
            }
            i++;
        }
        if(i%4 != 4)
        tableModel.addRow(strings.clone());

        //添加 错误
        java.util.List<String> list = scanner.getErrors();
        for(String str:list) {
            String[] strs = str.split(":");
            tableModelError.addRow(strs);
        }

        //添加 符号表
        SymbolTable st = scanner.getSymbolTable();
        for(SymbolTable.Node node:st.getNodes()) {
            tableModelSymbol.addRow(new String[]{""+(node.pos==null?"":node.pos),""+(node.len==null?"":node.len),
                    ""+(node.type==null?"":node.type.str),""+(node.val==null?"":node.val)});
        }
        char[] chars = st.getChars().toCharArray();
        Character[] chars1 = new Character[8];
        int j = 0;
        for(i = 0;i < chars.length;++i) {
            if(i%8 == 0) {
                if(i != 0){
                    tableModelSymbol2.addRow(chars1);
                }
                chars1 = new Character[8];
                for(int k = 0;k < 8;++k) {
                    chars1[k] = ' ';
                }
                j = 0;
            }
            chars1[j] = chars[i];
            j++;
        }
        tableModelSymbol2.addRow(chars1);
    }

    /** 保存 分析结果 */
    private void saveResult(ActionEvent e) {
        // TODO add your code here
        DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
        DefaultTableModel tableModelError = (DefaultTableModel) tableError.getModel();
        DefaultTableModel tableModelSymbol = (DefaultTableModel) tableSymbol.getModel();
        DefaultTableModel tableModelSymbol2 = (DefaultTableModel) tableSymbol2.getModel();
        try {
            File file = new File("二元式.txt");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(toStr(tableModel).getBytes());
            fos.flush();
            fos.close();

            File file1 = new File("错误表.txt");
            file1.createNewFile();
            fos = new FileOutputStream(file1);
            fos.write(toStr(tableModelError).getBytes());
            fos.flush();
            fos.close();

            File file2 = new File("符号表.txt");
            file2.createNewFile();
            fos = new FileOutputStream(file2);
            fos.write(toStr(tableModelSymbol).getBytes());
            fos.write(toStr(tableModelSymbol2).getBytes());
            fos.flush();
            fos.close();
            //提示 保存成功
            JOptionPane.showMessageDialog(null, "请在此程序目录下查看保存的文件!","保存成功:",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e1) {

        }
    }
    private String toStr(DefaultTableModel defaultTableModel) {
        String str = "";
        int x = defaultTableModel.getRowCount();
        int y = defaultTableModel.getColumnCount();
        Object[][] objects = new Object[x][y];
        for(int i = 0;i < x;++i){
            for(int j = 0;j < y;++j) {
                str += defaultTableModel.getValueAt(i,j)+"\t";
            }
            str += "\n";
        }
        return str;
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        toolBar1 = new JToolBar();
        button1 = new JButton();
        button3 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        rightPanel = new JPanel();
        labelSymbol = new JLabel();
        scrollPane4 = new JScrollPane();
        tableSymbol = new JTable();
        scrollPane5 = new JScrollPane();
        tableSymbol2 = new JTable();
        bottomPanel = new JPanel();
        labelError = new JLabel();
        scrollPane3 = new JScrollPane();
        tableError = new JTable();
        leftPanel = new JPanel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        table2 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== toolBar1 ========
        {

            //---- button1 ----
            button1.setText("\u6253\u5f00\u6587\u4ef6");
            button1.setIcon(null);
            button1.addActionListener(e -> openFile(e));
            toolBar1.add(button1);

            //---- button3 ----
            button3.setText("\u8bcd\u6cd5\u5206\u6790");
            button3.addActionListener(e -> lexicalAnalysis(e));
            toolBar1.add(button3);

            //---- button2 ----
            button2.setText("\u4fdd\u5b58\u5206\u6790\u7ed3\u679c");
            button2.addActionListener(e -> saveResult(e));
            toolBar1.add(button2);
        }
        contentPane.add(toolBar1);
        toolBar1.setBounds(0, 5, 800, 25);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(textArea1);
            }
            panel1.add(scrollPane2);
            scrollPane2.setBounds(200, 5, 400, 350);

            //======== rightPanel ========
            {
                rightPanel.setBorder(new LineBorder(Color.gray, 3, true));
                rightPanel.setLayout(null);

                //---- labelSymbol ----
                labelSymbol.setText("\u7b26\u53f7\u8868");
                labelSymbol.setHorizontalAlignment(SwingConstants.CENTER);
                labelSymbol.setLabelFor(tableSymbol);
                labelSymbol.setFont(new Font("\u65b9\u6b63\u5170\u4ead\u8d85\u7ec6\u9ed1\u7b80\u4f53", Font.BOLD, 14));
                rightPanel.add(labelSymbol);
                labelSymbol.setBounds(0, 5, 195, labelSymbol.getPreferredSize().height);

                //======== scrollPane4 ========
                {

                    //---- tableSymbol ----
                    tableSymbol.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "pos", "len", "type", "val"
                        }
                    ));
                    tableSymbol.setEnabled(false);
                    scrollPane4.setViewportView(tableSymbol);
                }
                rightPanel.add(scrollPane4);
                scrollPane4.setBounds(5, 25, 185, 325);

                //======== scrollPane5 ========
                {

                    //---- tableSymbol2 ----
                    tableSymbol2.setToolTipText("\u7b26\u53f7\u8868");
                    tableSymbol2.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "1", "2", "3", "4", "5", "6", "7", "8"
                        }
                    ));
                    tableSymbol2.setEnabled(false);
                    tableSymbol2.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 12));
                    scrollPane5.setViewportView(tableSymbol2);
                }
                rightPanel.add(scrollPane5);
                scrollPane5.setBounds(5, 355, 185, 105);
            }
            panel1.add(rightPanel);
            rightPanel.setBounds(605, 0, 195, 465);

            //======== bottomPanel ========
            {
                bottomPanel.setBorder(new LineBorder(Color.gray, 2, true));
                bottomPanel.setLayout(null);

                //---- labelError ----
                labelError.setText("\u9519\u8bef\u8868");
                labelError.setHorizontalAlignment(SwingConstants.LEFT);
                labelError.setLabelFor(tableError);
                labelError.setFont(new Font("\u65b9\u6b63\u5170\u4ead\u8d85\u7ec6\u9ed1\u7b80\u4f53", Font.BOLD, 14));
                bottomPanel.add(labelError);
                labelError.setBounds(0, 5, 400, labelError.getPreferredSize().height);

                //======== scrollPane3 ========
                {

                    //---- tableError ----
                    tableError.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null},
                            {null, null},
                            {null, null},
                            {null, null},
                            {null, null},
                            {null, null},
                            {null, null},
                            {null, null},
                            {null, null},
                        },
                        new String[] {
                            "\u4f4d\u7f6e", "\u9519\u8bef\u4fe1\u606f"
                        }
                    ));
                    {
                        TableColumnModel cm = tableError.getColumnModel();
                        cm.getColumn(0).setMinWidth(100);
                        cm.getColumn(0).setMaxWidth(100);
                        cm.getColumn(0).setPreferredWidth(100);
                    }
                    scrollPane3.setViewportView(tableError);
                }
                bottomPanel.add(scrollPane3);
                scrollPane3.setBounds(0, 25, 400, 80);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < bottomPanel.getComponentCount(); i++) {
                        Rectangle bounds = bottomPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = bottomPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    bottomPanel.setMinimumSize(preferredSize);
                    bottomPanel.setPreferredSize(preferredSize);
                }
            }
            panel1.add(bottomPanel);
            bottomPanel.setBounds(200, 360, bottomPanel.getPreferredSize().width, 105);

            //======== leftPanel ========
            {
                leftPanel.setBorder(new LineBorder(Color.gray, 3, true));
                leftPanel.setLayout(null);

                //---- label2 ----
                label2.setText("\u4e8c\u5143\u5f0f");
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setLabelFor(table2);
                label2.setFont(new Font("\u65b9\u6b63\u5170\u4ead\u8d85\u7ec6\u9ed1\u7b80\u4f53", Font.BOLD, 14));
                leftPanel.add(label2);
                label2.setBounds(0, 5, 195, label2.getPreferredSize().height);

                //======== scrollPane1 ========
                {

                    //---- table2 ----
                    table2.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "1", "2", "3"
                        }
                    ));
                    table2.setEnabled(false);
                    scrollPane1.setViewportView(table2);
                }
                leftPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 25, 195, 440);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < leftPanel.getComponentCount(); i++) {
                        Rectangle bounds = leftPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = leftPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    leftPanel.setMinimumSize(preferredSize);
                    leftPanel.setPreferredSize(preferredSize);
                }
            }
            panel1.add(leftPanel);
            leftPanel.setBounds(0, 0, leftPanel.getPreferredSize().width, 465);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(new Rectangle(new Point(0, 35), panel1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(800, 500));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JToolBar toolBar1;
    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JPanel panel1;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JPanel rightPanel;
    private JLabel labelSymbol;
    private JScrollPane scrollPane4;
    private JTable tableSymbol;
    private JScrollPane scrollPane5;
    private JTable tableSymbol2;
    private JPanel bottomPanel;
    private JLabel labelError;
    private JScrollPane scrollPane3;
    private JTable tableError;
    private JPanel leftPanel;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTable table2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
