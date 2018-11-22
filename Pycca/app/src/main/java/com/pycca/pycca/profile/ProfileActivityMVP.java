package com.pycca.pycca.profile;

import com.pycca.pycca.pojo.User;

public interface ProfileActivityMVP {

    interface View {

        void goToMultiLoginActivity();

        void setPhotoUrl(String photoUrl);

        void setName(String name);

        void setEmail(String email);

        void setIdentificationCard(String identificationCard);

        void setClubPyccaCardNumber(String clubPyccaCardNumber);

    }

    interface Presenter {

        void setView(ProfileActivityMVP.View view);

        void getCurrentUser(ProfileActivity profileActivity);

        void logoutClicked(ProfileActivity profileActivity);

    }

    interface Model {

        User getUser(ProfileActivity profileActivity);

        void userLogout(ProfileActivity profileActivity);

        void userSubscribeToTopic(String topic);

        void userUnsubscribeFromTopic(String topic);

    }

}
