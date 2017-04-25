package com.example.allyan.sqlitegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    private int counter;
    private EditText gamerNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        counter = intent.getIntExtra("counter", -1);

        TextView numOfClickes = (TextView) findViewById(R.id.activity_end_textView_numOfClickes);
        numOfClickes.setText(counter + "");

        gamerNameET = (EditText) findViewById(R.id.activity_end_editText_name);

        findViewById(R.id.activity_end_button_save).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EndActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(gamerNameET.getText().toString().isEmpty()){
            gamerNameET.setError("Can`t be empty");
        }
        Intent intent = new Intent(EndActivity.this, HallOfFameActivity.class);
//        startActivity(intent);
//        finish();
    }
}
