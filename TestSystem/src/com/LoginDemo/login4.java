package com.LoginDemo;

import com.ObjectDemo.Student;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class login4 extends JFrame {

    private JTextArea jTextArea;
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;
    private JPanel panel;
    Student[] student;

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
        try {
            oos.writeInt(2);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public login4() throws IOException, ClassNotFoundException {
        this.setResizable(false);
        this.setTitle("查询考试成绩");
        this.setSize(800, 600);
        panel = new JPanel();
        jTextArea = new JTextArea(10, 35);
        jTextArea.setEditable(false);
        panel.add(jTextArea);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        jTextArea.setText("");
        JButton loginButton5 = new JButton("退出");
        loginButton5.setBounds(280, 400, 150, 25);
        this.add(loginButton5);


        Student obj = (Student) dis.readObject();


        jTextArea.append(obj.toString());

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
        new login4();
    }
}

