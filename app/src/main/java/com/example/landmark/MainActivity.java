package com.example.landmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    Button button, button1;
    EditText userInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = this.findViewById(R.id.send);
        userInput = (EditText) findViewById(R.id.editText);



        button1 = this.findViewById(R.id.foto);
/*
        button.setOnClickListener(new View.OnClickListener() {        // METODO ONCLICK

            @Override
            public void onClick(View v) {

                String InputUser = userInput.getText().toString();
                Toast.makeText( MainActivity.this, InputUser , Toast.LENGTH_LONG).show();

                Toast.makeText(MainActivity.this, "Ouh Mama", Toast.LENGTH_LONG).show();
                Intent view = new Intent(MainActivity.this, SecondActivity.class);
                view.setAction(Intent.ACTION_VIEW);
                view.putExtra(SecondActivity.CONSTANT_EXTRA_TEXT,InputUser);
                startActivity(view);
            }

        });
*/
            FirebaseVisionCloudDetectorOptions options =
                    new FirebaseVisionCloudDetectorOptions.Builder()
                            .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                            .setMaxResults(15)
                            .build();


        button.setOnClickListener(new View.OnClickListener() {        // METODO ONCLICK

            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        Button buttonGal = this.findViewById(R.id.gallery);

        buttonGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent , 0 );
            }
        });

/*
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode) {
                case QuestionEntryView.RESULT_GALLERY :
                    if (null != data) {
                        imageUri = data.getData();
                        //Do whatever that you desire here. or leave this blank

                    }
                    break;
                default:
                    break;
            }
        }*/
    }

}
