package com.example.contactsmvvm.Database_Dao_Entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact_Entity.class},version = 1)
public abstract class Contact_Database extends RoomDatabase {

    private static final String DATABASE_NAME = "contact_db";
    private static Contact_Database instance_db;

    public static synchronized Contact_Database getInstance(Context context){
        if (instance_db == null){
            instance_db = Room.databaseBuilder(context, Contact_Database.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return instance_db;
    }

    public abstract Contact_Dao contactDao();

}
