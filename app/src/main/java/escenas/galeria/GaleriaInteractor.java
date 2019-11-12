package escenas.galeria;

import android.util.Log;

import java.util.ArrayList;

import ayudas.Callback;
import escenas.galeria.interfaces.IGaleriaInteractor;
import modelos.ModeloAsset;
import recursos.Assetsrecursos;

public class GaleriaInteractor implements IGaleriaInteractor {
    //Interface IGalleryInteractor
    @Override
    public void subscribeForAssets(final Callback callback){
        Assetsrecursos.shared.subscribe(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<ModeloAsset> assets = (ArrayList<ModeloAsset>) responseObject;
                callback.onSuccess(assets);
            }

            @Override
            public void onError() {
                Log.d("debug", "error");
                callback.onError();
            }
        });
    }
}
