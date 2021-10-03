package com.dkolotsey.datey.Data;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Contacts.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ContactsDao contactsDao();
}
