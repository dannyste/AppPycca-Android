package com.pycca.pycca.coupon;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.util.Constants;
import com.pycca.pycca.util.Util;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CouponFragmentModel implements CouponFragmentMVP.Model {
    FirebaseFirestore db;

    public CouponFragmentModel(){
        db = FirebaseFirestore.getInstance();
    }


    @Override
    public void getContentImages(final CouponFragmentMVP.TaskListener listener) {
        final ArrayList<CouponImageResource> list = new ArrayList<>();
        CollectionReference crContent = db.collection(Constants.FIRESTORE_CONTENT_TABLE);
        DocumentReference drHome = crContent.document(Constants.FIRESTORE_COUPON_TABLE);
        CollectionReference crHeader = drHome.collection(Constants.FIRESTORE_COUPON_CONTENT_TABLE);
        crHeader.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                list.clear();
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                        CouponImageResource imageResource = document.toObject(CouponImageResource.class);
                        if(!Util.containCouponResourceInList(list, imageResource)){
                            list.add(imageResource);
                        }
                    }
                }
                try {
                    Collections.sort(list, new Comparator<CouponImageResource>() {
                        @Override
                        public int compare(CouponImageResource img1, CouponImageResource img2) {
                            return Integer.valueOf(img1.getOrder()) - Integer.valueOf(img2.getOrder());
                        }
                    });
                }catch (Exception e1){
                    e1.printStackTrace();
                }
                listener.onSuccess(list);
            }

        });
    }
}
