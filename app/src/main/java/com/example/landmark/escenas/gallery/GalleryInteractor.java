package com.example.landmark.escenas.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.gallery.interfaces.IGalleryInteractor;
import com.example.landmark.models.LandMarkModel;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class GalleryInteractor implements IGalleryInteractor {
    @Override
    public void onCameraSelected(Callback callback) {
        /*
         * GET AN IMAGE FROM CAMERA
         * CALL CALLBACK WITH BITMAP
         *
        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/


        Bitmap bm = new Bitmap(GalleryActivity.this.onCameraSelected());//aqui tengo que poner la uri de la imagen que he hecho path anteriormente);
        callback.onSuccess(bm);
       /* @Override
        public void onSuccess(Object responseObject) {
            //from response object should be a Bitmap
            Bitmap bm = (Bitmap) responseObject;
            GalleryInteractor.this.onCameraSelected(bm);
        }*/
    }

    @Override
    public void onGallerySelected(Callback callback) {
        /*
         * GET AN IMAGE FROM GALLERY
         * CALL CALLBACK WITH BITMAP
         *
        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/

        Uri selectedImage = data.getData();
        Bitmap = MediaStore.Images.Media.getBitmap(this.onGallerySelected(Callback callback));
        imgPath = getRealPathFromURI(selectedImage);
        destination = new File(imgPath.toString());
        imageview.setImageBitmap(bitmap);


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

    } */


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


            // [START image_from_bitmap]
            FirebaseVisionImage imag = FirebaseVisionImage.fromBitmap(imag);
            // [END image_from_bitmap]


            // Pasa el objeto media.Image y la rotación al firebase
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            private void imageFromMediaImage(Image mediaImage, int rotation) {
                // [START image_from_media_image]
                FirebaseVisionImage image = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);
                //[END image_from_media_image]
            }


        FirebaseDatabase.getInstance();



        LandMarkModel.shared.name = "test";
        callback.onSuccess(LandMarkModel.shared);
    }
/*
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    } */
}
