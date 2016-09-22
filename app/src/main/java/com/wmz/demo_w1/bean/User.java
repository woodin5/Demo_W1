package com.wmz.demo_w1.bean;

/**
 * Created by wmz on 2016/9/9.
 */
public class User {
    private String name;
    private String pic;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
