package com.asyf.mybatis.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String username;

    private String password;

    private String email;

    private String age;

    private Timestamp createDate;

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        // 比较引用
        if (this == o) return true;
        // 比较类型
        if (o == null || getClass() != o.getClass()) return false;
        // 比较内容，这里比较的是 id 值
        User other = (User) o;
        // 判断逻辑就是：三元运算符 的结果如果等于null 那么就返回 false
        // 首先确定当前 id 不等于 null，然后确定当前 id 不等于被比较的 id
        // 最后条件都满足了之后还是不等null，说明肯定不一致就返回 false 就可以了
        if (id != null ? !id.equals(other.id) : other.id != null) return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
                + email + ", age=" + age + "]";
    }

    /*public String isAdmin() {
        return "1";
    }*/
    /*public void isAdmin() {
     //   return "1";
    }*/
}