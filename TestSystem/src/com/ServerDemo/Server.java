package com.ServerDemo;

import com.ObjectDemo.Student;
import com.ObjectDemo.Teacher;
import com.ObjectDemo.Testquestion;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    static ResultSet rs = null;
    static Connection connection = null;
    static PreparedStatement statement = null;
    static boolean flag = false;


    static {
        String url = "jdbc:mysql://localhost:3306/zhou";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, "root", "banyuan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("数据库连接中.....");
    }


    public static void main(String[] args) throws Exception {
        List list = new ArrayList();

        Student user = null;
        Teacher user2 = null;

        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        System.out.println("服务器连接中....");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream dos = new ObjectOutputStream(socket.getOutputStream());
        Random random = new Random();
        int s = ois.readInt();
        while (true) {
            if (s == 0) {
                System.out.println("学生登陆");
                 user = (Student) ois.readObject();
                String sql = "select *from student";
                statement = connection.prepareStatement(sql);
                rs = statement.executeQuery();
                System.out.println(user.getName() + "登陆了");
                while (rs.next()) {
                    if (rs.getString("name").equals(user.getName()) && rs.getString("password").equals(user.getPassword())) {
                        flag = true;
                    }
                }

                dos.writeBoolean(flag);
                dos.flush();
                int d = ois.readInt();
                if (d == 1) {
                    System.out.println("考试");
                    for (int i = 0; i < 10; i++) {
                        int b = random.nextInt(59);
                        String sql2 = "select *from test where id =" + b;
                        statement = connection.prepareStatement(sql2);
                        rs = statement.executeQuery();

                        while (rs.next()) {
                            Testquestion testquestion = new Testquestion(rs.getString("question"), rs.getString("answer"));
                            dos.writeObject(testquestion);
                            dos.flush();
                        }}
                    Student stu=(Student) ois.readObject();
                    String y=user.getName();
                    int x=user.getId();
                    int f=stu.getScores();
                    String z=user.getPassword();
                    String t=user.getName();
                    String sql8 = "insert into student values( "+ x+",'"+ t + "','"+z+"'," + f+");";
                    statement = connection.prepareStatement(sql8);
                    int r = statement.executeUpdate(sql8);

                } else if (d == 2) {
                    System.out.println("查询成绩");
                    String e = user.getName();
                    String sql3 = "select *from student where name='" + e + "'";
                    statement = connection.prepareStatement(sql3);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        Student student1 = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("score"));
                        dos.writeObject(student1);
                        dos.flush();
                    }

                } else if (d == 3) {
                    System.out.println("修改密码");
                    Student student3 = (Student) ois.readObject();
                    System.out.println(student3);
                    String q = user.getName();
                    String h = student3.getPassword();
                    System.out.println(h);
                    String sql4 = "update student set password ='" + h + "' where name = '" + q + "'";
                    statement = connection.prepareStatement(sql4);
                    int r = statement.executeUpdate();

                } else if (d == 4) {
                    System.out.println("导出成绩");
                    String e = user.getName();
                    String sql3 = "select *from student where name='" + e + "'";
                    statement = connection.prepareStatement(sql3);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        Student student1 = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("score"));
                        dos.writeObject(student1);
                        dos.flush();
                    }

                }

            } else if (s == -1) {
                System.out.println("教师登陆");
                user2 = (Teacher) ois.readObject();
                System.out.println(user2.getName() + "登陆了");
                flag = false;
                String sql1 = "select *from teacher";
                statement = connection.prepareStatement(sql1);
                rs = statement.executeQuery();

                while (rs.next()) {
                    if (rs.getString("name").equals(user2.getName()) && rs.getString("password").equals(user2.getPassword())) {
                        flag = true;
                    }
                }
                dos.writeBoolean(flag);
                dos.flush();

                int w = ois.readInt();
                if (w == 5) {
                    int i = 4;
                    flag = false;
                    System.out.println("增加学员");
                    Student student3 = (Student) ois.readObject();
                    String q = student3.getName();
                    String h = student3.getPassword();
                    String sql4 = "insert into student values (" + i + ",'" + q + "'," + "'" + h + "',null);";
                    statement = connection.prepareStatement(sql4);
                    int r = statement.executeUpdate(sql4);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();

                } else if (w == 6) {
                    int i = 4;
                    flag = false;
                    System.out.println("删除学员");
                    Student student4 = (Student) ois.readObject();
                    String q = student4.getName();
                    String sql5 = "delete from student where name ='" + q + "'";
                    statement = connection.prepareStatement(sql5);
                    int r = statement.executeUpdate(sql5);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();

                } else if (w == 7) {
                    int i = 4;
                    flag = false;
                    System.out.println("更改学员");
                    Student student4 = (Student) ois.readObject();
                    int q = student4.getId();
                    String g = student4.getName();
                    String y = student4.getPassword();
                    int p = student4.getScores();

                    String sql5 = "update student set name = '" + g + "',password='" + y + "' ,score=" + p + " where id =" + q;
                    statement = connection.prepareStatement(sql5);
                    int r = statement.executeUpdate(sql5);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();

                } else if (w == 8) {
                    System.out.println("查询成绩");
                    String sql6 = "select *from student ";
                    statement = connection.prepareStatement(sql6);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        Student student1 = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getInt("score"));
                        System.out.println(student1);
                        dos.writeObject(student1);
                        dos.flush();
                    }
                    dos.close();

                } else if (w == 9) {
                    int i = 61;
                    flag = false;
                    System.out.println("增加考题");
                    Testquestion student4 = (Testquestion) ois.readObject();
                    String q = student4.getQuestionText();
                    String h = student4.getStandardkey();
                    String sql7 = "insert into test values (" + i + ",'" + q + "'," + "'" + h + "');";
                    statement = connection.prepareStatement(sql7);
                    int r = statement.executeUpdate(sql7);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();
                } else if (w == 10) {
                    flag = false;
                    System.out.println("更改考题");
                    Testquestion student5 = (Testquestion) ois.readObject();
                    int q = student5.getId();
                    String g = student5.getQuestionText();
                    String y = student5.getStandardkey();

                    String sql5 = "update test set question = '" + g + "', answer ='" + y + "'  where id =" + q;
                    statement = connection.prepareStatement(sql5);
                    int r = statement.executeUpdate(sql5);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();

                } else if (w == 11) {
                    int i = 4;
                    flag = false;
                    System.out.println("删除考题");
                    Testquestion student6 = (Testquestion) ois.readObject();
                    int q = student6.getId();
                    String sql5 = "delete from test where id ='" + q + "'";
                    statement = connection.prepareStatement(sql5);
                    int r = statement.executeUpdate(sql5);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();

                } else if (w == 12) {
                    System.out.println("查询考题");
                    String sql6 = "select *from test ";
                    statement = connection.prepareStatement(sql6);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        Testquestion student1 = new Testquestion(rs.getInt("id"), rs.getString("question"), rs.getString("answer"));
                        dos.writeObject(student1);
                        dos.flush();
                    }
                    dos.close();

                } else if (w == 13) {
                    int i = 61;
                    flag = false;
                    System.out.println("增加考题");
                    Testquestion student4 = (Testquestion) ois.readObject();
                    String q = student4.getQuestionText();
                    String h = student4.getStandardkey();
                    String sql7 = "insert into test values (" + i + ",'" + q + "'," + "'" + h + "');";
                    statement = connection.prepareStatement(sql7);
                    int r = statement.executeUpdate(sql7);
                    if (r > 0) {
                        flag = true;
                    }
                    dos.writeBoolean(flag);
                    dos.flush();
                }
            }

        }
    }
}



