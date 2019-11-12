package escenas.boot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landmark.R;

import escenas.boot.interfaces.IBootActivity;

public class BootActivity extends AppCompatActivity implements IBootActivity {
    //MVP Variables
    private BootPresenter presenter;


    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);

        SessionDataSource.shared.signOut();

        //Init the presenter
        this.presenter = new BootPresenter(this);

        //boot the Application
        this.presenter.bootApplication();
    }

    //Interface IBootActivity
    @Override
    public void navigateToPublic() {
        Intent intent = new Intent(BootActivity.this, TermsActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }

    @Override
    public void navigateToPrivate() {
        Intent intent = new Intent(BootActivity.this, GalleryActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }
}
