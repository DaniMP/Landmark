package com.example.landmark.escenas.gallery.interfaces;

import android.graphics.Bitmap;

import com.example.landmark.ayudas.Callback;

public interface IGalleryInteractor {

    void onCameraSelected(Callback callback);
    void onGallerySelected(Callback callback);

    void tagImageWithFirebase(Bitmap imag, Callback callback);
}
