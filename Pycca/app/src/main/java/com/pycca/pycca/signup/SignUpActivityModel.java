package com.pycca.pycca.signup;

import android.support.annotation.NonNull;

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
import com.pycca.pycca.util.Constants;

public class SignUpActivityModel implements SignUpActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    SignUpActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void saveUserAuthentication(String email, String password, String identificationCard, String clubPyccaCardNumber, final SignUpActivityMVP.TaskListener listener, SignUpActivity signUpActivity) {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setIdentificationCard(identificationCard);
        user.setClubPyccaCardNumber(clubPyccaCardNumber);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signUpActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserFirestore(user, listener);
                        }
                        else{
                            int errorCode;
                            if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                errorCode = R.string.error_password_least_six_characters;
                            }
                            else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                errorCode = R.string.error_email_already_use;
                            }
                            else {
                                errorCode = R.string.error_create_user;
                            }
                            listener.onError(errorCode);
                        }
                    }
                });
    }

    private void saveUserFirestore(User user, final SignUpActivityMVP.TaskListener listener) {
        firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail()).set(user.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onError(R.string.error_create_user);
                    }
                });

    }
}
