package com.mobile_computing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ResultDisplayActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    private ImageLoader mImageLoader;
    private TextView textView;
    private TextView booktitle;
    private TextView bookdate;
    private CheckBox checkbox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean checkforFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar();
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        String date = intent.getStringExtra("date");
        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        final String viewId = intent.getStringExtra("id");
        setTitle("Book:" + title);
        NetworkImageView image = (NetworkImageView)findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        booktitle = (TextView) findViewById(R.id.bookTitle);
        bookdate = (TextView) findViewById(R.id.bookDsate);
        mImageLoader = VolleySingleton.getInstance(this).getImageLoader();
        mImageLoader.get(imageUrl, ImageLoader.getImageListener(image,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        image.setImageUrl(imageUrl, mImageLoader);
        textView.setText(text);
        booktitle.setText(title);
        bookdate.setText(date);
        checkbox = (CheckBox)findViewById(R.id.starButton);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(viewId))
        {
            checkbox.setChecked(true);
        }
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                   //Log.d("MobileComputing", "onCreate: shared preference--  "+ viewId);
                                                   editor = sharedPreferences.edit();

                                                   if(isChecked)
                                                   {
                                                       editor.putBoolean(viewId,true);
                                                   }
                                                   else
                                                   {
                                                       editor.remove(viewId);
                                                   }
                                                   editor.commit();
                                               }
                                           }
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
