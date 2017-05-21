package cc.chauncy.foc.JLA;

import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;

import javax.swing.*;
import java.io.File;

/**
 * 主函数
 * Created by 13969 on 2017/5/17.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    JLAMain jla = new JLAMain();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        });
    }
}
