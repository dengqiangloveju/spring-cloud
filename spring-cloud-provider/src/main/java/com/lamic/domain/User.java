package com.lamic.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private int id;
    @NotNull(message="名字不能为空")
    @Size(min=5, max=10, message="请输入5至10个字符")
    private String name;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", createTime=" + createTime + "]";
    }
}
