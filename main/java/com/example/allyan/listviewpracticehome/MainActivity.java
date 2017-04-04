package com.example.allyan.listviewpracticehome;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static ArrayList<Person> arrUser;
    private ListView listView;
    private NamesAdapter adapter;

    final private int EDITOR_ACTIVITY_REQUEST_CODE = 354;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        arrUser = new ArrayList<Person>();
        listView = (ListView) findViewById(R.id.activity_main_list_view);
        listView.setItemsCanFocus(false);

        listView.setClickable(true);
        listView.setOnItemClickListener(this);


    }

    public void main_activity_add_button(View view) {
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EDITOR_ACTIVITY_REQUEST_CODE){
            boolean working = data.getBooleanExtra("working", false);
            Bitmap photo = data.getParcelableExtra("photo");

            String name = data.getStringExtra("name");
            String age = data.getStringExtra("age");
            if(resultCode == RESULT_OK && !working){
                adapter = new NamesAdapter(this, arrUser);
                arrUser.add(new Person(name, age));
                if(photo != null){
                    arrUser.get(arrUser.size()-1).setImageBitmap(photo);
                }
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
            else if(working){
                int position = data.getIntExtra("position", -1);
                if(photo != null){
                    arrUser.get(arrUser.size()-1).setImageBitmap(photo);
                }
                adapter = new NamesAdapter(this, arrUser);
                arrUser.get(position).setName(name);
                arrUser.get(position).setAge(age);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
            else if(resultCode == RESULT_CANCELED){
                System.out.println("ddddddd");
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra("position", position + "");
        startActivityForResult(intent, EDITOR_ACTIVITY_REQUEST_CODE);
    }

}
