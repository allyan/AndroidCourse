package com.example.allyan.listviewpracticehome;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.name;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class EditorActivity extends AppCompatActivity implements View.OnClickListener {


    static EditText nameET;
    static EditText ageET;
    static ImageView imageView;
    static Button buttonEA;
    Bitmap imageBitmap;
    private boolean working;
    private int position;
    public static int REQUEST_IMAGE_CAPTURE = 14;
    String mCurrentPhotoPath;
    static int counter;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_editor);


        working = false;

        nameET = (EditText) findViewById(R.id.editText_name);
        ageET = (EditText) findViewById(R.id.editText_age);
        imageView = (ImageView) findViewById(R.id.activity_editor_user_photo);
        buttonEA = (Button) findViewById(R.id.editor_button_update);


        Intent intent = getIntent();
        try {

            position = Integer.parseInt(intent.getStringExtra("position"));
            nameET.setText(MainActivity.arrUser.get(position).getName());
            ageET.setText(MainActivity.arrUser.get(position).getAge());
            buttonEA.setText("update");
            if (MainActivity.arrUser.get(position).getImageBitmap() != null) {
                imageView.setOnClickListener(this);
                imageView.setRotation(-90);
                imageBitmap = MainActivity.arrUser.get(position).getImageBitmap();
                imageView.setImageBitmap(MainActivity.arrUser.get(position).getImageBitmap());
            }

            working = true;
        } catch (NumberFormatException e) {
            working = false;
            System.out.println("oooooppppsssss");
        }


    }

    public void editor_update_button(View view) {
        Intent intent = new Intent();
        String name = nameET.getText().toString();
        String age = ageET.getText().toString();
        if (imageBitmap != null) {
            intent.putExtra("photo", imageBitmap);
        }

        if (working) {
            intent.putExtra("working", working);
            intent.putExtra("position", position);
        }
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        super.onBackPressed();
    }

    public void changeImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        setResult(RESULT_CANCELED, takePictureIntent);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && data != null) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setOnClickListener(this);
            imageView.setRotation(-90);
            imageView.setImageBitmap(imageBitmap);

            getOutputMediaFile(MEDIA_TYPE_IMAGE);
        } else {
            Toast.makeText(this, "You don`t get photo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ImagePreview.class);
        intent.putExtra("Image", imageBitmap);
        startActivity(intent);
    }

    public File getOutputMediaFile(int type) {
        String filename = "UserPhotos";
        File file = new File(this.getFilesDir(), filename);
        file.mkdirs();
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(file.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");

//            System.out.println(mediaFile.getAbsolutePath());
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(file.getPath() + File.separator +
                    "VIDEO_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        try {
            FileOutputStream fos = new FileOutputStream(mediaFile);
//            System.out.println(mediaFile.getPath() + " , " + myDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mediaFile;
    }
}
