package com.example.contactsmvvm.Database_Dao_Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_contacts")
public class Contact_Entity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String email;
    private String notes;

    public Contact_Entity(String name, String phone, String email, String notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
