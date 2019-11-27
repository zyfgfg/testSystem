package com.LoginDemo;

import com.ObjectDemo.Student;
import com.ObjectDemo.Teacher;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author zy
 */
public class login1 extends JFrame {
    private static JFrame frame = new JFrame("兄弟连考试系统");
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;
    JPanel panel;


    static {
        try {
            socket = new Socket("192.168.9.110", 8888);
            oos = new ObjectOutputStream(socket.getOutputStream());
            dis = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    login1() {
        panel = new JPanel();

        this.setLayout(null);
        this.setSize(800, 600);
        this.setResizable(false);

        JLabel userLabel = new JLabel("用户名");
        userLabel.setBounds(300, 200, 80, 25);
        this.add(userLabel);
        JLabel userLabel2 = new JLabel("欢迎来到兄弟连考试系统");
        userLabel2.setBounds(330, 150, 200, 25);
        this.add(userLabel2);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(350, 250, 165, 25);
        this.add(passwordText);

        JButton loginButton = new JButton("登陆");
        loginButton.setBounds(300, 300, 80, 25);

        JRadioButton jRadioButton1 = new JRadioButton("学生");
        jRadioButton1.setBounds(300, 300, 100, 100);
        JRadioButton jRadioButton2 = new JRadioButton("教师");
        jRadioButton2.setBounds(400, 300, 100, 100);
        jRadioButton1.setSelected(true);

        ButtonGroup bg = new ButtonGroup();//创建按钮组,为了单选

        bg.add(jRadioButton1);
        bg.add(jRadioButton2);
        this.add(loginButton);
        this.add(jRadioButton1);
        this.add(jRadioButton2);

        // 创建登录按钮
        JButton loginButton2 = new JButton("退出");
        loginButton2.setBounds(400, 300, 80, 25);
        this.add(loginButton2);

        JTextField userText = new JTextField(20);
        userText.setBounds(350, 200, 165, 25);
        this.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(300, 250, 80, 25);
        this.add(passwordLabel);
        this.setVisible(true);


        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                Boolean s = true;
                //学生登陆
                if (jRadioButton1.isSelected()) {
                    Student user = new Student(userText.getText(), new String(passwordText.getPassword()));
                    try {
                        oos.writeInt(0);
                        oos.flush();
                        oos.writeObject(user);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 4.接收反馈信息并输出
                    boolean flag = false;
                    try {
                        flag = dis.readBoolean();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (flag) {
                        JOptionPane.showMessageDialog(panel, "登陆成功", "验证消息", JOptionPane.INFORMATION_MESSAGE);
                        login2 loglin = null;
                        try {
                            loglin = new login2();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        loglin.setVisible(true);
                        loglin.setSize(800, 600);
                        loglin.setResizable(false);
                        loglin.setTitle("学生管理系统");
                        dispose();


                    } else {
                        JOptionPane.showMessageDialog(panel, "您输入信息有误", "验证消息", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

                //教师登陆
                if (jRadioButton2.isSelected()) {
                    Teacher user1 = new Teacher(userText.getText(), new String(passwordText.getPassword()));
                    try {
                        s = false;
                        oos.writeInt(-1);
                        oos.flush();
                        oos.writeObject(user1);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    boolean flag1 = false;
                    try {
                        flag1 = dis.readBoolean();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (flag1) {
                        JOptionPane.showMessageDialog(panel, "登陆成功", "验证消息", JOptionPane.INFORMATION_MESSAGE);
                        login6 login = null;
                        try {
                            login = new login6();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        login.setVisible(true);
                        login.setSize(800, 600);
                        login.setResizable(false);
                        login.setTitle("管理员系统");
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(panel, "您输入信息有误", "验证消息", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }


        });
        loginButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);

            }

    });}

    public static void main(String[] args) throws Exception {
        new login1();
    }


}



