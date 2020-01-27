package com.example.landmark.scenes.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.landmark.R;

public class MainActivity extends AppCompatActivity {

    MainPresenter presenter;
    ImageView image;
    TextView name;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.image = this.findViewById(R.id.imageView);
        this.name = this.findViewById(R.id.nameView);
        this.desc = this.findViewById(R.id.descriptionView);

        /* GALLERY BUTTON */
        this.findViewById(R.id.gallery).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.this.presenter.showGallery();
            }

        });


        //Presenter
        this.presenter = new MainPresenter(this);
        this.presenter.askForStoragePermission();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.presenter.processGalleryImage(requestCode, resultCode, data);
    }

}
