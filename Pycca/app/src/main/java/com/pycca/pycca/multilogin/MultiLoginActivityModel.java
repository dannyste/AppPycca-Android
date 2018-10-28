package com.pycca.pycca.multilogin;

import android.support.annotation.NonNull;
import android.util.Log;

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
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.Date;

public class MultiLoginActivityModel implements MultiLoginActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseInstanceId firebaseInstanceId;

    MultiLoginActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseInstanceId = FirebaseInstanceId.getInstance();
    }

    @Override
    public FirebaseUser getCurrentFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void firebaseAuthWithFacebook(final MultiLoginActivity multiLoginActivity, AccessToken accessToken, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            User user = new User();
                            user.setPhotoUrl(firebaseUser.getPhotoUrl().toString());
                            user.setName(firebaseUser.getDisplayName());
                            user.setEmail(firebaseUser.getEmail());
                            user.setCreationDate(new Date());
                            getToken(multiLoginActivity, user, taskListener);
                        }
                        else {
                            taskListener.onError(R.string.error_login_facebook);
                        }
                    }
                });
    }

    @Override
    public void firebaseAuthWithGoogle(final MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            User user = new User();
                            user.setPhotoUrl(firebaseUser.getPhotoUrl().toString());
                            user.setName(firebaseUser.getDisplayName());
                            user.setEmail(firebaseUser.getEmail());
                            user.setCreationDate(new Date());
                            getToken(multiLoginActivity, user, taskListener);
                        }
                        else {
                            taskListener.onError(R.string.error_login_google);
                        }
                    }
                });
    }

    private void getToken(final MultiLoginActivity multiLoginActivity, final User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        firebaseInstanceId.getInstanceId().addOnSuccessListener( multiLoginActivity,  new OnSuccessListener<InstanceIdResult>() {
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
                                userFirebaseFirestore.setToken(user.getToken());
                                updateUserFirebaseFirestore(multiLoginActivity, userFirebaseFirestore, taskListener);
                            }
                            else {
                                createUserFirebaseFirestore(multiLoginActivity, user, taskListener);
                            }
                        }
                        else {
                            taskListener.onError(R.string.error_get_user);
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
                        taskListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskListener.onError(R.string.error_create_user);
                    }
                });
    }

    private void updateUserFirebaseFirestore(final MultiLoginActivity multiLoginActivity, final User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.update("token", user.getToken())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesManager.getInstance(multiLoginActivity).setUser(user);
                        taskListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskListener.onError(R.string.error_update_user);
                    }
                });
    }

}
