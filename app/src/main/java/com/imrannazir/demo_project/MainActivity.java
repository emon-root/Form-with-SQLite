package com.imrannazir.demo_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phoneNumber;
    TextView addData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phoneNumber = (EditText)findViewById(R.id.phone);
        addData = (TextView)findViewById(R.id.add_data);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( name.getText().toString().isEmpty() || name.getText().toString().length() < 4){
                    Toast.makeText(MainActivity.this, "Please Enter you valid name.", Toast.LENGTH_LONG).show();
                }else if( email.getText().toString().isEmpty() || name.getText().toString().length() < 5){
                    Toast.makeText(MainActivity.this, "Please Enter you valid email.", Toast.LENGTH_LONG).show();
                }else if( phoneNumber.getText().toString().isEmpty() || phoneNumber.getText().toString().length() <= 10){
                    Toast.makeText(MainActivity.this, "Please Enter you valid phone number.", Toast.LENGTH_LONG).show();
                }else{
                    disclaimer();
                    DBHandler db = new DBHandler(MainActivity.this);
                    db.addPersonData(new PersonData(name.getText().toString(),email.getText().toString(),phoneNumber.getText().toString()));
                }
            }
        });

        findViewById(R.id.view_all_data2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataShow.class));
            }
        });



        findViewById(R.id.view_all_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataShow.class));
            }
        });


    }

    private void disclaimer() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Data Added Successfully.");
        builder.setMessage("To see all data press View.");
        builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name.setText("");
                email.setText("");
                phoneNumber.setText("");
                startActivity(new Intent(MainActivity.this, DataShow.class));
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                name.setText("");
                email.setText("");
                phoneNumber.setText("");
            }
        });
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
