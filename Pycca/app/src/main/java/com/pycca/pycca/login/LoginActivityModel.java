package com.pycca.pycca.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.pycca.pycca.R;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private FirebaseAuth firebaseAuth;

    LoginActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void firebaseAuthWithEmailAndPassword(String email, String password, final LoginActivityMVP.TaskListener listener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    listener.onSuccess();
                }
                else {
                    if (task.getException() != null) {
                        int errorMsg = R.string.error_default;
                        if (task.getException() instanceof FirebaseAuthInvalidUserException){
                            errorMsg = R.string.error_user_not_exist;
                        }
                        else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            errorMsg = R.string.error_invalid_credentials;
                        }
                        listener.onError(errorMsg);
                    }
                }
            }
        });
    }
}
