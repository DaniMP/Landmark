package com.example.landmark.escenas.gallery;

import android.graphics.Bitmap;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.gallery.interfaces.IGalleryInteractor;
import com.example.landmark.models.LandMarkModel;

public class GalleryInteractor implements IGalleryInteractor {
    @Override
    public void onCameraSelected(Callback callback) {
        /*
         * GET AN IMAGE FROM CAMERA
         * CALL CALLBACK WITH BITMAP
         *

        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/
    }

    @Override
    public void onGallerySelected(Callback callback) {
        /*
         * GET AN IMAGE FROM GALLERY
         * CALL CALLBACK WITH BITMAP
         *
        Bitmap bm = new Bitmap();
        callback.onSuccess(bm);*/
    }

    @Override
    public void tagImageWithFirebase(Bitmap imag, Callback callback) {
        /*
        * Convert BITMAP to FIREBASE IMAGE
        * CALL FIREBASE
        * GET PROPPERTIES AND CREATE A NEW LANDMARK MODEL WITH THEM
        *
        * */

        LandMarkModel.shared.name = "test";
        callback.onSuccess(LandMarkModel.shared);
    }
}
