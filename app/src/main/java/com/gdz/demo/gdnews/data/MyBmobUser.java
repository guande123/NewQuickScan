package com.gdz.demo.gdnews.data;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
//MyBmobUser 为特殊类BmobUser 的子类
//扩展字段
//    用户表（_User）、帖子表（Post）、评论表（Comment）,
public class MyBmobUser extends BmobUser {
   private Integer age;//为用户表新增一个age字段，注意其必须为`Integer`类型，而不是int

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
