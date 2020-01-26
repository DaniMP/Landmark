package com.example.landmark;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import android.Manifest;



import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.example.landmark.BuildConfig;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LANDMARK"
    Button buttonSend, buttonFavs, buttonMaps, buttonTrips, buttonGal, buttonCam;
    EditText userInput;
    private static final String LANDMARK_RECOGNITION = "Landmark Recognition";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonGal = this.findViewById(R.id.gallery);
               // METODO ONCLICK

            buttonGal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()){
                    Intent galleryIntent = Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent , 0 );
                }
            }

        });


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);

    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);


            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);


         if (cursor == null || cursor.getCount() < 1) {
        return; // no cursor or no record. DO YOUR ERROR HANDLING
    }
        // mirar que es esto
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

        if(columnIndex < 0) // no column index
            return; // DO YOUR ERROR HANDLING


        String picturePath = cursor.getString(columnIndex);
        cursor.close(); // close cursor

        Bitmap bitmap = BitmapFactory.decodeFile(picturePath.toString());

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        recognizeLandmarksCloud(image);

    }


    private void recognizeLandmarksCloud(FirebaseVisionImage image) {
        // [START set_detector_options_cloud]
        FirebaseVisionCloudDetectorOptions options = new FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(30)
                .build();
        // [END set_detector_options_cloud]

        // [START get_detector_cloud]
        FirebaseVisionCloudLandmarkDetector detector = FirebaseVision.getInstance()
                .getVisionCloudLandmarkDetector();
        // Or, to change the default settings:
        // FirebaseVisionCloudLandmarkDetector detector = FirebaseVision.getInstance()
        //         .getVisionCloudLandmarkDetector(options);
        // [END get_detector_cloud]

        // [START run_detector_cloud]
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
                            for (FirebaseVisionLatLng loc : landmark.getLocations()) {
                                double latitude = loc.getLatitude();
                                double longitude = loc.getLongitude();
                            }
                        }
                    }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...
                        Log.d(TAG, "onFailure: "+e.toString());
                    }
                });
    }
}
