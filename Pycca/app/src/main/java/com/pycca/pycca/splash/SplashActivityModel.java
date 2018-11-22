package com.pycca.pycca.splash;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pycca.pycca.pojo.Parameter;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;

public class SplashActivityModel implements SplashActivityMVP.Model {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseMessaging firebaseMessaging;

    SplashActivityModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    @Override
    public void setParameter(SplashActivity splashActivity, SplashActivityMVP.TaskListener taskListener) {
        getParameterFirebaseFirestore(splashActivity, taskListener);
    }

    @Override
    public void userSubscribeToTopic(String topic) {
        firebaseMessaging.subscribeToTopic(topic);
    }

    @Override
    public void userUnsubscribeFromTopic(String topic) {
        firebaseMessaging.unsubscribeFromTopic(topic);
    }

    private void getParameterFirebaseFirestore(final SplashActivity splashActivity, final SplashActivityMVP.TaskListener taskListener) {
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
                                getUser(splashActivity, taskListener);
                            }
                            else {
                                Parameter parameter = new Parameter();
                                parameter.setPhoneNumber("042592003");
                                parameter.setEmail("servicioalcliente@pycca.com");
                                SharedPreferencesManager.getInstance(splashActivity).setParameter(parameter);
                                getUser(splashActivity, taskListener);
                            }
                        }
                        else {
                            Parameter parameter = new Parameter();
                            parameter.setPhoneNumber("042592003");
                            parameter.setEmail("servicioalcliente@pycca.com");
                            SharedPreferencesManager.getInstance(splashActivity).setParameter(parameter);
                            getUser(splashActivity, taskListener);
                        }
                    }
                });
    }

    public void getUser(SplashActivity splashActivity, SplashActivityMVP.TaskListener taskListener) {
        taskListener.onSuccess(SharedPreferencesManager.getInstance(splashActivity).getUser());
    }

}
