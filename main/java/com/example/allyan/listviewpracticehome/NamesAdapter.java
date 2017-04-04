package com.example.allyan.listviewpracticehome;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;
import static com.example.allyan.listviewpracticehome.EditorActivity.REQUEST_IMAGE_CAPTURE;

class NamesAdapter extends ArrayAdapter<Person> implements View.OnClickListener {

    private Context context;
    private ArrayList<Person> arrUser;

    public NamesAdapter(Context context, ArrayList<Person> arrUser) {
        super(context, R.layout.list_item, arrUser);
        this.context = context;
        this.arrUser = arrUser;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        } else {

        }
        TextView nameTextView = (TextView) convertView.findViewById(R.id.list_item_name_text_view);
        TextView phoneTextView = (TextView) convertView.findViewById(R.id.list_item_name_age_view);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_user_photo);
        ImageView callButton = (ImageView) convertView.findViewById(R.id.list_item_call_button);
        callButton.setClickable(true);
        callButton.setTag(position);
        callButton.setOnClickListener(this);
        nameTextView.setText(arrUser.get(position).getName());
        phoneTextView.setText(arrUser.get(position).getAge());
        if (arrUser.get(position).getImageBitmap() != null) {
            imageView.setImageBitmap(arrUser.get(position).getImageBitmap());
            imageView.setRotation(-90);
        }
        return convertView;
    }

    @Override
    public void onClick(View view) {
        ImageView callButton = (ImageView) view.findViewById(R.id.list_item_call_button);
        int position = (int) callButton.getTag();
        String phone = arrUser.get(position).getAge();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }
}
