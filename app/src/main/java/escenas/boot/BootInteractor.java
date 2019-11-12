package escenas.boot;

import ayudas.Callback;
import escenas.boot.interfaces.IBootInteractor;
import recursos.Sesionrecursos;

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
