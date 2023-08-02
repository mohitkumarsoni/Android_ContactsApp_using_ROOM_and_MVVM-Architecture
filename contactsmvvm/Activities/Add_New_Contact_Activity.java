package com.example.contactsmvvm.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactsmvvm.R;
import com.example.contactsmvvm.databinding.ActivityAddNewContactBinding;

public class Add_New_Contact_Activity extends AppCompatActivity {
    ActivityAddNewContactBinding binding;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        binding = ActivityAddNewContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //=========
        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
            setTitle("Update Contact");

            binding.nameEt.setText(getIntent().getStringExtra("name"));
            binding.phoneEt.setText(getIntent().getStringExtra("phone"));
            binding.emailEt.setText(getIntent().getStringExtra("email"));
            binding.notesEt.setText(getIntent().getStringExtra("note"));
            int id = getIntent().getIntExtra("id",0);

            binding.saveFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = binding.nameEt.getText().toString();
                    String phone = binding.phoneEt.getText().toString();
                    String email = binding.emailEt.getText().toString();
                    String note = binding.notesEt.getText().toString();

                    if (!name.isEmpty() && !phone.isEmpty()){
                        Intent intent = new Intent();
                        intent.putExtra("name", name );
                        intent.putExtra("phone", phone);
                        intent.putExtra("email", email);
                        intent.putExtra("note", note);
                        intent.putExtra("id",id);
                        setResult(RESULT_OK, intent);
                        finish();
                    }else {
                        Toast.makeText(Add_New_Contact_Activity.this, "name & phone can't be empty..!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else {
            setTitle("Add Contact");
            binding.saveFab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    String name = binding.nameEt.getText().toString();
                    String phone = binding.phoneEt.getText().toString();
                    String email = binding.emailEt.getText().toString();
                    String note = binding.notesEt.getText().toString();

                    if (!name.isEmpty() && !phone.isEmpty()) {
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("phone", phone);
                        intent.putExtra("email", email);
                        intent.putExtra("note", note);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(Add_New_Contact_Activity.this, "name & phone can't be empty..!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}