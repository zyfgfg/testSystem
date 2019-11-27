package com.LoginDemo;

import com.ObjectDemo.Student;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 *
 */
public class login2 extends JFrame {
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;
    JPanel panel;

    static {
        socket = login1.socket;

        try {
            dis = login1.dis;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            oos = login1.oos;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public login2() throws IOException {

        panel = new JPanel();

        panel.setLayout(null);
        // 创建 JLabel
        JFrame frame = new JFrame("学生管理系统");
        JLabel userLabel2 = new JLabel("欢迎来到学生管理模版");
        userLabel2.setBounds(330, 100, 250, 25);
        this.add(userLabel2);
        JButton loginButton = new JButton("开始考试(直接随机生成一套试题答题");
        loginButton.setBounds(280, 150, 250, 25);
        this.add(loginButton);
        JButton loginButton2 = new JButton("查询所有考试成绩(按时间降序排列)");
        loginButton2.setBounds(280, 200, 250, 25);
        this.add(loginButton2);
        JButton loginButton3 = new JButton("修改密码");
        loginButton3.setBounds(280, 250, 250, 25);
        this.add(loginButton3);
        JButton loginButton4 = new JButton("导出所有考试成绩");
        loginButton4.setBounds(280, 300, 250, 25);
        this.add(loginButton4);
        JButton loginButton5 = new JButton("退出");
        loginButton5.setBounds(280, 500, 250, 25);
        this.add(loginButton5);

        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login3 loglin = new login3();
                loglin.setVisible(true);
                loglin.setSize(800, 600);
                loglin.setResizable(false);
                loglin.setTitle("学生考试系统");
                dispose();
            }
        });
        loginButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login4 loglin2 = null;
                try {
                    loglin2 = new login4();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                loglin2.setVisible(true);
                loglin2.setSize(800, 600);
                loglin2.setResizable(false);
                loglin2.setTitle("查询成绩");
                dispose();


            }
        });
        loginButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login5 loglin3 = null;
                loglin3 = new login5();
                loglin3.setVisible(true);
                loglin3.setSize(800, 600);
                loglin3.setResizable(false);
                loglin3.setTitle("修改密码");
                dispose();


            }
        });
        loginButton4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(panel, "已成功复制到file文件下", "验证消息", JOptionPane.INFORMATION_MESSAGE);
                try {
                    oos.writeInt(4);
                    oos.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Student obj = (Student) dis.readObject();
                    File file = new File("/Users/edz/TestSystem/src/com/ToolsDemo/file.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream sc = new FileOutputStream(file);
                    ObjectOutputStream oes = new ObjectOutputStream(sc);
                    oes.writeObject(obj.toString());

                    oes.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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
}


