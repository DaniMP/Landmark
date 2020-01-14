package com.example.landmark.escenas.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.gallery.interfaces.IGalleryInteractor;
import com.example.landmark.models.LandMarkModel;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public abstract class GalleryInteractor implements IGalleryInteractor {
    @Override
    public void onCameraSelected(Callback callback) {

        /*
         * GET AN IMAGE FROM CAMERA
         * CALL CALLBACK WITH BITMAP
         *
        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/

        this.onCameraSelected(new Callback() {
            @Override
            public void onSuccess (Object responseObject){
                //from response object should be a Bitmap
                Bitmap bm = (Bitmap) responseObject;
                GalleryInteractor.this.tagImageWithFirebase(bm, callback);
            }

            @Override
            public void onError () {
                Log.d("debug", "error");
                callback.onError();
            }
        });
    }


       /* Bitmap bm = new Bitmap(GalleryActivity.this.onCameraSelected());//aqui tengo que poner la uri de la imagen que he hecho path anteriormente);
        callback.onSuccess(bm); */
       /* @Override
        public void onSuccess(Object responseObject) {
            //from response object should be a Bitmap
            Bitmap bm = (Bitmap) responseObject;
            GalleryInteractor.this.onCameraSelected(bm);
        }*/


    @Override
    public void onGallerySelected(Callback callback) {
        /*
         * GET AN IMAGE FROM GALLERY
         * CALL CALLBACK WITH BITMAP
         *
        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/



        /*
        Uri selectedImage = data.getData();
        Bitmap = MediaStore.Images.Media.getBitmap(this.onGallerySelected(Callback callback));
        imgPath = getRealPathFromURI(selectedImage);
        destination = new File(imgPath.toString());
        imageview.setImageBitmap(bitmap); */

        this.onGallerySelected(new Callback() {
            @Override
            public void onSuccess (Object responseObject){
                    //from response object should be a Bitmap
                    Bitmap bm = (Bitmap) responseObject;
                    GalleryInteractor.this.tagImageWithFirebase(bm, callback);
                    }

            @Override
            public void onError () {
                    Log.d("debug", "error");
                    callback.onError();
                    }
        });

/*
        @Override
        public void onSuccess(Object responseObject) {
            //from response object should be a Bitmap
            Bitmap bm = (Bitmap) responseObject;
            GalleryInteractor.this.fromImageToLandmark(bm);

        }

        @Override
        public void onError() {
            GalleryInteractor.this.view.showError("Cannot get image from Gallery");
        }
*/
    }


    @Override
    public void tagImageWithFirebase(Bitmap imag, Callback callback) {
        /*
        * Convert BITMAP to FIREBASE IMAGE
        * CALL FIREBASE
        * GET PROPPERTIES AND CREATE A NEW LANDMARK MODEL WITH THEM
        *
        * */
/*
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        mImageLabel.setImageBitmap(imageBitmap);
        encodeBitmapAndSaveToFirebase(imageBitmap); */

        this.tagImageWithFirebase (new Bitmap, new Callback() {
            @Override
            public void onSuccess (Object responseObject){
                // [START image_from_bitmap]
                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imag);
                // [END image_from_bitmap]
            }
            @Override
            public void onError () {
                Log.d("debug", "error");
                callback.onError();
            }
        });
      /*
        private void imageFromBitmap (){
            // [START image_from_bitmap]
            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imag);
            // [END image_from_bitmap]
        }*/

        FirebaseDatabase.getInstance();

        LandMarkModel.shared.name = "test";
        callback.onSuccess(LandMarkModel.shared);
    };
/*
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    } */
}
