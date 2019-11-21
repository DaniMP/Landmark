package com.example.landmark.recursos;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.example.landmark.ayudas.Callback;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sesionrecursos {
    public static Sesionrecursos shared = new Sesionrecursos();

    public void signOut() { FirebaseAuth.getInstance().signOut();
    }

    public void getCurrentUser(final Callback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        callback.onSuccess(currentUser);
    }

    public  Boolean isUserLogedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void SignIn(final Callback callback) {
        FirebaseAuth.getInstance().signOut();
        //FirebaseAuth.getInstance().signInWithEmailAndPassword("email", "password")
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError();
            }
        });

    }
}
