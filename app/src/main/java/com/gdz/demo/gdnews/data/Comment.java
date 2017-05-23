package com.gdz.demo.gdnews.data;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class Comment extends BmobObject {
    private  String content;
    private MyBmobUser user;//评论的用户，Pointer类型，一对一关系
    private Post post;//所评论的帖子，这里体现的是一对多的关系，一个评论只能属于一个微博

    public String getContent() {
        return content;
    }

    public MyBmobUser getUser() {
        return user;
    }

    public void setUser(MyBmobUser user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
