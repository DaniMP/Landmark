package escenas.terminos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landmark.R;

import escenas.galeria.GaleriaActivity;
import escenas.terminos.interfaces.ITerminosActivity;

public class TerminosActivity extends AppCompatActivity implements ITerminosActivity {

    //MVP Variables
    private TerminosPresenter presenter;

    //View outlets
    private Button privateButton;
    //Setting the UI
    private ProgressBar spinner;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);

        //init the UI
        this.privateButton = this.findViewById(R.id.termsAcceptsButton);
        this.spinner = (ProgressBar)findViewById(R.id.progressBar);

        this.hideSpinner();

        //Init the presenter
        this.presenter = new TerminosPresenter(this);

        //Setup events
        this.privateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerminosActivity.this.presenter.privateButtonPressed();
            }
        });
    }

    //Interface ITermsActivity
    @Override
    public void navigateToPrivate() {
        TerminosActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TerminosActivity.this, GaleriaActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                TerminosActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void showSpinner() {
        TerminosActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TerminosActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideSpinner() {
        TerminosActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TerminosActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showError(final String error) {
        TerminosActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TerminosActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
