package com.example.landmark.escenas.gallery;

import android.graphics.Bitmap;
import android.telecom.Call;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.gallery.interfaces.IGalleryActivity;
import com.example.landmark.escenas.gallery.interfaces.IGalleryInteractor;
import com.example.landmark.escenas.gallery.interfaces.IGalleryPresenter;
import com.example.landmark.models.LandMarkModel;
import com.google.android.gms.vision.face.Landmark;

public class GalleryPresenter implements IGalleryPresenter {

    private IGalleryActivity view;
    private IGalleryInteractor interactor;

    public GalleryPresenter(IGalleryActivity view){
        this.view = view;
        this.interactor = new GalleryInteractor();
    }

    @Override
    public void onCameraSelected() {
        this.interactor.onCameraSelected(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                //from response object should be a Bitmap
                Bitmap bm = (Bitmap) responseObject;
                GalleryPresenter.this.fromImageToLandmark(bm);
            }

            @Override
            public void onError() {
                GalleryPresenter.this.view.showError("Cannot get image from Camera");
            }
        });
    }

    @Override
    public void onGallerySelected() {
        this.interactor.onGallerySelected(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
               //from response object should be a Bitmap
                Bitmap bm = (Bitmap) responseObject;
                GalleryPresenter.this.fromImageToLandmark(bm);

            }

            @Override
            public void onError() {
                GalleryPresenter.this.view.showError("Cannot get image from Gallery");
            }
        });
    }

    private void fromImageToLandmark(Bitmap image){
        GalleryPresenter.this.interactor.tagImageWithFirebase(image, new Callback() {
            @Override
            public void onSuccess(Object responseObject) {

                //do something with the responseObject
                GalleryPresenter.this.view.showDetail();
            }

            @Override
            public void onError() {
                GalleryPresenter.this.view.showError("Cannot tag image with Firebase");
            }
        });
    }
}
