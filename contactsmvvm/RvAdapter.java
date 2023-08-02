package com.example.contactsmvvm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmvvm.Activities.Contact_Detail_Activity;
import com.example.contactsmvvm.Database_Dao_Entity.Contact_Entity;
import com.example.contactsmvvm.databinding.EachRvBinding;

public class RvAdapter extends ListAdapter<Contact_Entity, RvAdapter.MyViewHolder> {

    Context context;
    public RvAdapter(Context context){
        super(CALLBACK);
        this.context = context;
    }
    private static final DiffUtil.ItemCallback<Contact_Entity> CALLBACK = new DiffUtil.ItemCallback<Contact_Entity>() {
        @Override
        public boolean areItemsTheSame(@NonNull Contact_Entity oldItem, @NonNull Contact_Entity newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Contact_Entity oldItem, @NonNull Contact_Entity newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getPhone().equals(newItem.getPhone()) &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getNotes().equals(newItem.getName()) ;
        }
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact_Entity contact = getItem(position);
        holder.binding.nameTv.setText(contact.getName());
        holder.binding.phoneTv.setText(contact.getPhone());

        holder.binding.mainContactRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Contact_Detail_Activity.class);
                intent.putExtra("id",contact.getId());
                intent.putExtra("name", contact.getName());
                intent.putExtra("phone",contact.getPhone());
                intent.putExtra("email",contact.getEmail());
                intent.putExtra("note",contact.getNotes());
                context.startActivity(intent);
            }
        });

    }

    public Contact_Entity getPosition(int position){
        return getItem(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EachRvBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = EachRvBinding.bind(itemView);

        }
    }

}
