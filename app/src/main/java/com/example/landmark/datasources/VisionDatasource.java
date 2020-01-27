package com.example.landmark.datasources;

import android.graphics.Rect;

import androidx.annotation.NonNull;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.models.LandMarkMockFactory;
import com.example.landmark.models.LandMarkModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionLatLng;

import java.util.List;

public class VisionDatasource {

    private static  Boolean MOCK = true;


    public static void recognizeLandmarksCloud_Mock(FirebaseVisionImage image, Callback callback) {
        callback.onSuccess(LandMarkMockFactory.getRandom());
    }

    public static void recognizeLandmarksCloud(FirebaseVisionImage image, Callback callback) {

        if(MOCK) {
            recognizeLandmarksCloud_Mock(image, callback);
        }else {
            FirebaseVisionCloudDetectorOptions options = new FirebaseVisionCloudDetectorOptions.Builder()
                    .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                    .setMaxResults(30)
                    .build();

            FirebaseVisionCloudLandmarkDetector detector = FirebaseVision.getInstance().getVisionCloudLandmarkDetector();

            Task<List<FirebaseVisionCloudLandmark>> result = detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionCloudLandmark>>() {
                @Override
                public void onSuccess(List<FirebaseVisionCloudLandmark> firebaseVisionCloudLandmarks) {
                    // Task completed successfully
                    // [START_EXCLUDE]
                    // [START get_landmarks_cloud]
                    for (FirebaseVisionCloudLandmark landmark : firebaseVisionCloudLandmarks) {

                        Rect bounds = landmark.getBoundingBox();
                        String landmarkName = landmark.getLandmark();
                        String entityId = landmark.getEntityId();
                        float confidence = landmark.getConfidence();

                        // Multiple locations are possible, e.g., the location of the depicted
                        // landmark and the location the picture was taken.

                        double latitude = 0 ;
                        double longitude = 0;

                        for (FirebaseVisionLatLng loc : landmark.getLocations()) {
                            latitude = loc.getLatitude();
                            longitude = loc.getLongitude();

                        }

                        LandMarkModel model = new LandMarkModel(landmarkName, landmarkName, latitude, longitude);
                        callback.onSuccess(model);

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    callback.onError();
                }
            });
        }
    }
}
