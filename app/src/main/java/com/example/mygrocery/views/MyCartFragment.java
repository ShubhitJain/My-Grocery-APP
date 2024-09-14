package com.example.mygrocery.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.CartAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.CartItemModel;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {
    private RecyclerView cartItemsRecyclerView;
    private Button cont;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0181R.C0185layout.fragment_my_cart, container, false);
        this.cont = (Button) view.findViewById(C0181R.C0184id.cart_continue_btn);
        this.cartItemsRecyclerView = (RecyclerView) view.findViewById(C0181R.C0184id.cart_items_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(1);
        this.cartItemsRecyclerView.setLayoutManager(layoutManager);
        List<CartItemModel> cartItemModelList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = layoutManager;
        CartItemModel cartItemModel = r6;
        CartItemModel cartItemModel2 = new CartItemModel(0, C0181R.C0183drawable.almonds, "Almonds 250g", 2, "Rs.499/-", "Rs.599/-", 1, 0, 0);
        cartItemModelList.add(cartItemModel);
        cartItemModelList.add(new CartItemModel(0, C0181R.C0183drawable.almonds, "Almonds 250g", 2, "Rs.499/-", "Rs.599/-", 1, 1, 0));
        cartItemModelList.add(new CartItemModel(0, C0181R.C0183drawable.almonds, "Almonds 250g", 2, "Rs.499/-", "Rs.599/-", 1, 2, 0));
        cartItemModelList.add(new CartItemModel(1, "Price(3 items)", "Rs.1497/-", "free", "300/-", "1497/-"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        this.cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        this.cont.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyCartFragment.this.getContext().startActivity(new Intent(MyCartFragment.this.getContext(), AddAddressActivity.class));
            }
        });
        return view;
    }
}
