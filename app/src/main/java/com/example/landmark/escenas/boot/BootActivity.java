package com.example.landmark.escenas.boot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landmark.CameraXActivity;
import com.example.landmark.R;

import com.example.landmark.escenas.boot.interfaces.IBootActivity;
import com.example.landmark.escenas.terminos.TerminosActivity;
import com.example.landmark.recursos.Sesionrecursos;

public class BootActivity extends AppCompatActivity implements IBootActivity {
    //MVP Variables
    private BootPresenter presenter;


    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);

        Sesionrecursos.shared.signOut();

        //Init the presenter
        this.presenter = new BootPresenter(this);

        //boot the Application
        this.presenter.bootApplication();
    }

    //Interface IBootActivity
    @Override
    public void navigateToPublic() {
        Intent intent = new Intent(BootActivity.this, CameraXActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }

    @Override
    public void navigateToPrivate() {
        //Intent intent = new Intent(BootActivity.this, GaleriaActivity.class);
        //intent.setAction(Intent.ACTION_VIEW);
        //BootActivity.this.startActivity(intent);
    }
}
