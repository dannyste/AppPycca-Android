package com.pycca.pycca.buy;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.pycca.pycca.home.HomeFragmentMVP;
import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class BuyFragmentModel implements BuyFragmentMVP.Model {

    FirebaseFirestore db;

    public BuyFragmentModel(){
       db = FirebaseFirestore.getInstance();
    }


    @Override
    public void getHeaderImages(final BuyFragmentMVP.TaskListener listener) {
        final ArrayList<ImageResource> list = new ArrayList<>();
        CollectionReference crContent = db.collection(Constants.FIRESTORE_CONTENT_TABLE);
        DocumentReference drHome = crContent.document(Constants.FIRESTORE_BUY_TABLE);
        CollectionReference crHeader = drHome.collection(Constants.FIRESTORE_BUY_HEADER_TABLE);
        crHeader.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                list.clear();
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        ImageResource imageResource = document.toObject(ImageResource.class);
                        if(!Util.containImageResourceInList(list, imageResource)){
                            list.add(imageResource);
                        }
                    }
                }
                listener.onSuccessHeader(list);
            }

        });
    }

    @Override
    public void getContentImages(final BuyFragmentMVP.TaskListener listener) {
        final ArrayList<DivisionImageResource> list = new ArrayList<>();
        CollectionReference crContent = db.collection(Constants.FIRESTORE_CONTENT_TABLE);
        DocumentReference drHome = crContent.document(Constants.FIRESTORE_BUY_TABLE);
        CollectionReference crContentHome = drHome.collection(Constants.FIRESTORE_BUY_CONTENT_TABLE);
        crContentHome.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                list.clear();
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        DivisionImageResource imageResource = document.toObject(DivisionImageResource.class);
                        if(!Util.containDivisionResourceInList(list, imageResource)){
                            list.add(imageResource);
                        }
                    }
                }
                listener.onSuccessContent(list);
            }

        });
    }

}
