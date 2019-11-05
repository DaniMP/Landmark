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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    Button button, button1;
    EditText userInput;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = this.findViewById(R.id.send);
        userInput = (EditText) findViewById(R.id.editText);



        button1 = this.findViewById(R.id.foto);

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

        button1.setOnClickListener(new View.OnClickListener() {        // METODO ONCLICK

            @Override
            public void onClick(View v) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }

           // @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");


                    FirebaseStorage storage = FirebaseStorage.getInstance();

                    //Generate reference

                    StorageReference storageRef = storage.getReference();
                    //Bitmap de la camara

                    //guardar en la referencia el bitmap, meta data imagen jpg



                   // Bitmap imageBitmap = (Bitmap) extras.get("data       //Código para guardar la imagen en Firebase

                   // Bitmap imageBitmap = (Bitmap) extras.get("data  ");     //Código para guardar la imagen en Firebase

                }
            }

        });



    }

}
/*
    String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());

    // Creamos una referencia a la carpeta y el nombre de la imagen donde se guardara
    StorageReference mountainImagesRef = storageRef.child("camara/"+timeStamp+".jpga imagen a un array de byte
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();

// Empezamos con la subida a Firebase
        UploadTask uploadTask = mountainImagesRef.putBytes(datas);
        uploadTask.addOnFailureListener(new OnFailureListener() {
@Override
public void onFailure(@NonNull Exception exception) {
        Toast.makeText(getBaseContext(),"Hubo un error",Toast.LENGTH_LONG);
        }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
@Override
public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Toast.makeText(getBaseContext(),"Subida con exito",Toast.LENGTH_LONG);

        }
        }); */