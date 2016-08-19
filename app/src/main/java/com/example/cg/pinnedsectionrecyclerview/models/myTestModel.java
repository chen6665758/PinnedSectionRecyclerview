package com.example.cg.pinnedsectionrecyclerview.models;

/**
* 测试数据结构
* 作者：cg
* 时间：2016/8/18 0018 上午 11:01
*/
public class myTestModel {

    private String title;             //类别，标题
    private String name;              //股票名称
    private String value1;            //涨跌值1
    private String value2;            //涨跌值2
    private int type;                 //数据显示的类别 1:Grid布局数据  2:List布局数据　3:类别布局数据
    private int sordid;               //分类的顺序值，此值为信息所属类别的顺序值

    public myTestModel() {
    }


    public myTestModel(String title, String name, String value1, String value2,int type,int sordid) {
        this.title = title;
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        this.type = type;
        this.sordid = sordid;


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSordid() {
        return sordid;
    }

    public void setSordid(int sordid) {
        this.sordid = sordid;
    }
}
