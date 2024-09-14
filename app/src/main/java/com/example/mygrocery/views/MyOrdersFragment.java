package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.MyOrderAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.MyOrderitemModel;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {
    private RecyclerView myOrdersRecyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_my_orders, container, false);
        this.myOrdersRecyclerView = (RecyclerView) view.findViewById(C0181R.C0184id.myOrdersRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(1);
        this.myOrdersRecyclerView.setLayoutManager(layoutManager);
        List<MyOrderitemModel> orderitemModelList = new ArrayList<>();
        orderitemModelList.add(new MyOrderitemModel(C0181R.C0183drawable.almonds, "Almonds 250g", "Dilevered on Mon,15 Apr 2022", 2));
        orderitemModelList.add(new MyOrderitemModel(C0181R.C0183drawable.raisins, "raisins(Kishmis) 250g", "Dilevered on Mon,15 Apr 2022", 2));
        orderitemModelList.add(new MyOrderitemModel(C0181R.C0183drawable.product, "Sarso Oil 1L Bottle", "Cancelled", 2));
        orderitemModelList.add(new MyOrderitemModel(C0181R.C0183drawable.makhana, "Makhana 250g", "Dilevered on Mon,15 Apr 2022", 2));
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(orderitemModelList);
        this.myOrdersRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }
}
