package com.example.springtest.domain;

public class Member {
    private Long id;
    private String name;

    public Long getID(){return id;}
    public void setID(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
