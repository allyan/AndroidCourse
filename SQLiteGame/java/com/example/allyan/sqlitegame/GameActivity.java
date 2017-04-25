package com.example.allyan.sqlitegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startGameBtn;
    private TextView timerTV;
    private CountDownTimer timer;
    private long timerTime;
    private int counter;
    private SharedPreferences settings;
    private String timerLength = "TimerLength";
    private String timerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        settings = getSharedPreferences(getString(R.string.shared_prefs_file_name), MODE_PRIVATE);
        timerTime =  (Integer.parseInt(settings.getString(timerLength, null)))*1000;

        timerTV = (TextView) findViewById(R.id.activity_game_timer);
        timerTV.setText(new SimpleDateFormat("mm:ss:SS").format(new Date(timerTime)));

        startGameBtn = (Button) findViewById(R.id.activity_game_start_btn);
        startGameBtn.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        startGameBtn.setText("CLICK");
        ++counter;
        if (timer == null) {
            timer = new CountDownTimer(timerTime, 10) {
                @Override
                public void onTick(long l) {

                    timerText = new SimpleDateFormat("mm:ss:SS").format(new Date(l));
                    timerTV.setText(timerText);
                }

                @Override
                public void onFinish() {
                    timer.cancel();
                    timer = null;
                    Intent intent = new Intent(GameActivity.this, EndActivity.class);
                    intent.putExtra("counter", counter);
                    startActivity(intent);
                    finish();
                }
            }.start();
        } else {
//            timer.cancel();
//            timer = null;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
