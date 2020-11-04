package com.example.recyclerviewdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.model.IPerson;
import com.example.recyclerviewdemo.model.PersonPhone;
import com.example.recyclerviewdemo.model.PersonAddress;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<IPerson> persons;
    public PersonAdapter(List<IPerson> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_phone_item, parent, false);
                return new PersonHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_address_item, parent, false);
                return new PersonAddressHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IPerson person = persons.get(position);
        switch (person.getType()){
            case 1:
                ((PersonHolder)holder).tvName.setText(((PersonPhone)person).getName());
                ((PersonHolder)holder).tvPhone.setText(((PersonPhone)person).getPhone());
                break;
            case 2:
                ((PersonAddressHolder)holder).tvName.setText(((PersonAddress)person).getName());
                ((PersonAddressHolder)holder).tvAddress.setText(((PersonAddress)person).getAddress());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public int getItemViewType(int position) {
        return persons.get(position).getType();
    }

    public static class PersonHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvPhone;
        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }

    public static class PersonAddressHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvAddress;
        public PersonAddressHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
