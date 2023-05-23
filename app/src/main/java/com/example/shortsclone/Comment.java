
package com.example.shortsclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("commentingAllowed")
    @Expose
    private Boolean commentingAllowed;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getCommentingAllowed() {
        return commentingAllowed;
    }

    public void setCommentingAllowed(Boolean commentingAllowed) {
        this.commentingAllowed = commentingAllowed;
    }

}
