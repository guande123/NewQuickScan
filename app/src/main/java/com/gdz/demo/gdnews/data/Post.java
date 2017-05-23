package com.gdz.demo.gdnews.data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class Post  extends BmobObject{
    private String title;//帖子标题
    private String  content;//帖子内容
    private MyBmobUser author ;;//帖子的发布者，这里体现的是一对一的关系，该帖子属于某个用户

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyBmobUser getAuthor() {
        return author;
    }

    public void setAuthor(MyBmobUser author) {
        this.author = author;
    }
}
