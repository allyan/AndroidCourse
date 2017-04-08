package com.example.allyan.listviewpracticehome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImagePreview extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_image_preview);

        ImageView imageView = (ImageView) findViewById(R.id.activity_preview_image_preview);
        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("Image");
        bitmap.getWidth();

        imageView.setImageBitmap(bitmap);
        imageView.setRotation(-90);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
