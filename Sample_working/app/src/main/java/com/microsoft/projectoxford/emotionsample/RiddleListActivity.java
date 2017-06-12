package com.microsoft.projectoxford.emotionsample;

import android.support.v4.app.Fragment;

/**
 * Created by Viji on 5/8/2017.
 */

public class RiddleListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RiddleListFragment();
    }

}