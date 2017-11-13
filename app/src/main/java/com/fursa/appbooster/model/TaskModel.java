package com.fursa.appbooster.model;

import java.io.Serializable;

/**
 * Model class which contains fields to parse from JSON
 * Created by Fursa Ilya on 09.11.17.
 */

public class TaskModel implements Serializable {
    private int id;
    private String title;
    private String description;
    private String downloadLink;
    private String iconUrl;
    private double reward;
    private String appleDownloadLink;
    private String appleBundleId;

    public TaskModel(String title, String iconUrl, double reward) {
        this.title = title;
        this.iconUrl = iconUrl;
        this.reward = reward;
    }

    public TaskModel(int id, String title, String description, String downloadLink, String iconUrl, double reward, String appleDownloadLink, String appleBundleId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.downloadLink = downloadLink;
        this.iconUrl = iconUrl;
        this.reward = reward;
        this.appleDownloadLink = appleDownloadLink;
        this.appleBundleId = appleBundleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public String getAppleDownloadLink() {
        return appleDownloadLink;
    }

    public void setAppleDownloadLink(String appleDownloadLink) {
        this.appleDownloadLink = appleDownloadLink;
    }

    public String getAppleBundleId() {
        return appleBundleId;
    }

    public void setAppleBundleId(String appleBundleId) {
        this.appleBundleId = appleBundleId;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", downloadLink='" + downloadLink + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", reward=" + reward +
                ", appleDownloadLink='" + appleDownloadLink + '\'' +
                ", appleBundleId='" + appleBundleId + '\'' +
                '}';
    }
}
