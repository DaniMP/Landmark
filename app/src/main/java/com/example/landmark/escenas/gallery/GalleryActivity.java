package com.example.landmark.escenas.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landmark.R;
import com.example.landmark.escenas.gallery.interfaces.IGalleryActivity;
import com.example.landmark.recursos.Sesionrecursos;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;

public class GalleryActivity extends AppCompatActivity implements IGalleryActivity {

    private Button buttonGal, buttonCam;

    //MVP Variables
    private GalleryPresenter presenter;
    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        /*Setup events listeners to call onCameraSelected or onGallerySelected*/

        //Camera setup listener
        this.buttonCam.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);

                }
            }
        });

        //Gallery setup listener
        this.buttonGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent.createChooser(galleryIntent,"Select Picture") , 0 );
            }

            public void buildCloudVisionOptions() {
                // [START ml_build_cloud_vision_options]
                FirebaseVisionCloudDetectorOptions options =
                        new FirebaseVisionCloudDetectorOptions.Builder()
                                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                                .setMaxResults(15)
                                .build();
                // [END ml_build_cloud_vision_options]
            }
        });

    }

    @Override
    public void onCameraSelected() {
        this.presenter.onCameraSelected();
    }

    @Override
    public void onGallerySelected() {
        this.presenter.onGallerySelected();
    }

    @Override
    public void showDetail() {
        /*Navigate to a detail activity*/
        this.showDetail();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}
