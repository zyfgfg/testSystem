package com.ObjectDemo;

import java.io.Serializable;

/**
 *
 */
public class Testquestion implements Serializable {
    private int id;
    private String questionText = "";//定义题目
    private String standardkey = "";// 定义正确答案
    private String selectKey = "";// 定义输入答案

    public Testquestion(String questionText, String standardkey) {
        this.questionText = questionText;
        this.standardkey = standardkey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Testquestion(int id, String questionText, String standardkey) {
        this.id = id;
        this.questionText = questionText;
        this.standardkey = standardkey;
    }

    public Testquestion(int id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public Testquestion() {
    }

    public Testquestion(String questionText, String standardkey, String selectKey) {
        this.questionText = questionText;
        this.standardkey = standardkey;
        this.selectKey = selectKey;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getStandardkey() {
        return standardkey;
    }

    public void setStandardkey(String standardkey) {
        this.standardkey = standardkey;
    }

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public boolean check() {
        if (this.selectKey.equals(this.standardkey)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return +id +
                "、question='" + questionText + "\n" + '\'' +
                "answer='" + standardkey + '\'';
    }
}
