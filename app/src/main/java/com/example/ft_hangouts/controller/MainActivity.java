package com.example.ft_hangouts.controller;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;


import com.example.ft_hangouts.model.DBContacts;
import com.example.ft_hangouts.DetailContacts;
import com.example.ft_hangouts.ListAdapter;
import com.example.ft_hangouts.ListContacts;
import com.example.ft_hangouts.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView obj;
    private DBContacts mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configuting Toolbar
        this.configureToolbar();

        mydb = new DBContacts(this);
        List<ListContacts> listContacts = mydb.getAllContacts();

        obj = findViewById(R.id.listView1);
        obj.setAdapter(new ListAdapter(this, listContacts));


        ImageButton imgbut = findViewById(R.id.addButton);

        imgbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), DetailContacts.class);

                intent.putExtras(dataBundle);
                startActivity(intent);

            }
        });

        obj.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", (int)id);

                Intent intent = new Intent(getApplicationContext(),DetailContacts.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        Toolbar toolbar = findViewById(R.id.toolbar);

        switch (item.getItemId())
        {

            //ColorDrawable colorDrawable = new ColorDrawable(R.color.colorPrimaryDark);
            //toolbar.setBackground(colorDrawable);

            case R.id.blue:
                //setSupportActionBar(toolbar);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                break;

            case R.id.red:
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
                break;

            case R.id.green:
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}