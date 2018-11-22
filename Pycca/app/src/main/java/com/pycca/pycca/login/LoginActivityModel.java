package com.pycca.pycca.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;
import com.pycca.pycca.util.Util;

import java.util.Date;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseInstanceId firebaseInstanceId;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseMessaging firebaseMessaging;

    LoginActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    @Override
    public void firebaseAuthWithEmailAndPassword(final LoginActivity loginActivity, final String email, String password, final LoginActivityMVP.TaskListener taskListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User();
                    user.setEmail(email);
                    user.setModificationDate(new Date());
                    getToken(loginActivity, user, taskListener);
                }
                else {
                    if (task.getException() != null) {
                        int error = R.string.error_default;
                        if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                            error = R.string.error_user_not_exist;
                        }
                        else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            error = R.string.error_invalid_credentials;
                        }
                        taskListener.onError(error);
                    }
                }
            }
        });
    }

    @Override
    public void userSubscribeToTopic(String topic) {
        firebaseMessaging.subscribeToTopic(topic);
    }

    @Override
    public void userUnsubscribeFromTopic(String topic) {
        firebaseMessaging.unsubscribeFromTopic(topic);
    }

    private void getToken(final LoginActivity loginActivity, final User user, final LoginActivityMVP.TaskListener taskListener) {
        firebaseInstanceId.getInstanceId().addOnSuccessListener(loginActivity,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                user.setToken(instanceIdResult.getToken());
                getUserFirebaseFirestore(loginActivity, user, taskListener);
            }
        });
    }

    private void getUserFirebaseFirestore(final LoginActivity loginActivity, final User user, final LoginActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                User userFirebaseFirestore = documentSnapshot.toObject(User.class);
                                userFirebaseFirestore.setToken(user.getToken());
                                userFirebaseFirestore.setModificationDate(user.getModificationDate());
                                updateUserFirebaseFirestore(loginActivity, userFirebaseFirestore, taskListener);
                            }
                            else {
                                taskListener.onError(R.string.error_default);
                            }
                        }
                        else {
                            taskListener.onError(R.string.error_default);
                        }
                    }
                });
    }

    private void updateUserFirebaseFirestore(final LoginActivity multiLoginActivity, final User user, final LoginActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.update(
                "token", user.getToken(),
                "modificationDate", user.getModificationDate()
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesManager.getInstance(multiLoginActivity).setUser(user);
                        taskListener.onSuccess(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskListener.onError(R.string.error_default);
                    }
                });
    }

}
