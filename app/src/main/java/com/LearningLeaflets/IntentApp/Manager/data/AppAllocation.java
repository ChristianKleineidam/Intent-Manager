package com.LearningLeaflets.IntentApp.Manager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "app_allocation")
public class AppAllocation implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo()
    private int id;

    @NonNull
    @ColumnInfo()
    private String name;

    @NonNull
    @ColumnInfo()
    private String intent;

    @ColumnInfo()
    private int slot;

    @ColumnInfo()
    private boolean active;

    public AppAllocation(String name, String intent, int slot, boolean active) {
        this.name = name;
        this.intent = intent;
        this.slot = slot;
        this.active = active;
    }

    public String getName(){return this.name;}

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getIntent() {
        return intent;
    }

    public void setIntent(@NonNull String intent) {
        this.intent = intent;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
