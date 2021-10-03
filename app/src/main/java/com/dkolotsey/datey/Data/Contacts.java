package com.dkolotsey.datey.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification_list")
public class Contacts {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "imgPath")
    private String imgPath;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "birthday_date")
    private String birthdayDate;

    public Contacts(int id, String imgPath, String name, String birthdayDate) {
        this.id = id;
        this.imgPath = imgPath;
        this.name = name;
        this.birthdayDate = birthdayDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }
}
