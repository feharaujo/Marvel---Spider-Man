package br.com.fearaujo.marvel.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @SerializedName("issueNumber")
    private Integer issueNumber;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }
}
