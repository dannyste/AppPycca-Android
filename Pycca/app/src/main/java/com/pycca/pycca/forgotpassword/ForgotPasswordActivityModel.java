package com.pycca.pycca.forgotpassword;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pycca.pycca.R;

public class ForgotPasswordActivityModel implements ForgotPasswordActivityMVP.Model {

    private FirebaseAuth firebaseAuth;

    ForgotPasswordActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void firebaseSendPasswordResetEmail(String email, final ForgotPasswordActivityMVP.TaskListener taskListener) {
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            taskListener.onSuccess();
                        }
                        else {
                            taskListener.onError(R.string.error_default);
                        }
                    }
                });
    }
}
