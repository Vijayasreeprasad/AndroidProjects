package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Viji on 5/8/2017.
 */

public class RiddleListFragment extends Fragment {

   // Modify CrimeListFragment to use this layout file and to find the RecyclerView in the layout file

    private RecyclerView mRiddleRecyclerView;
    private RiddleAdapter mAdapter;

    //added 2:menu
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
//end 2:menu

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riddle_list, container, false);

        mRiddleRecyclerView = (RecyclerView) view
                .findViewById(R.id.riddle_recycler_view);
        mRiddleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


//added 1: menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_riddle_list, menu);
    }
//end 1: menu

//menu_button to add to database
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
         /*   case R.id.new_riddle:
                Riddle riddle = new Riddle();
                RiddleLab.get(getActivity()).addCrime(riddle);
                Intent intent = RiddlePagerActivity
                        .newIntent(getActivity(), riddle.getId());
                startActivity(intent);
                return true; */
            case R.id.new_cam:
                Riddle riddleCam = new Riddle();
                RiddleLab.get(getActivity()).addCrime(riddleCam);
                Intent camintent = RecognizeActivity
                        .newIntent1(getActivity(), riddleCam.getId());
                startActivity(camintent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        RiddleLab crimeLab = RiddleLab.get(getActivity());
        List<Riddle> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
        mAdapter = new RiddleAdapter(crimes);
        mRiddleRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }
    }


        private class RiddleHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            private Riddle mRiddle;
            private TextView mTitleTextView;
            //private TextView mDateTextView;

            public RiddleHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_item_riddle, parent, false));
                itemView.setOnClickListener(this);

                mTitleTextView = (TextView) itemView.findViewById(R.id.riddle_title);
                //mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            }

            //This will be called each time a new Crime should be displayed in your RiddleHolder.
            public void bind(Riddle riddle) {
                mRiddle = riddle;
                mTitleTextView.setText(mRiddle.getTitle());
                //mDateTextView.setText(mCrime.getDate().toString());
            }
            @Override
            public void onClick(View view) {
                Intent intent = RiddlePagerActivity.newIntent(getActivity(), mRiddle.getId());
                startActivity(intent);
            }
        }


    private class RiddleAdapter extends RecyclerView.Adapter<RiddleHolder> {

        private List<Riddle> mRiddles;

        public RiddleAdapter(List<Riddle> crimes) {
            mRiddles = crimes;

        }
            @Override
            public RiddleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

                return new RiddleHolder(layoutInflater, parent);
            }

            @Override
            public void onBindViewHolder(RiddleHolder holder, int position) {
                Riddle riddle = mRiddles.get(position);
                holder.bind(riddle);

            }

            @Override
            public int getItemCount() {
                return mRiddles.size();
            }

        public void setCrimes(List<Riddle> riddles) {
            mRiddles = riddles;
        }
    }

    }





