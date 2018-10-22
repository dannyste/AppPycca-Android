package com.pycca.pycca.forgotpassword;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pycca.pycca.R;

public class ForgotPasswordActivityModel implements ForgotPasswordActivityMVP.Model {

    private FirebaseAuth mAuth;

    public ForgotPasswordActivityModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void resetPasswordAuthentication(String email, final ForgotPasswordActivityMVP.TaskListener listener) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            listener.onSuccess();
                        }else {
                            listener.onError(R.string.error_reset_password);
                        }
                    }
                });
    }
}
