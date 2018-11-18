package com.pycca.pycca.clubpycca;

import com.pycca.pycca.pojo.ClubPycca;

import java.util.ArrayList;

public interface ClubPyccaFragmentMVP {

    interface View {

        void updateDataRecyclerView(ArrayList<ClubPycca> clubPyccaArrayList);

        void goToBalanceActivity();

        void goToAccountStatusActivity();

        void goToQuotaIncreaseActivity();

        void goToQuotaCalculatorActivity();

        void goToVirtualCardActivity();

        void goToCardBlockingActivity();

    }

    interface Presenter {

        void setView(ClubPyccaFragmentMVP.View view);

        void loadClubPyccaArrayList();

        void firstItemClicked();

        void secondItemClicked();

        void thirdItemClicked();

        void fourthItemClicked();

        void fifthItemClicked();

        void sixthItemClicked();

    }

    interface Model {

        ArrayList<ClubPycca> getClubPyccaArrayList();

    }

}
