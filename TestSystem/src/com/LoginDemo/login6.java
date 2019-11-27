package com.LoginDemo;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class login6 extends JFrame {
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

    public login6() throws IOException {

        panel = new JPanel();

        this.setLayout(null);
        // 创建 JLabel
        JFrame frame = new JFrame("管理员系统");
        JLabel userLabel2 = new JLabel("欢迎来到教师管理模版");
        userLabel2.setBounds(340, 150, 150, 25);
        this.add(userLabel2);
        JButton loginButton = new JButton("增加考试学员");
        loginButton.setBounds(230, 200, 150, 25);
        this.add(loginButton);
        JButton loginButton2 = new JButton("删除考试学员");
        loginButton2.setBounds(230, 250, 150, 25);
        this.add(loginButton2);
        JButton loginButton3 = new JButton("修改考试学员");
        loginButton3.setBounds(230, 300, 150, 25);
        this.add(loginButton3);
        JButton loginButton4 = new JButton("查询考试学员");
        loginButton4.setBounds(230, 350, 150, 25);
        this.add(loginButton4);
        JButton loginButton5 = new JButton("增加考题");
        loginButton5.setBounds(230, 400, 150, 25);
        this.add(loginButton5);
        JButton loginButton6 = new JButton("修改考题");
        loginButton6.setBounds(430, 200, 150, 25);
        this.add(loginButton6);
        JButton loginButton7 = new JButton("删除考题");
        loginButton7.setBounds(430, 250, 150, 25);
        this.add(loginButton7);
        JButton loginButton8 = new JButton("查询考题");
        loginButton8.setBounds(430, 300, 150, 25);
        this.add(loginButton8);
        JButton loginButton9 = new JButton("批量导入考题");
        loginButton9.setBounds(430, 350, 150, 25);
        this.add(loginButton9);
        JButton loginButton10 = new JButton("退出 ");
        loginButton10.setBounds(430, 400, 150, 25);
        this.add(loginButton10);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login7 loglin = new login7();
                loglin.setVisible(true);
                loglin.setSize(800, 600);
                loglin.setResizable(false);
                loglin.setTitle("增加学员");
                dispose();
            }
        });
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login7 loglin = new login7();
                loglin.setVisible(true);
                loglin.setSize(800, 600);
                loglin.setResizable(false);
                loglin.setTitle("增加学员");
                dispose();
            }
        });
        loginButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login8 loglin = new login8();
                loglin.setVisible(true);
                loglin.setSize(800, 600);
                loglin.setResizable(false);
                loglin.setTitle("删除学员");
                dispose();
            }
        });
        loginButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login9 loglin = new login9();
                loglin.setVisible(true);
                loglin.setSize(800, 600);
                loglin.setResizable(false);
                loglin.setTitle("修改学员");
                dispose();
            }
        });
        loginButton4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login10 loglin10 = null;
                try {
                    loglin10 = new login10();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                loglin10.setVisible(true);
                loglin10.setSize(800, 600);
                loglin10.setResizable(false);
                loglin10.setTitle("查询考试成员");
                dispose();
            }
        });
        loginButton5.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login11 loglin11 = null;
                loglin11 = new login11();
                loglin11.setVisible(true);
                loglin11.setSize(800, 600);
                loglin11.setResizable(false);
                loglin11.setTitle("增加考题");
                dispose();
            }
        });
        loginButton6.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login12 loglin12 = null;
                loglin12 = new login12();
                loglin12.setVisible(true);
                loglin12.setSize(800, 600);
                loglin12.setResizable(false);
                loglin12.setTitle("修改考题");
                dispose();
            }
        });
        loginButton7.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login13 loglin13 = null;
                loglin13 = new login13();
                loglin13.setVisible(true);
                loglin13.setSize(800, 600);
                loglin13.setResizable(false);
                loglin13.setTitle("删除考题");
                dispose();
            }
        });
        loginButton8.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login14 loglin14 = null;
                try {
                    loglin14 = new login14();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                assert loglin14 != null;
                loglin14.setVisible(true);
                loglin14.setSize(800, 600);
                loglin14.setResizable(false);
                loglin14.setTitle("查询考题");
                dispose();
            }
        });
        loginButton9.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login15 loglin15 = null;
                loglin15 = new login15();
                loglin15.setVisible(true);
                loglin15.setSize(800, 600);
                loglin15.setResizable(false);
                loglin15.setTitle("查询考题");
                dispose();
            }
        });
        loginButton10.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login1 loglin1 = null;
                loglin1 = new login1();
                loglin1.setVisible(true);
                loglin1.setSize(800, 600);
                loglin1.setResizable(false);
                loglin1.setTitle("登陆页面");
                dispose();
            }
        });


    }


}