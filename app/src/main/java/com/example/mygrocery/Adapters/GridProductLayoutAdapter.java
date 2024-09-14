package com.example.mygrocery.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.HorizontalProductScrollModel;
import com.example.mygrocery.views.ProductDetailsActivity;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {
    List<HorizontalProductScrollModel> gridProductScrollModel;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> gridProductScrollModel2) {
        this.gridProductScrollModel = gridProductScrollModel2;
    }

    public int getCount() {
        return this.gridProductScrollModel.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView != null) {
            return convertView;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.horizontal_scroll_item_layout, (ViewGroup) null);
        view.setElevation(0.0f);
        view.setBackgroundColor(Color.parseColor("#FF0000"));
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent productDetailsActivityIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                productDetailsActivityIntent.putExtra("PRODUCT_ID", GridProductLayoutAdapter.this.gridProductScrollModel.get(position).getProductId());
                parent.getContext().startActivity(productDetailsActivityIntent);
            }
        });
        Glide.with(parent.getContext()).load(this.gridProductScrollModel.get(position).getHorizontalProductImage()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.product)).into((ImageView) view.findViewById(C0181R.C0184id.horizontal_product_image));
        ((TextView) view.findViewById(C0181R.C0184id.horizontal_product_title)).setText(this.gridProductScrollModel.get(position).getHorizontalProductTitle());
        ((TextView) view.findViewById(C0181R.C0184id.horizontal_product_description)).setText(this.gridProductScrollModel.get(position).getHorizontalProductDescription());
        ((TextView) view.findViewById(C0181R.C0184id.horizontal_product_price)).setText(this.gridProductScrollModel.get(position).getHorizontalProductPrice());
        return view;
    }
}
