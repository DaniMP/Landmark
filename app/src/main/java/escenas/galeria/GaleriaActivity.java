package escenas.galeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import com.example.landmark.R;

import androidx.appcompat.app.AppCompatActivity;

import escenas.galeria.interfaces.IGaleriaActivity;

public class GaleriaActivity extends AppCompatActivity implements IGaleriaActivity {

    //MVP Variables
    private GaleriaPresenter presenter;

    //Setting the UI
    private ProgressBar spinner;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_actiivity);

        //init the UI
        this.spinner = (ProgressBar)findViewById(R.id.progressBarGallery);
        //Init the presenter
        this.presenter = new GaleriaPresenter(this);


        //Call the preseenter to subscribe for assets
        this.presenter.subscribeForAssets();
    }

    //Interface IGalleryActivity
    @Override
    public void navigateToDetail(final AssetModel assetModel){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(GalleryActivity.this, DetailActivity.class);
                //Adding the ID of the model as a parameter
                myIntent.putExtra(DetailActivity.CONSTANT_ID_ASSET, assetModel.id);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public void setAdapterForGrid() {
        //Setup the presenter as Adapter for the GridView when the presenter is ready
        ((GridView) findViewById(R.id.galleryGrid)).setAdapter(this.presenter);
    }

    @Override
    public void showSpinner() {
        GalleryActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GalleryActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideSpinner() {
        GalleryActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GalleryActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }
}