package com.microsoft.projectoxford.emotionsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.microsoft.projectoxford.emotionsample.helper.SelectImageActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Viji on 5/9/2017.
 */

public class RiddlePagerActivity extends AppCompatActivity {

    private static final String EXTRA_RIDDLE_ID =
            "com.microsoft.projectoxford.emotionsample.riddle_id";

    private ViewPager mViewPager;
    private List<Riddle> mRiddles;


    public static Intent newIntent(Context packageContext, UUID riddleId) {
        Intent intent = new Intent(packageContext, RiddlePagerActivity.class);
        intent.putExtra(EXTRA_RIDDLE_ID, riddleId);
        return intent;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle_pager);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_RIDDLE_ID);

        mViewPager = (ViewPager) findViewById(R.id.riddle_view_pager);

        mRiddles = RiddleLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Riddle riddle = mRiddles.get(position);
                return RiddleFragment.newInstance(riddle.getId());
            }

            @Override
            public int getCount() {
                return mRiddles.size();
            }
        });

        for (int i = 0; i < mRiddles.size(); i++) {
            if (mRiddles.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public void sendButton(View view) {
        Intent intent = new Intent(this, ChatActivity.class); //SecondActivity
        startActivity(intent);
    }

  /*  public void cameraClick(View view)
    {
        Intent intent=new Intent(this,SelectImageActivity.class); //SecondActivity
        startActivity(intent);
    }*/


}
