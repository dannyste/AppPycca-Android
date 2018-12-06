package com.pycca.pycca.clubpycca;

import com.pycca.pycca.pojo.ClubPycca;
import com.pycca.pycca.pojo.User;

import java.util.ArrayList;

public interface ClubPyccaFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<ClubPycca> clubPyccaArrayList);

        void showAlertDialogClubPyccaCardLocked();

        void showAlertDialogClubPyccaPartner();

        void goToAccountStatusActivity();

        void goToQuotaIncreaseActivity();

        void goToQuotaCalculatorActivity();

        void goToVirtualCardActivity();

        void goToCardBlockingActivity();

    }

    interface Presenter {

        void setView(ClubPyccaFragmentMVP.View view);

        void loadClubPyccaArrayList();

        void lockPositiveButtonClicked();

        void clubPyccaFirstItemClicked();

        void clubPyccaSecondItemClicked(ClubPyccaFragment clubPyccaFragment);

        void clubPyccaThirdItemClicked();

        void clubPyccaFourthItemClicked(ClubPyccaFragment clubPyccaFragment);

        void clubPyccaFifthItemClicked();

    }

    interface Model {

        ArrayList<ClubPycca> getClubPyccaArrayList();

        User getUser(ClubPyccaFragment clubPyccaFragment);

    }

}
