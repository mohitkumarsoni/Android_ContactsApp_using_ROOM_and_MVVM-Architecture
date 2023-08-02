package com.example.contactsmvvm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contactsmvvm.Contact_ViewModel;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Dao;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Database;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Entity;
import com.example.contactsmvvm.R;
import com.example.contactsmvvm.databinding.ActivityContactDetailBinding;

import java.util.List;

public class Contact_Detail_Activity extends AppCompatActivity {


    private ActivityContactDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        binding = ActivityContactDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Contact detail");


        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        String note = getIntent().getStringExtra("note");

        binding.name.setText(name);
        binding.phone.setText(phone);
        binding.email.setText(email);
        binding.note.setText(note);

    }
}