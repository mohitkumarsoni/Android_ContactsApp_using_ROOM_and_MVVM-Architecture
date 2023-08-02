package com.example.contactsmvvm.Database_Dao_Entity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Contact_Dao {

    @Insert
    public void insertContact(Contact_Entity contact);
    @Update
    public void updateContact(Contact_Entity contact);
    @Delete
    public void deleteContact(Contact_Entity contact);
    @Query("SELECT * FROM my_contacts")
    public LiveData<List<Contact_Entity>> getAllContact();
    @Query("SELECT * FROM my_contacts WHERE id = :id")
    public LiveData<List<Contact_Entity>> getNoteById(int id);

}
