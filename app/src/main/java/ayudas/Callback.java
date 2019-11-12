package ayudas;

public interface Callback <Object>{
    void onSuccess(Object responseObject);
    void onError();
}
