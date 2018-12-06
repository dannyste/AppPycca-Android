package com.pycca.pycca.cardlocking;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.pojo.User;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.restApi.model.CardResponse;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.SharedPreferencesManager;

import java.util.ArrayList;

public class CardLockingActivityModel implements CardLockingActivityMVP.Model {

    private FirebaseFirestore firebaseFirestore;

    CardLockingActivityModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public User getUser(CardLockingActivity cardLockingActivity) {
        return SharedPreferencesManager.getInstance(cardLockingActivity).getUser();
    }

    @Override
    public void setUser(CardLockingActivity cardLockingActivity, User user, CardLockingActivityMVP.TaskListener taskListener) {
        updateUserFirebaseFirestore(cardLockingActivity, user, taskListener);
    }

    @Override
    public ArrayList<Card> getCardArrayList(BaseResponse baseResponse) {
        Gson gson = new Gson();
        TypeToken<ArrayList<CardResponse>> typeToken = new TypeToken<ArrayList<CardResponse>>() {};
        ArrayList<CardResponse> cardResponseArrayList = gson.fromJson(gson.toJson(baseResponse.getData().getResult()), typeToken.getType());
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for (CardResponse cardResponse : cardResponseArrayList) {
            Card card = new Card();
            card.setClubPyccaCardNumber(cardResponse.getTarjeta());
            card.setClubPyccaCardName(cardResponse.getTa_plnombre1());
            card.setHolder(cardResponse.getTa_princiadicio().equalsIgnoreCase("P"));
            cardArrayList.add(card);
        }
        return cardArrayList;
    }

    private void updateUserFirebaseFirestore(final CardLockingActivity cardLockingActivity, final User user, final CardLockingActivityMVP.TaskListener taskListener) {
        DocumentReference documentReference = firebaseFirestore.collection(Constants.FIRESTORE_USER_TABLE).document(user.getEmail());
        documentReference.update(
                    "clubPyccaCardLocked", user.isClubPyccaCardLocked(),
                    "modificationDate", user.getModificationDate()
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesManager.getInstance(cardLockingActivity).setUser(user);
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
