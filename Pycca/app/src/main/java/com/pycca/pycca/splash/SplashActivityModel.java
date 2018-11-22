package com.pycca.pycca.splash;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pycca.pycca.pojo.Parameter;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;

public class SplashActivityModel implements SplashActivityMVP.Model {

    private FirebaseFirestore firebaseFirestore;

    SplashActivityModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void getParameter(SplashActivity splashActivity) {
        getParameterFirebaseFirestore(splashActivity);
    }

    private void getParameterFirebaseFirestore(final SplashActivity splashActivity) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_PARAMETER_TABLE).document(Constants.FIRESTORE_PARAMETER_APP_TABLE);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()) {
                                Parameter parameter = documentSnapshot.toObject(Parameter.class);
                                SharedPreferencesManager.getInstance(splashActivity).setParameter(parameter);
                            }
                            else {
                                Parameter parameter = new Parameter();
                                parameter.setPhoneNumber("");
                                parameter.setEmail("");
                                SharedPreferencesManager.getInstance(splashActivity).setParameter(parameter);
                            }
                        }
                        else {
                            Parameter parameter = new Parameter();
                            parameter.setPhoneNumber("");
                            parameter.setEmail("");
                            SharedPreferencesManager.getInstance(splashActivity).setParameter(parameter);
                        }
                    }
                });
    }

    @Override
    public User getUser(SplashActivity splashActivity) {
        return SharedPreferencesManager.getInstance(splashActivity).getUser();
    }

}
