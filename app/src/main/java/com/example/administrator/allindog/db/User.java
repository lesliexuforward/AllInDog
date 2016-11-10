package com.example.administrator.allindog.db;

/**
 * Created by jiang on 2016/11/10.
 */
public class User {
    private String username;
    private String password;
    private String phone;
    private String birthday;
    private String description;
    private int sex;

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    private String registerTime;


    //构造方法
    public User(String inputusername,String inputpassword,String inputphone, String inputbir,String inputnote
    ,int isboy,String inputDate){
        username=inputusername;
        password=inputpassword;
        phone=inputphone;
        birthday=inputbir;
        description=inputnote;
        sex=isboy;
        registerTime=inputDate;

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }



}
