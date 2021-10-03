package com.dkolotsey.datey.Data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface ContactsDao {
    @Insert
    public void addData(Contacts contacts);

    @Query("select * from notification_list")
    public List<Contacts> getContacts();

    @Update
    public void update(Contacts contacts);

    @Delete
    public void delete(Contacts contacts);
}
