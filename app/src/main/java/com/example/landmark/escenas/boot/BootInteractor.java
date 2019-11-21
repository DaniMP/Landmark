package com.example.landmark.escenas.boot;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.boot.interfaces.IBootInteractor;
import com.example.landmark.recursos.Sesionrecursos;

public class BootInteractor implements IBootInteractor {
    //Interface IBootInteractor
    @Override
    public void isUserLoggedIn(Callback callback) {

        if (Sesionrecursos.shared.isUserLogedIn()) {
            callback.onSuccess(null);
        }else {
            callback.onError();
        }
    }
}
