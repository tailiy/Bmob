package com.example.administrator.bmob;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/3/6.
 */

public class person extends BmobObject{
    String name;
    int age;
    String address;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public BmobFile getHead() {
        return head;
    }

    public void setHead(BmobFile head) {
        this.head = head;
    }

    BmobFile head;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
