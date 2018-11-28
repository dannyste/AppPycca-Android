package com.pycca.pycca.host;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.ClientResponse;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.Date;

public class HostActivityModel implements HostActivityMVP.Model {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseMessaging firebaseMessaging;

    HostActivityModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
    }

    @Override
    public User getUser(HostActivity hostActivity) {
        return SharedPreferencesManager.getInstance(hostActivity).getUser();
    }

    @Override
    public void setUser(HostActivity hostActivity, String identificationCard, String clubPyccaCardNumber, BaseResponse baseResponse, HostActivityMVP.TaskListener taskListener) {
        Gson gson = new Gson();
        ClientResponse clientResponse = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), ClientResponse.class);
        User user = SharedPreferencesManager.getInstance(hostActivity).getUser();
        user.setClubPyccaPartner(true);
        user.setIdentificationCard(identificationCard);
        user.setClubPyccaCardNumber(clubPyccaCardNumber);
        user.setNamesClubPyccaPartner(clientResponse.getCl_nombres());
        user.setSurnamesClubPyccaPartner(clientResponse.getCl_apellidos());
        user.setAccountNumber(clientResponse.getMa_cuenta());
        user.setClientSince(clientResponse.getMa_fapertura());
        user.setModificationDate(new Date());
        updateUserFirebaseFirestore(hostActivity, user, taskListener);
    }

    @Override
    public void userSubscribeToTopic(String topic) {
        firebaseMessaging.subscribeToTopic(topic);
    }

    @Override
    public void userUnsubscribeFromTopic(String topic) {
        firebaseMessaging.unsubscribeFromTopic(topic);
    }

    private void updateUserFirebaseFirestore(final HostActivity hostActivity, final User user, final HostActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.update(
                    "clubPyccaPartner", user.isClubPyccaPartner(),
                    "identificationCard", user.getIdentificationCard(),
                    "clubPyccaCardNumber", user.getClubPyccaCardNumber(),
                    "accountNumber", user.getAccountNumber(),
                    "clientSince", user.getClientSince(),
                    "modificationDate", user.getModificationDate()
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesManager.getInstance(hostActivity).setUser(user);
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
