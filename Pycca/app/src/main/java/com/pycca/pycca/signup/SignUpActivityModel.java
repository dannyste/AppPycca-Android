package com.pycca.pycca.signup;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.RestApiConstants;
import com.pycca.pycca.util.Constants;

import java.util.concurrent.Executor;

public class SignUpActivityModel implements SignUpActivityMVP.Model {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    SignUpActivityModel() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void saveUserAuthentication(String email, String password, String identification, String card_number, final SignUpActivityMVP.TaskListener listener, SignUpActivity signUpActivity) {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setIdentification(identification);
        user.setCard_number(card_number);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signUpActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserFirestore(user, listener);
                        }
                        else{
                            int errorCode;
                            if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                errorCode = R.string.error_password_weak;
                            }
                            else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                errorCode = R.string.error_email_exist;
                            }
                            else {
                                errorCode = R.string.error_create_new_user;
                            }
                            listener.onError(errorCode);
                        }
                    }
                });
    }

    @Override
    public void saveUserFirestore(User user, final SignUpActivityMVP.TaskListener listener) {
        db.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail())
                .set(user.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.onSucess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onError(R.string.error_create_new_user);
                    }
                });

    }
}
