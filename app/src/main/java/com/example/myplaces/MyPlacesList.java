package com.example.myplaces;

import com.example.myplaces.Models.*;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyPlacesList extends AppCompatActivity {

    static int NEW_PLACE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MyPlacesList.this,EditMyPlaceActivity.class);
                startActivityForResult(i,NEW_PLACE);
            }
        });


        ListView myPlacesList = (ListView) findViewById(R.id.my_places_list);
        myPlacesList.setAdapter(new ArrayAdapter<MyPlace>(this, android.R.layout.simple_list_item_1, MyPlacesData.getInstance().getMyPlaces()));

        myPlacesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle positionBundle = new Bundle();
                positionBundle.putInt("position", i);
                Intent intent= new Intent(MyPlacesList.this, ViewMyPlacesActivity.class);
                intent.putExtras(positionBundle);
                startActivity(intent);


            }
        });

        myPlacesList.setOnCreateContextMenuListener((contextMenu, v, contextMenuInfo) -> {
            AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
            MyPlace place= MyPlacesData.getInstance().getPlace(info.position);
            contextMenu.setHeaderTitle(place.name);
            contextMenu.add(0,1,1,"View place");
            contextMenu.add(0,2,2,"Edit place");
            contextMenu.add(0,3,3,"Delete place");
            contextMenu.add(0,4,4,"Show on map");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_places_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.show_map_item){
            Intent i= new Intent(this, MyPlacesMapsActivity.class);
            //i.putExtra("state", MyPlacesMapsActivity.SHOW_MAP);
            startActivity(i);
        } else if(id==R.id.new_place_item){
            Intent i = new Intent(this, EditMyPlaceActivity.class);
            startActivity(i);
        } else if(id==R.id.about_item){
            Intent i=new Intent(this, About.class);
            startActivity(i);
        } else if(id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Bundle positionBundle = new Bundle();
        positionBundle.putInt("position",info.position);
        Intent i=null;
        if(item.getItemId()==1){
            i=new Intent(this,ViewMyPlacesActivity.class);
            i.putExtras(positionBundle);
            startActivity(i);
        }
        else if(item.getItemId()==2){
            i=new Intent(this,EditMyPlaceActivity.class);
            i.putExtras(positionBundle);
            startActivityForResult(i,1);
        }
        else if(item.getItemId()==3)
        {
            MyPlacesData.getInstance().deletePlace(info.position);
            setList();
        }
        else if(item.getItemId() == 4)
        {
            i=new Intent(this,MyPlacesMapsActivity.class);
            i.putExtra("state", MyPlacesMapsActivity.CENTER_PLACE_ON_MAP);
            MyPlace place = MyPlacesData.getInstance().getPlace(info.position);
            i.putExtra("lat",place.latitude);
            i.putExtra("lon",place.longitude);
            startActivityForResult(i,2);
        }

        return super.onContextItemSelected(item);
    }
    private void setList()
    {
        ListView myPlacesList= (ListView) findViewById(R.id.my_places_list);
        myPlacesList.setAdapter(new ArrayAdapter<MyPlace>(this, android.R.layout.simple_list_item_1,MyPlacesData.getInstance().getMyPlaces()));
    }
}