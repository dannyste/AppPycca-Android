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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.ClientResponse;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.Date;

public class SignUpActivityModel implements SignUpActivityMVP.Model {

    private FirebaseAuth firebaseAuth;
    private FirebaseInstanceId firebaseInstanceId;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseMessaging firebaseMessaging;

    SignUpActivityModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    @Override
    public void firebaseCreateUserWithEmailAndPassword(final SignUpActivity signUpActivity, String email, String password, boolean clubPyccaPartner, String identificationCard, String clubPyccaCardNumber, BaseResponse baseResponse, final SignUpActivityMVP.TaskListener taskListener) {
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setClubPyccaPartner(clubPyccaPartner);
        if (clubPyccaPartner) {
            Gson gson = new Gson();
            ClientResponse clientResponse = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), ClientResponse.class);
            user.setIdentificationCard(identificationCard);
            user.setClubPyccaCardNumber(clubPyccaCardNumber);
            user.setNamesClubPyccaPartner(clientResponse.getCl_nombres());
            user.setSurnamesClubPyccaPartner(clientResponse.getCl_apellidos());
            user.setAccountNumber(clientResponse.getMa_cuenta());
            user.setClientSince(clientResponse.getMa_fapertura());
        }
        user.setCreationDate(new Date());
        user.setModificationDate(new Date());
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signUpActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getToken(signUpActivity, user, taskListener);
                        }
                        else {
                            int errorCode;
                            if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                errorCode = R.string.error_password_least_six_characters;
                            }
                            else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                errorCode = R.string.error_email_already_use;
                            }
                            else {
                                errorCode = R.string.error_default;
                            }
                            taskListener.onError(errorCode);
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

    private void getToken(final SignUpActivity signUpActivity, final User user, final SignUpActivityMVP.TaskListener taskListener) {
        firebaseInstanceId.getInstanceId().addOnSuccessListener(signUpActivity,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                user.setToken(instanceIdResult.getToken());
                createUserFirebaseFirestore(signUpActivity, user, taskListener);
            }
        });
    }

    private void createUserFirebaseFirestore(final SignUpActivity signUpActivity, final User user, final SignUpActivityMVP.TaskListener taskListener) {
        firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail()).set(user.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesManager.getInstance(signUpActivity).setUser(user);
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
