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
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;

import java.util.Date;

public class MultiLoginActivityModel implements MultiLoginActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    MultiLoginActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public FirebaseUser getCurrentFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void firebaseAuthWithFacebook(MultiLoginActivity multiLoginActivity, AccessToken accessToken, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            getUserFirebaseFirestore(firebaseUser, taskListener);
                        }
                        else {
                            taskListener.onError(R.string.error_login_facebook);
                        }
                    }
                });
    }

    @Override
    public void firebaseAuthWithGoogle(MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            getUserFirebaseFirestore(firebaseUser, taskListener);
                        }
                        else {
                            taskListener.onError(R.string.error_login_google);
                        }
                    }
                });
    }

    private void getUserFirebaseFirestore(final FirebaseUser firebaseUser, final MultiLoginActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(firebaseUser.getEmail());
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                taskListener.onSuccess();
                            }
                            else {
                                User user = new User();
                                user.setPhotoUrl(firebaseUser.getPhotoUrl().toString());
                                user.setName(firebaseUser.getDisplayName());
                                user.setEmail(firebaseUser.getEmail());
                                user.setCreationDate(new Date());
                                createUserFirebaseFirestore(user, taskListener);
                            }
                        }
                        else {
                            taskListener.onError(R.string.error_get_user);
                        }
                    }
                });
    }

    private void createUserFirebaseFirestore(User user, final MultiLoginActivityMVP.TaskListener taskListener) {
        firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail()).set(user.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
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

}
