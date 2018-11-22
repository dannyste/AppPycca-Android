package com.pycca.pycca.clubpyccapartner;

public interface ClubPyccaPartnerActivityMVP {

    interface View {
        void showLoadingAnimation();

        void hideLoadingAnimation();

        void showDoneAnimation();

        void showErrorAnimation();

        void showErrorMessage(int errorCode);

        void goToHostActivity();

        void showViewFormSended();

        void showViewForm();

        String getName();

        String getLastName();

        String getBornDate();

        String getIdentification();

        String getEmail();

        String getPhoneNumber();

        String getCellPhoneNumber();

        String getAddress();

    }

    interface Presenter {

        void setView(ClubPyccaPartnerActivityMVP.View view);

        void sendRequestClubPyccaPartner();

        void finishedDoneAnimation();

        void finishedErrorAnimation();

        boolean isFormValid();

    }

    interface Model {

    }

}
