package com.tao;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author DongTao
 * @since 2018-09-05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private int age;
    private String email;

    @JSONField(name = "sex_2")
    private UserType sex;

    public User() {
    }

    public User(String name, int age, String email, UserType sex) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.sex = sex;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getSex() {
        return sex;
    }

    public void setSex(UserType sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                '}';
    }
}
