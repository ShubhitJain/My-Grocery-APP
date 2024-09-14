package com.example.mygrocery.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.MyWishListAdapter;
import com.example.mygrocery.C0181R;

import java.util.ArrayList;

public class MywishListFragment extends Fragment {
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_mywish_list, container, false);
        this.recyclerView = (RecyclerView) view.findViewById(C0181R.C0184id.My_wishlist_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(layoutManager);
        MyWishListAdapter WishListadapter = new MyWishListAdapter(new ArrayList<>(), true);
        this.recyclerView.setAdapter(WishListadapter);
        WishListadapter.notifyDataSetChanged();
        return view;
    }
}
