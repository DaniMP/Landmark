package com.example.landmark.scenes.main;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.landmark.LandMarkModel;
import com.example.landmark.ayudas.Callback;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

interface  MainPresenterInterface {
    void showGallery();
    void askForStoragePermission();
    boolean checkPermission();

    void processGalleryImage(int requestCode, int resultCode, Intent data);
    Bitmap bitmapFromData(Intent data);
}

public class MainPresenter implements  MainPresenterInterface {

    private MainActivity view;
    private MainInteractor interactor;

    public MainPresenter(MainActivity view){
        this.view = view;
        this.interactor = new MainInteractor(this);
    }

    @Override
    public void showGallery() {
        //this will do the stuff
        if (this.interactor.isStoragePermissionGranted()) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            this.view.startActivityForResult(galleryIntent , 0 );
        }
        //and update the view
    }

    @Override
    public void askForStoragePermission() {
        ActivityCompat.requestPermissions(this.view,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
    }

    @Override
    public boolean checkPermission() {
        return this.view.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void processGalleryImage(int requestCode, int resultCode, Intent data) {

        Bitmap bitmap = this.bitmapFromData(data);

        if( bitmap != null ) {

            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
            this.interactor.recognizeLandmarksCloud(image, new Callback() {
                @Override
                public void onSuccess(Object responseObject) {
                    LandMarkModel model = (LandMarkModel) responseObject;



                    MainPresenter.this.view.image.setImageBitmap(bitmap);
                    MainPresenter.this.view.image.setVisibility(View.VISIBLE);

                    MainPresenter.this.view.name.setText(model.name);
                    MainPresenter.this.view.name.setVisibility(View.VISIBLE);

                    MainPresenter.this.view.desc.setText(model.description);
                    MainPresenter.this.view.desc.setVisibility(View.VISIBLE);

                }

                @Override
                public void onError() {
                    Toast.makeText(MainPresenter.this.view, "Error!", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public Bitmap bitmapFromData(Intent data) {

        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = this.view.getContentResolver().query(selectedImage, filePathColumn, null, null, null);


        if (cursor == null || cursor.getCount() < 1) {
            // no cursor or no record. DO YOUR ERROR HANDLING
            return null;
        }

        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

        if(columnIndex < 0) {
            // no column index
            return null;
        }



        String picturePath = cursor.getString(columnIndex);
        cursor.close(); // close cursor

        Bitmap bitmap = BitmapFactory.decodeFile(picturePath.toString());

        return bitmap;
    }
}
