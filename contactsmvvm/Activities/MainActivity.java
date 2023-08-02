package com.example.contactsmvvm.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.contactsmvvm.Contact_Repository;
import com.example.contactsmvvm.Contact_ViewModel;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Entity;
import com.example.contactsmvvm.R;
import com.example.contactsmvvm.RvAdapter;
import com.example.contactsmvvm.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Contact_ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(Contact_ViewModel.class);

        binding.fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_New_Contact_Activity.class);
                intent.putExtra("type","insert");
                startActivityForResult(intent,1);
            }
        });

        //=======
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);
        RvAdapter adapter = new RvAdapter(this);
        binding.rv.setAdapter(adapter);

        viewModel.getContactList().observe(this, new Observer<List<Contact_Entity>>() {
            @Override
            public void onChanged(List<Contact_Entity> contactEntities) {
                adapter.submitList(contactEntities);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT| ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction== ItemTouchHelper.RIGHT){
                    //delete
                    viewModel.deleteContact(adapter.getPosition(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
                } else if (direction== ItemTouchHelper.LEFT) {
                    //update
                    Intent intent = new Intent(MainActivity.this, Add_New_Contact_Activity.class);
                    intent.putExtra("name", adapter.getPosition(viewHolder.getAdapterPosition()).getName());
                    intent.putExtra("phone", adapter.getPosition(viewHolder.getAdapterPosition()).getPhone());
                    intent.putExtra("email", adapter.getPosition(viewHolder.getAdapterPosition()).getEmail());
                    intent.putExtra("note", adapter.getPosition(viewHolder.getAdapterPosition()).getNotes());
                    intent.putExtra("id", adapter.getPosition(viewHolder.getAdapterPosition()).getId());
                    intent.putExtra("type","update");
                    startActivityForResult(intent, 2);
                }

            }
        }).attachToRecyclerView(binding.rv);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK){

            //insert
            assert data != null;
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String email = data.getStringExtra("email");
            String note = data.getStringExtra("note");
            Contact_Entity contact = new Contact_Entity(name,phone,email,note);
            viewModel.insertContact(contact);
            Toast.makeText(this, "added..!", Toast.LENGTH_SHORT).show();
        } else if (requestCode==2 && resultCode==RESULT_OK) {
            //update
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String email = data.getStringExtra("email");
            String note = data.getStringExtra("note");
            Contact_Entity contact = new Contact_Entity(name,phone,email,note);
            contact.setId(data.getIntExtra("id",0));
            viewModel.updateContact(contact);
            Toast.makeText(this, "updated..!", Toast.LENGTH_SHORT).show();
        }

    }


}