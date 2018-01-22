package com.jimzhang.demo.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description JFileChooser实现选择文件路径
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2017-11-15 16:28
 */
public class SystemOS extends JFrame implements ActionListener {
    JPanel pnlMain;
    JTextField txtfile;
    JButton btnSelect;
    JFileChooser fc = new JFileChooser();

    public SystemOS() {
        pnlMain = new JPanel();
        this.getContentPane().add(pnlMain);
        txtfile = new JTextField(10);
        btnSelect = new JButton("选择");
        btnSelect.addActionListener(this);
        pnlMain.add(txtfile);
        pnlMain.add(btnSelect);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSelect) {
            /*
                这是尤为重要的。因为JFileChooser默认的是选择文件，而需要选目录。
                故要将DIRECTORIES_ONLY装入模型
            另外，若选择文件，则无需此句
            */
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int intRetVal = fc.showOpenDialog(this);
            if (intRetVal == JFileChooser.APPROVE_OPTION) {
                txtfile.setText(fc.getSelectedFile().getPath());
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new SystemOS();
        f.setSize(200, 300);
        f.setVisible(true);
    }
}
