package com.txs.springboottest.动态实例化;

public class User {
    private Integer id =1;

    private String name ="111";

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
