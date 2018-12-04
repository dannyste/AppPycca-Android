package com.pycca.pycca.multilogin;

import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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

public class MultiLoginActivityModel implements MultiLoginActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseInstanceId firebaseInstanceId;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseMessaging firebaseMessaging;

    MultiLoginActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    @Override
    public void firebaseAuthWithFacebook(final MultiLoginActivity multiLoginActivity, AccessToken accessToken, final String nativePhoneNumber, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            User user = new User();
                            user.setRegistrationProvider(Util.RegistrationProvider.GOOGLE.toString());
                            user.setPhotoUrl(firebaseUser.getPhotoUrl().toString());
                            user.setName(firebaseUser.getDisplayName());
                            user.setEmail(firebaseUser.getEmail());
                            user.setAccountPhoneNumber(firebaseUser.getPhoneNumber());
                            user.setNativePhoneNumber(nativePhoneNumber);
                            user.setCreationDate(new Date());
                            user.setModificationDate(new Date());
                            getToken(multiLoginActivity, user, taskListener);
                        }
                        else {
                            taskListener.onError(R.string.error_default);
                        }
                    }
                });
    }

    @Override
    public void firebaseAuthWithGoogle(final MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, final String nativePhoneNumber, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            User user = new User();
                            user.setRegistrationProvider(Util.RegistrationProvider.GOOGLE.toString());
                            user.setPhotoUrl(firebaseUser.getPhotoUrl().toString());
                            user.setName(firebaseUser.getDisplayName());
                            user.setEmail(firebaseUser.getEmail());
                            user.setAccountPhoneNumber(firebaseUser.getPhoneNumber());
                            user.setNativePhoneNumber(nativePhoneNumber);
                            user.setCreationDate(new Date());
                            user.setModificationDate(new Date());
                            getToken(multiLoginActivity, user, taskListener);
                        }
                        else {
                            taskListener.onError(R.string.error_default);
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

    private void getToken(final MultiLoginActivity multiLoginActivity, final User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        firebaseInstanceId.getInstanceId().addOnSuccessListener(multiLoginActivity,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                user.setToken(instanceIdResult.getToken());
                getUserFirebaseFirestore(multiLoginActivity, user, taskListener);
            }
        });
    }

    private void getUserFirebaseFirestore(final MultiLoginActivity multiLoginActivity, final User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                User userFirebaseFirestore = documentSnapshot.toObject(User.class);
                                userFirebaseFirestore.setRegistrationProvider(user.getRegistrationProvider());
                                userFirebaseFirestore.setPhotoUrl(user.getPhotoUrl());
                                userFirebaseFirestore.setName(user.getName());
                                userFirebaseFirestore.setEmail(user.getEmail());
                                userFirebaseFirestore.setAccountPhoneNumber(user.getAccountPhoneNumber());
                                userFirebaseFirestore.setNativePhoneNumber(user.getNativePhoneNumber());
                                userFirebaseFirestore.setToken(user.getToken());
                                userFirebaseFirestore.setModificationDate(user.getModificationDate());
                                updateUserFirebaseFirestore(multiLoginActivity, userFirebaseFirestore, taskListener);
                            }
                            else {
                                createUserFirebaseFirestore(multiLoginActivity, user, taskListener);
                            }
                        }
                        else {
                            taskListener.onError(R.string.error_default);
                        }
                    }
                });
    }

    private void createUserFirebaseFirestore(final MultiLoginActivity multiLoginActivity, final User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail()).set(user.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesManager.getInstance(multiLoginActivity).setUser(user);
                        taskListener.onSuccess(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        taskListener.onError(R.string.error_default);
                    }
                });
    }

    private void updateUserFirebaseFirestore(final MultiLoginActivity multiLoginActivity, final User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.update(
                    "registrationProvider", user.getRegistrationProvider(),
                    "photoUrl", user.getPhotoUrl(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "accountPhoneNumber", user.getAccountPhoneNumber(),
                    "nativePhoneNumber", user.getNativePhoneNumber(),
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
