
package com.example.shortsclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("posts")
    @Expose
    private List<Post> posts;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPosts(List<Post> postList) {posts.addAll(postList);}

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
