package com.imrannazir.demo_project;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataShow extends AppCompatActivity {

//    private DBHandler databaseHelper;
//    private RecyclerView recycler;
//    private RecycleAdapter recycleAdapter;
//    List<PersonData> dataModelsList;

    private ListView listView;
    private ArrayList<PersonData> listdatamodel;
    private DataAdapter adapter;
    private String names[];
    private String contacts[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);


        listView = (ListView) findViewById(R.id.listview);
        listdatamodel = new ArrayList<>();

        DBHandler db = new DBHandler(this);


        List<PersonData> allPersonalData = db.getAllPersonalData();

        for (PersonData data : allPersonalData) {
            String log = "Id: " + data.getName() + " ,Name: " + data.getEmail() + " ,Phone: " + data.getPhone();
            PersonData item = new PersonData();
            item.setName(data.getName());
            item.setEmail(data.getEmail());
            item.setPhone(data.getPhone());
            listdatamodel.add(item);
            Log.d("Name: ", log);
        }

        adapter = new DataAdapter(listdatamodel, this);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        listdatamodel.remove(position);
                        adapter.notifyDataSetChanged();
                        return false;
                    }
                });
    }
}

//        dataModelsList = new ArrayList<PersonData>();
//        recycler = (RecyclerView) findViewById(R.id.listview);
//
//
//                databaseHelper = new DBHandler(DataShow.this);
//                dataModelsList = databaseHelper.getAllPersonalData();
//                recycleAdapter = new RecycleAdapter(dataModelsList);
//
//                RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
//                recycler.setLayoutManager(llm);
//                recycler.setItemAnimator(new DefaultItemAnimator());
//                recycler.setAdapter(recycleAdapter);
//        }

//}
