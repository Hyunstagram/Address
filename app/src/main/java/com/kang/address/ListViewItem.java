package com.kang.address;

public class ListViewItem {
    private String nameStr ;
    private String numStr ;
    private String emailStr ;

    public void setName(String name) {
        nameStr = name;
    }
    public void setNum(String num) {
        numStr = num;
    }
    public void setEmail(String email) {
        emailStr = email;
    }

    public String getName() {
        return this.nameStr;
    }
    public String getNum() {
        return this.numStr;
    }
    public String getEmail() {
        return this.emailStr;
    }
}