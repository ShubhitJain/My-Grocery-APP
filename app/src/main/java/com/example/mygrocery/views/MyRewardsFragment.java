package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.RewardsAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.RewardModel;

import java.util.ArrayList;
import java.util.List;

public class MyRewardsFragment extends Fragment {
    RecyclerView rewardsrecyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_my_rewards, container, false);
        this.rewardsrecyclerView = (RecyclerView) view.findViewById(C0181R.C0184id.My_reward_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(1);
        this.rewardsrecyclerView.setLayoutManager(layoutManager);
        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("CashBack", "till 31st,April 2022", "GET 20% CASHBACK on any product above 200"));
        RewardsAdapter adapter = new RewardsAdapter(rewardModelList);
        this.rewardsrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
