package com.inti.student.assignment;

/**
 * Created by Mister on 22/11/2018.
 */

public class user {

    private String email;
    private String fname;
    private String password;
    private String phone;
    private String userid;




    public user(String userid,String email, String password, String phone,String fname) {
        this.userid=userid;
        this.fname=fname;
        this.email = email;
        this.password = password;
        this.phone = phone;

    }

    public String getUserid(){
        return userid;
    }
    public String getEmail() {
        return email;
    }
    public String getFname(){
        return fname;
    }

    public void setFname(String fname){
        this.fname=fname;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setUserid(String userid) {
        this.userid = userid;
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
}
