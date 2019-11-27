package com.LoginDemo;

import com.ObjectDemo.Student;
import com.ObjectDemo.Testquestion;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class login3 extends JFrame implements ActionListener {
    private JButton start, commit, back, next;
    private JRadioButton aButton, bButton, cButton, dButton;
    private ButtonGroup buttonGroup;
    private JLabel label, clock;
    private JTextArea jTextArea;
    private JPanel panel, panel2, panel3;
    Testquestion t1;
    Testquestion[] questions;
    int examtime;

    int p = 0;//设置题目数指针
    private int topicnum = 0;
    private int right, error;                                                     //答对和答错
    ClockDispaly mt;
    static ObjectOutputStream oos = null;
    static ObjectInputStream dis = null;
    static Socket socket = null;

//倒计时模块

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
            oos.writeInt(1);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public login3() {
        this.setResizable(false);
        this.setTitle("学生在线考试系统");                                   //设置标题
        this.setSize(800, 600);                                           //设置窗口大小
        this.setLocationRelativeTo(null);                                //设置显示位置居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             //设置关闭时关闭

        panel = new JPanel();                                            //初始化面板
        panel2 = new JPanel();
        panel3 = new JPanel();
        label = new JLabel("总考试时间:100分钟 ");                             //初始化并命名标签
        clock = new JLabel();
        jTextArea = new JTextArea(10, 35);                                //初始化文本区域
        jTextArea.setEditable(false);                                   //设置文本不可修改

        aButton = new JRadioButton("A");                                //初始化单选按钮
        bButton = new JRadioButton("B");
        cButton = new JRadioButton("C");
        dButton = new JRadioButton("D");
        buttonGroup = new ButtonGroup();                                 //初始化选项组

        start = new JButton("开始考试");                                   //初始化按键
        back = new JButton("上一题");
        next = new JButton("下一题");
        commit = new JButton("提交考试");
        JButton loginButton5 = new JButton("退出");

        aButton.addActionListener(this);                              //单选按钮添加监听事件
        bButton.addActionListener(this);
        cButton.addActionListener(this);
        dButton.addActionListener(this);

        start.addActionListener(this);                                //按钮添加监听事件
        back.addActionListener(this);
        next.addActionListener(this);
        commit.addActionListener(this);


        buttonGroup.add(aButton);                                     //把单选按钮放到选项组
        buttonGroup.add(bButton);
        buttonGroup.add(cButton);
        buttonGroup.add(dButton);

        panel.add(label);                                             //把标签放入面板panel
        panel.add(clock);
        panel.add(start);                                             //把按键放入面板panel
        panel2.add(jTextArea);                                        //把文本区域放入面板panel2
        panel3.add(aButton);                                          //把单选按钮放入面板panel3
        panel3.add(bButton);
        panel3.add(cButton);
        panel3.add(dButton);
        panel3.add(back);                                             //把按键放入面板panel3
        panel3.add(next);
        panel3.add(commit);
        panel3.add(loginButton5);

        this.add(panel, BorderLayout.NORTH);                           //设置面板panel放在上面
        this.add(panel2, BorderLayout.CENTER);                         //设置面板panel2放在中间
        this.add(panel3, BorderLayout.SOUTH);                         //设置面板panel放在下面

        this.setVisible(true);                                        //设置窗口可见

        mt = new ClockDispaly(clock, 100);
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


    public void createExam() throws IOException, ClassNotFoundException {//创建考试模块
        ArrayList<Testquestion> qlist = new ArrayList<Testquestion>();//创建一个向量列表，用于动态增加试题
        Testquestion t;
        String questionText = "";
        String standardKey;
        Object obj = null;
        try {
            for (int j = 0; j < 10; j++) {
                obj = dis.readObject();

                Testquestion testquestion = (Testquestion) obj;
                questionText = testquestion.getQuestionText();
                standardKey = testquestion.getStandardkey();
                t = new Testquestion(questionText, standardKey);
                qlist.add(t);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        //统计试题数量
        topicnum = qlist.size();
        questions = new Testquestion[topicnum];
        for (int i = 0; i < qlist.size(); i++)      //读取试题
        {
            questions[i] = qlist.get(i);
        }

    }


    public void setSelected(String s) {//设置单选按钮不重复模块
        if (s.equals("a")) {
            buttonGroup.setSelected(aButton.getModel(), true);
        }
        if (s.equals("b")) {
            buttonGroup.setSelected(bButton.getModel(), true);
        }
        if (s.equals("c")) {
            buttonGroup.setSelected(cButton.getModel(), true);
        }
        if (s.equals("d")) {
            buttonGroup.setSelected(dButton.getModel(), true);
        }
        if (s.equals("")) {
            buttonGroup.clearSelection();
        }
    }

    public void showQuestion() {//设置试题模块
        jTextArea.setText("");
        jTextArea.append(questions[p].getQuestionText());//在文本区域显示试题
        setSelected(questions[p].getSelectKey());
    }

    public void showScore() throws IOException {//设置成绩模块
        right = 0;
        error = 0;
        for (int i = 0; i < topicnum; i++) {

            if (questions[i].getStandardkey().equals(questions[i].getSelectKey())) {//判断答案的正确与错误
                right++;
            } else {
                error++;
            }
        }
        int score = (int) (right * 100 / topicnum);            //设置分数
        try {
            Student stu = new Student((String)null,score);
            oos.writeObject(stu);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "答对" + right + "题，答错" + error + "题，分数为" + score);

    }


    @Override
    public void actionPerformed(ActionEvent e) {//动作监听事件
        if (e.getSource() == start) {//开始开始按键实现
            try {
                createExam();          //调用createExam模块
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            p = 0;                   //题目序号
            showQuestion();        //调用showQuestion模块
            start.setEnabled(false);//设置按钮不可点击
            mt.start();             //考试时间倒计时启动
        }
        if (e.getSource() == back) {//上一题的按键实现
            p--;
            if (p == -1) {
                JOptionPane.showMessageDialog(null, "已经是第一题");
                p++;
            }
            showQuestion();
        }
        if (e.getSource() == next) {//下一题的按键实现
            p++;
            if (p == topicnum) {
                JOptionPane.showMessageDialog(null, "已经是最后一题");
                p--;
            }
            showQuestion();
        }
        if (e.getSource() == commit) {//提交试卷的按键实现
            try {
                showScore();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            commit.setEnabled(false);
            System.exit(0);          //退出
        }

        if (e.getSource() == aButton) {
            questions[p].setSelectKey("a");
        }
        if (e.getSource() == bButton) {
            questions[p].setSelectKey("b");
        }
        if (e.getSource() == cButton) {
            questions[p].setSelectKey("c");
        }
        if (e.getSource() == dButton) {
            questions[p].setSelectKey("d");
        }


    }

    public static void main(String[] args) {
        new login3();
    }
}



