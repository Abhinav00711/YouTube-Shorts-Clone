
package com.example.shortsclone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reaction {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("voted")
    @Expose
    private Boolean voted;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getVoted() {
        return voted;
    }

    public void setVoted(Boolean voted) {
        this.voted = voted;
    }

}
