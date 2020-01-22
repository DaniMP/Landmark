package com.example.landmark.escenas.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.gallery.interfaces.IGalleryActivity;
import com.example.landmark.escenas.gallery.interfaces.IGalleryInteractor;
import com.example.landmark.models.LandMarkModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionLatLng;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryInteractor implements IGalleryInteractor {
    private IGalleryActivity view;
    private IGalleryInteractor interactor;

    @Override
    public void onCameraSelected(Callback callback) {

        /*
         * GET AN IMAGE FROM CAMERA
         * CALL CALLBACK WITH BITMAP
         *
        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/

        this.interactor.onCameraSelected(new Callback() {
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

        this.interactor.onGallerySelected (new Callback() {
        @Override
        public void onSuccess (Object responseObject){
            //from response object should be a Bitmap
            Bitmap bm = (Bitmap) responseObject;
            callback.onSuccess(bm);
            //GalleryInteractor.this.tagImageWithFirebase(bm, callback);
        }

        @Override
        public void onError () {
            Log.d("debug", "error");
            callback.onError();
        }
        //});
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


        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imag);


        FirebaseVisionCloudDetectorOptions options = new FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(30)
                .build();
        FirebaseVisionCloudLandmarkDetector detector = FirebaseVision.getInstance()
                .getVisionCloudLandmarkDetector();



        Task<List<FirebaseVisionCloudLandmark>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionCloudLandmark>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionCloudLandmark> firebaseVisionCloudLandmarks) {
                        // Task completed successfully
                        // [START_EXCLUDE]
                        // [START get_landmarks_cloud]
                        for (FirebaseVisionCloudLandmark landmark: firebaseVisionCloudLandmarks) {

                            Rect bounds = landmark.getBoundingBox();
                            String landmarkName = landmark.getLandmark();
                            String entityId = landmark.getEntityId();
                            float confidence = landmark.getConfidence();

                            // Multiple locations are possible, e.g., the location of the depicted
                            // landmark and the location the picture was taken.
                            for (FirebaseVisionLatLng loc: landmark.getLocations()) {
                                double latitude = loc.getLatitude();
                                double longitude = loc.getLongitude();
                            }
                        }

                        callback.onSuccess(LandMarkModel.shared);

                        // [END get_landmarks_cloud]
                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...

                        callback.onError();
                    }
                });


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
