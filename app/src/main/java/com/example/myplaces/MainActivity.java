package com.example.myplaces;

import com.example.myplaces.Models.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myplaces.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, LoginActivity.class);
                         startActivity(i);
            }
        });
        //Button next=findViewById(R.id.button_finish);
        //next.setOnClickListener(new View.OnClickListener(){

        //    @Override
        //    public void onClick(View v) {
        //        Intent i=new Intent(MainActivity.this, LoginActivity.class);
         //       startActivity(i);
         //   }
       // });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    static int NEW_PLACE = 1;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       if(id==R.id.show_map_item){
           Intent i= new Intent(this, MyPlacesMapsActivity.class);
           i.putExtra("state",MyPlacesMapsActivity.SHOW_MAP);
           startActivity(i);
       } else if(id==R.id.new_place_item){
           Toast.makeText(this, "Add Place!", Toast.LENGTH_SHORT).show();
       } else if(id==R.id.my_places_list_item){
           Intent i2=new Intent(this,MyPlacesList.class);
           startActivity(i2);
       } else if(id==R.id.about_item){
           Intent i=new Intent(this, About.class);
           startActivity(i);
       }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== Activity.RESULT_OK){
            ListView myPlacesList = (ListView) findViewById(R.id.my_places_list);
            if(myPlacesList!=null)
            myPlacesList.setAdapter(new ArrayAdapter<MyPlace>(this, android.R.layout.simple_list_item_1, MyPlacesData.getInstance().getMyPlaces()));
        }
    }
}