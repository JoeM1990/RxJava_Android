package com.creasmit.task.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Task {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private long id;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("delay")
    @ColumnInfo(name = "delay")
    private String delay;

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}
