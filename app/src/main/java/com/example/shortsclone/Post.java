
package com.example.shortsclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("postId")
    @Expose
    private String postId;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("comment")
    @Expose
    private Comment comment;
    @SerializedName("reaction")
    @Expose
    private Reaction reaction;
    @SerializedName("submission")
    @Expose
    private Submission submission;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

}
