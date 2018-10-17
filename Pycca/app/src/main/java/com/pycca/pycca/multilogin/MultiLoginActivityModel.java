package com.pycca.pycca.multilogin;

import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MultiLoginActivityModel implements MultiLoginActivityMVP.Model {

    private FirebaseAuth firebaseAuth;

    public MultiLoginActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public FirebaseUser getCurrentFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void firebaseAuthWithGoogle(MultiLoginActivity multiLoginActivity, GoogleSignInAccount googleSignInAccount, final MultiLoginActivityMVP.TaskListener taskListener) {
        AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(multiLoginActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    taskListener.onSuccess();
                }
                else {
                    taskListener.onError();
                }
            }
        });
    }

    @Override
    public void firebaseAuthWithFacebook() {

    }

}
