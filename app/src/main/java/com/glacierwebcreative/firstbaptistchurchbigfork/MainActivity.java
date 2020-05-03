package com.glacierwebcreative.firstbaptistchurchbigfork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the View that shows the Home Page
        Button homePage = (Button) findViewById(R.id.membersListButton);

        // Set a click listener on that View
        homePage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent chooseIntent = new Intent(MainActivity.this, MembersListDisplay.class);

                startActivity(chooseIntent);
            }
        });




    }





}
