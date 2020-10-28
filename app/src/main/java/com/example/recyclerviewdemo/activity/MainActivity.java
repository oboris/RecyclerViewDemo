package com.example.recyclerviewdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.recyclerviewdemo.adapter.PersonAdapter;
import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.api.ApiPersonService;
import com.example.recyclerviewdemo.model.CompositePerson;
import com.example.recyclerviewdemo.model.IPerson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final List<IPerson> persons = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.btnRefresh);
        button.setOnClickListener(view -> recyclerView.getAdapter().notifyDataSetChanged());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PersonAdapter(persons));

        intData();
    }

    private void intData() {

//        new Loader().execute();

//        new Thread(() -> {
//            List<CompositePerson> compositePersons = ApiPersonService.get().getPersons(null);
//            for (CompositePerson compositePerson : compositePersons) {
//                persons.add(compositePerson.toIPerson());
//            }
//        }).start();


        ApiPersonService.get().getPersons(new Callback<List<CompositePerson>>() {
            @Override
            public void onResponse(Call<List<CompositePerson>> call, Response<List<CompositePerson>> response) {
                Log.e("response","good!!!!!");
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        for (CompositePerson compositePerson : response.body()) {
                            persons.add(compositePerson.toIPerson());
                        }
                    }
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CompositePerson>> call, Throwable t) {
                Log.e("response",t.getMessage());
                t.printStackTrace();
            }
        });

//        persons.add(new Person("name1", "112233"));
//        persons.add(new Person("name2", "445566"));
//        persons.add(new PersonAddress("12334", "dfdfd"));
//        persons.add(new Person("name3", "445322"));
//        persons.add(new Person("name4", "00reg3uy"));
//        persons.add(new Person("name5", "dsfgjdsf3"));
//        persons.add(new PersonAddress("kjfjd", "dfdfd"));
    }

    private class Loader extends  AsyncTask <Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            List<CompositePerson> compositePersons = ApiPersonService.get().getPersons(null);
            for (CompositePerson compositePerson : compositePersons) {
                persons.add(compositePerson.toIPerson());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}