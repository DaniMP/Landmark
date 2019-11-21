package com.example.landmark.escenas.terminos;

import com.example.landmark.ayudas.Callback;
import com.example.landmark.escenas.terminos.interfaces.ITerminosActivity;
import com.example.landmark.escenas.terminos.interfaces.ITerminosPresenter;
import com.example.landmark.recursos.Sesionrecursos;

public class TerminosPresenter implements ITerminosPresenter {

    //MVP Variables
    private ITerminosActivity view;

    public TerminosPresenter(ITerminosActivity view){
        this.view = view;
    }

    //Interface ITermsPresenter
    @Override
    public void privateButtonPressed() {
        this.view.showSpinner();
        Sesionrecursos.shared.SignIn(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                TerminosPresenter.this.view.navigateToPrivate();
                TerminosPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError() {
                TerminosPresenter.this.view.hideSpinner();
                TerminosPresenter.this.view.showError("Can't create a valid firebase user");
            }
        });

    }
}
