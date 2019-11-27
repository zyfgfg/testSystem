package com.LoginDemo;

import com.ObjectDemo.Student;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class login5 extends JFrame {
    private JTextArea jTextArea;
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;
    private JPanel panel;
    Student[] student;
    private static JFrame frame = new JFrame("修改密码");

    static {
        socket = login1.socket;

        try {
            dis = login1.dis;
            oos = login1.oos;

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            oos.writeInt(3);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public login5() {
        this.setTitle("修改密码");                                   //设置标题
        this.setSize(800, 600);                                           //设置窗口大小
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        panel = new JPanel();


        panel = new JPanel();

        JLabel userLabe2 = new JLabel("修改后密码");
        userLabe2.setBounds(300, 200, 150, 25);
        this.add(userLabe2);
        JTextField userText2 = new JTextField(20);
        userText2.setBounds(370, 200, 200, 25);
        this.add(userText2);
        JLabel userLabel3 = new JLabel("再输一遍");
        userLabel3.setBounds(300, 250, 200, 25);
        this.add(userLabel3);
        JTextField userText3 = new JTextField(20);
        userText3.setBounds(370, 250, 200, 25);
        this.add(userText3);
        JButton loginButton = new JButton("确认修改");
        loginButton.setBounds(300, 300, 100, 25);
        this.add(loginButton);
        JButton loginButton5 = new JButton("退出");
        loginButton5.setBounds(420, 300, 100, 25);
        this.add(loginButton5);

        if (userText2.equals(userText3)) {
            JOptionPane.showMessageDialog(panel, "两次密码不一致，请重输", "验证消息", JOptionPane.INFORMATION_MESSAGE);
        }
        JPanel finalPanel = panel;

        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String sd = (String) userText2.getText();
                Student student = new Student("123", sd);
                try {
                    oos.writeObject(student);
                    System.out.println(student);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(panel, "修改成功", "验证消息", JOptionPane.INFORMATION_MESSAGE);


            }
        });
        loginButton5.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1 loglin3 = null;
                loglin3 = new login1();
                loglin3.setVisible(true);
                loglin3.setSize(800, 600);
                loglin3.setResizable(false);
                loglin3.setTitle("登陆界面");
                dispose();


            }
        });


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new login5();

    }

}


