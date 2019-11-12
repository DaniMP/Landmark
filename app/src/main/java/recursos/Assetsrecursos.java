package recursos;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import ayudas.Callback;
import modelos.ModeloAsset;

public class Assetsrecursos {
    public static Assetsrecursos shared = new Assetsrecursos();

    private ArrayList<ModeloAsset> assetsList = new ArrayList<ModeloAsset>();

    public ModeloAsset getById(String id){

        for (ModeloAsset asset : this.assetsList) {
            if (asset.id.equals(id)) {
                return asset;
            }
        }

        return null;
    }

    public void subscribe(final Callback callback) {
        this.fetch(true, callback);

    }

    public void fetchAll(final Callback callback, final Boolean subscribe) {
        this.fetch(false, callback);

    }

    private void fetch(final  Boolean subscribeCallback, final Callback callback){

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("content/assets");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ModeloAsset> assetsList = new ArrayList<ModeloAsset>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {

                    assetsList.add(snapshotToAssetModel(item_snapshot));
                }

                Assetsrecursos.this.assetsList = assetsList;

                if(!subscribeCallback){
                    databaseReference.removeEventListener(this);
                }

                callback.onSuccess(assetsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError();
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }

    private ModeloAsset snapshotToAssetModel(DataSnapshot item_snapshot) {

        String id = item_snapshot.getKey().toString();

        Integer category = item_snapshot.child("category").exists() ? Integer.parseInt(item_snapshot.child("category").getValue().toString()) : 0;
        String description = item_snapshot.child("description").exists() ? item_snapshot.child("description").getValue().toString() : "";
        String url = item_snapshot.child("url").exists() ? item_snapshot.child("url").getValue().toString() : "";
        String title = item_snapshot.child("title").exists() ? item_snapshot.child("title").getValue().toString() : id;

        return new ModeloAsset(id, url, title, description, category);

    }
}
