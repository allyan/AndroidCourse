package com.example.allyan.sqlitegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText timerLengthET;
    private EditText hallOffFameResults;
    private Button saveBtn;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private String timerLength = "TimerLength";
    private String HallOfFameResults = "Results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        timerLengthET = (EditText) findViewById(R.id.activity_settings_timer_length);
        hallOffFameResults = (EditText) findViewById(R.id.activity_settings_HoF_Results);
        saveBtn = (Button) findViewById(R.id.activity_settings_save_btn);
        saveBtn.setOnClickListener(this);

        settings = getSharedPreferences(getString(R.string.shared_prefs_file_name), MODE_PRIVATE);
        editor = settings.edit();

        timerLengthET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(timerLengthET.getText().toString().equals("0")){
                    timerLengthET.setText("");
                    timerLengthET.setError("Can`t be zero");
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View view) {
        if(timerLengthET.getText().toString().isEmpty()){
            timerLengthET.setError("Set Timer");
            return;
        }
        if(hallOffFameResults.getText().toString().isEmpty()){
            hallOffFameResults.setError("Set # of Results");
            return;
        }
        if(!hallOffFameResults.getText().toString().isEmpty() && !timerLengthET.getText().toString().isEmpty()){
            editor.putString(timerLength, timerLengthET.getText().toString());
            editor.putString(HallOfFameResults, hallOffFameResults.getText().toString());
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{

        }

    }
}
