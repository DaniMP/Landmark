package escenas.galeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.landmark.R;

import java.util.ArrayList;

import ayudas.Callback;
import escenas.galeria.interfaces.IGaleriaPresenter;
import modelos.ModeloAsset;

public class GaleriaPresenter extends BaseAdapter implements IGaleriaPresenter {

    //MVP Variables
    private GaleriaActivity view;
    private GaleriaInteractor interactor;

    private ArrayList<ModeloAsset> items = new ArrayList<ModeloAsset>(); //data source of the list adapter


    public GaleriaPresenter(GaleriaActivity view){
        this.view = view;
        this.interactor = new GaleriaInteractor();
    }

    //Interface IGalleryPresenter
    @Override
    public void subscribeForAssets() {
        this.view.showSpinner();
        this.interactor.subscribeForAssets(new Callback() {

            @Override
            public void onSuccess(Object responseObject) {

                ArrayList<ModeloAsset> assets = (ArrayList<ModeloAsset>) responseObject;
                GaleriaPresenter.this.items = assets;
                //Notify the view that the content is ready to be used or updated
                GaleriaPresenter.this.view.setAdapterForGrid();
                GaleriaPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError() {
                //show some error on the UI
            }
        });
    }


    //GridView BaseAdapter
    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public ModeloAsset getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.view.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Dedicated layout for the item itself
            convertView = inflater.inflate(R.layout.activity_gallery_item, parent, false);
        }


        final ModeloAsset asset = getItem(position);
        //Setting the texts
        ((TextView) convertView.findViewById(R.id.description)).setText(asset.description);
        ((TextView) convertView.findViewById(R.id.title)).setText(asset.title);

        //Using Picasso to cache the image
        Picasso.get().load(asset.url).into((ImageView) convertView.findViewById(R.id.imageView));

        //Listener for the click on the item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaPresenter.this.view.navigateToDetail(asset);
            }
        });

        return convertView;
    }
}
