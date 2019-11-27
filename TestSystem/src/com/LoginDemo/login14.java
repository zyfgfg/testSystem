package com.LoginDemo;

import com.ObjectDemo.Testquestion;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 */
public class login14 extends JFrame {

    private JTextArea jTextArea;
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;
    private JPanel panel;

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
            oos.writeInt(12);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public login14() throws IOException, ClassNotFoundException {
        this.setResizable(false);
        this.setTitle("查询考题");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        panel = new JPanel();
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        this.add(jTextArea);
        this.setVisible(true);
        this.add(panel);
        JScrollPane JSP = new JScrollPane(jTextArea);
        JSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(JSP);
        Object obj = null;
//        JButton loginButton5 = new JButton("退出");
//        loginButton5.setBounds(370, 270, 100, 30);
//        this.add(loginButton5);
//        loginButton5.addActionListener(new java.awt.event.ActionListener() {
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                login1 loglin3 = null;
//                loglin3 = new login1();
//                loglin3.setVisible(true);
//                loglin3.setSize(800, 600);
//                loglin3.setResizable(false);
//                loglin3.setTitle("登陆界面");
//                dispose();
//            }
//        });

        try {
            for (int i = 0; i < 60; i++) {
                obj = dis.readObject();
                Testquestion student = (Testquestion) obj;
                jTextArea.append(student.toString() + "\n");
            }
            dis.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

//        jTextArea.append(obj.toString());

        this.setVisible(true);

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new login14();
    }
}

