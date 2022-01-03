package com.practice.MajorProject;

import com.google.api.client.util.DateTime;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Video {
    private String channelId;
    private String channelTitle;
    private String description;
    private JSONObject thumbnails;
    private Date publishedAt;
    private String title;
    private String videoId;
    private String tag;

    public Video(String channelId, String channelTitle, String description, JSONObject thumbnails,
                 Date publishedAt, String title, String videoId, String tag) {
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.description = description;
        this.thumbnails = thumbnails;
        this.publishedAt = publishedAt;
        this.title = title;
        this.videoId = videoId;
        this.tag = tag;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JSONObject getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(JSONObject thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
