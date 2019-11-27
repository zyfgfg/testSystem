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
public class login9 extends JFrame {
    private JTextArea jTextArea;
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;
    private JPanel panel;
    Student[] student;
    private static JFrame frame = new JFrame("修改学员");

    static {
        socket = login1.socket;

        try {
            dis = login1.dis;
            oos = login1.oos;

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            oos.writeInt(7);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public login9() {
        this.setTitle("修改学员");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLayout(null);

        this.setSize(800, 600);

        JLabel userLabe2 = new JLabel("修改学员(根据学生编号进行修改)");
        userLabe2.setBounds(380, 150, 200, 30);
        this.add(userLabe2);
        JLabel userLabel3 = new JLabel("学生编号");
        userLabel3.setBounds(300, 200, 200, 30);
        this.add(userLabel3);
        JTextField userText3 = new JTextField(20);
        userText3.setBounds(350, 200, 200, 30);
        this.add(userText3);
        JLabel userLabel4 = new JLabel("学生姓名");
        userLabel4.setBounds(300, 250, 200, 30);
        this.add(userLabel4);
        JTextField userText4 = new JTextField(20);
        userText4.setBounds(350, 250, 200, 30);
        this.add(userText4);
        JLabel userLabel5 = new JLabel("学生密码");
        userLabel5.setBounds(300, 300, 200, 30);
        this.add(userLabel5);
        JTextField userText5 = new JTextField(20);
        userText5.setBounds(350, 300, 200, 30);
        this.add(userText5);
        JLabel userLabel6 = new JLabel("学生分数");
        userLabel6.setBounds(300, 350, 200, 30);
        this.add(userLabel6);
        JTextField userText6 = new JTextField(20);
        userText6.setBounds(350, 350, 200, 30);
        this.add(userText6);

        JButton loginButton = new JButton("确认修改");
        loginButton.setBounds(300, 400, 100, 30);
        this.add(loginButton);
        JButton loginButton5 = new JButton("退出");
        loginButton5.setBounds(420, 400, 100, 30);
        this.add(loginButton5);


        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String sx = userText3.getText();
                String se = userText4.getText();
                String sq = userText5.getText();
                String s = userText6.getText();
                int sd = Integer.parseInt(sx);
                int as = Integer.parseInt(s);
                Student student = new Student(sd, se, sq, as);
                try {
                    oos.writeObject(student);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean flag = false;
                try {
                    flag = dis.readBoolean();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (flag) {
                    JOptionPane.showMessageDialog(panel, "修改成功", "验证消息", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "修改失败", "验证消息", JOptionPane.INFORMATION_MESSAGE);

                }


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
        new login7();

    }

}


