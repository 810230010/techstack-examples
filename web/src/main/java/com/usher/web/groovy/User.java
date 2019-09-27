package com.usher.web.groovy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huajia 2019/09/24
 **/

public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("aa");

        List<User> list = new ArrayList<>();
        list.add(user);

        User res = list.get(0);
        res.setName("asdad");
        System.out.println(list);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
