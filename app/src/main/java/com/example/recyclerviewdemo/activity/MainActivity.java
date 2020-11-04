package com.example.recyclerviewdemo.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.adapter.PersonAdapter;
import com.example.recyclerviewdemo.api.ApiPersonService;
import com.example.recyclerviewdemo.database.PersonDataBase;
import com.example.recyclerviewdemo.model.CompositePerson;
import com.example.recyclerviewdemo.model.IPerson;
import com.example.recyclerviewdemo.model.PersonAddress;
import com.example.recyclerviewdemo.model.PersonPhone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<IPerson> persons = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnClear = findViewById(R.id.btnClearList);
        btnClear.setOnClickListener(view -> {
            persons.clear();
            recyclerView.getAdapter().notifyDataSetChanged();
        });

        Button btnRead = findViewById(R.id.btnReadFromDB);
        btnRead.setOnClickListener(view -> new Thread(() -> {
            List<PersonAddress> personAddresses = PersonDataBase.getDB(getApplicationContext()).personDAO().getPersonAddressFromBD();
            persons.addAll(personAddresses);
            List<PersonPhone> personPhones = PersonDataBase.getDB(getApplicationContext()).personDAO().getPersonPhoneFromBD();
            persons.addAll(personPhones);

            runOnUiThread(() -> recyclerView.getAdapter().notifyDataSetChanged());
        }).start());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PersonAdapter(persons));

        intData();
    }

    private void intData() {
        new Thread(() -> {
            List<CompositePerson> compositePersons = ApiPersonService.get().getPersons(null);
            for (CompositePerson compositePerson : compositePersons) {
                persons.add(compositePerson.toIPerson());
            }

//            PersonDataBase.getDB(getApplicationContext()).clearAllTables();
            for (IPerson iPerson : persons){
                switch (iPerson.getType()){
                    case 1:
                        PersonDataBase.getDB(getApplicationContext()).personDAO().insertPersonPhone((PersonPhone)iPerson);
                        break;
                    case 2:
                        PersonDataBase.getDB(getApplicationContext()).personDAO().insertPersonAddress((PersonAddress) iPerson);
                        break;
                }
            }

            runOnUiThread(() -> recyclerView.getAdapter().notifyDataSetChanged());
        }).start();

//        ApiPersonService.get().getPersons(new Callback<List<CompositePerson>>() {
//            @Override
//            public void onResponse(Call<List<CompositePerson>> call, Response<List<CompositePerson>> response) {
//                Log.e("response","good!!!!!");
//                if (response.isSuccessful()){
//                    if (response.body() != null) {
//                        for (CompositePerson compositePerson : response.body()) {
//                            persons.add(compositePerson.toIPerson());
//                        }
//                    }
//                    recyclerView.getAdapter().notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CompositePerson>> call, Throwable t) {
//                Log.e("response",t.getMessage());
//                t.printStackTrace();
//            }
//        });

//        persons.add(new Person("name1", "112233"));
//        persons.add(new Person("name2", "445566"));
//        persons.add(new PersonAddress("12334", "dfdfd"));
//        persons.add(new Person("name3", "445322"));
//        persons.add(new Person("name4", "00reg3uy"));
//        persons.add(new Person("name5", "dsfgjdsf3"));
//        persons.add(new PersonAddress("kjfjd", "dfdfd"));
    }
}