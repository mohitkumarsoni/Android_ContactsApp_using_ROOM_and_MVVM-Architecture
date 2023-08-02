package com.example.contactsmvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.contactsmvvm.Database_Dao_Entity.Contact_Dao;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Database;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Entity;

import java.util.List;

public class Contact_Repository {

    Contact_Dao contact_dao;
    LiveData<List<Contact_Entity>> contactList;


    public Contact_Repository(Application application){
        Contact_Database database = Contact_Database.getInstance(application);
        contact_dao = database.contactDao();
        contactList = contact_dao.getAllContact();
    }

    public void insertData(Contact_Entity contact){
        new insertTask(contact_dao).execute(contact);
    }
    public void updateData(Contact_Entity contact){
        new updateTask(contact_dao).execute(contact);
    }
    public void deleteData(Contact_Entity contact){
        new deleteTask(contact_dao).execute(contact);
    }
    public LiveData<List<Contact_Entity>> getContactList(){
        return contactList;
    }


    //========
    private static class insertTask extends AsyncTask<Contact_Entity, Void, Void>{
        Contact_Dao dao;
        public insertTask(Contact_Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Contact_Entity... contactEntities) {
            dao.insertContact(contactEntities[0]);
            return null;
        }
    }

    private static class updateTask extends AsyncTask<Contact_Entity, Void, Void>{
        Contact_Dao dao;

        public updateTask(Contact_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contact_Entity... contactEntities) {
            dao.updateContact(contactEntities[0]);
            return null;
        }
    }

    private static class deleteTask extends AsyncTask<Contact_Entity, Void, Void>{
        Contact_Dao dao;

        public deleteTask(Contact_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contact_Entity... contactEntities) {
            dao.deleteContact(contactEntities[0]);
            return null;
        }
    }

}
