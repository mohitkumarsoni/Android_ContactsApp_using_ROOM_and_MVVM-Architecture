package com.example.contactsmvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactsmvvm.Database_Dao_Entity.Contact_Entity;

import java.util.List;


public class Contact_ViewModel extends AndroidViewModel {

    private Contact_Repository contactRepository;
    private LiveData<List<Contact_Entity>> contactList;

    public Contact_ViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new Contact_Repository(application);
        contactList = contactRepository.getContactList();
    }


    public void insertContact(Contact_Entity contact){
        contactRepository.insertData(contact);
    }
    public void updateContact(Contact_Entity contact){
        contactRepository.updateData(contact);
    }
    public void deleteContact(Contact_Entity contact){
        contactRepository.deleteData(contact);
    }
    public LiveData<List<Contact_Entity>> getContactList(){
        return contactList;
    }


}
