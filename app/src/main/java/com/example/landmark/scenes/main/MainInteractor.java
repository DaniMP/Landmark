package com.example.landmark.scenes.main;


import android.os.Build;
import android.util.Log;

import com.example.landmark.VisionDatasource;
import com.example.landmark.ayudas.Callback;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

interface MainInteractorInterface {
    boolean isStoragePermissionGranted();
    void recognizeLandmarksCloud(FirebaseVisionImage image, Callback callback);
}

public class MainInteractor  implements  MainInteractorInterface {

    private static final String TAG = "LANDMARK";
    private MainPresenter presenter;

    public MainInteractor( MainPresenter presenter ) {
        this.presenter = presenter;
    }


    @Override
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.presenter.checkPermission()) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {
                this.presenter.askForStoragePermission();
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void recognizeLandmarksCloud(FirebaseVisionImage image, Callback callback) {
        VisionDatasource.recognizeLandmarksCloud(image,callback);

    }

}
