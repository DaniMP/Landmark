package escenas.galeria.interfaces;

import modelos.ModeloAsset;

public interface IGaleriaActivity {
    void navigateToDetail(ModeloAsset assetModel);
    void setAdapterForGrid();
    void showSpinner();
    void hideSpinner();
}
