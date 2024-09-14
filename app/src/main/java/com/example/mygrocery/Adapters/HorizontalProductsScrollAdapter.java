package com.example.mygrocery.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.HorizontalProductScrollModel;
import com.example.mygrocery.views.ProductDetailsActivity;

import java.util.List;

public class HorizontalProductsScrollAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<HorizontalProductScrollModel> horizontalProductScrollModels;

    public HorizontalProductsScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModels2) {
        this.horizontalProductScrollModels = horizontalProductScrollModels2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.horizontal_scroll_item_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        String resource = this.horizontalProductScrollModels.get(position).getHorizontalProductImage();
        String title = this.horizontalProductScrollModels.get(position).getHorizontalProductTitle();
        String description = this.horizontalProductScrollModels.get(position).getHorizontalProductDescription();
        String price = this.horizontalProductScrollModels.get(position).getHorizontalProductPrice();
        holder.setdata(this.horizontalProductScrollModels.get(position).getProductId(), resource, title, description, price);
    }

    public int getItemCount() {
        if (this.horizontalProductScrollModels.size() > 8) {
            return 8;
        }
        return this.horizontalProductScrollModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private TextView price;
        private ImageView productImage;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            this.productImage = (ImageView) itemView.findViewById(C0181R.C0184id.horizontal_product_image);
            this.title = (TextView) itemView.findViewById(C0181R.C0184id.horizontal_product_title);
            this.description = (TextView) itemView.findViewById(C0181R.C0184id.horizontal_product_description);
            this.price = (TextView) itemView.findViewById(C0181R.C0184id.horizontal_product_price);
        }

        /* access modifiers changed from: private */
        public void setdata(String productId, String resource, String title1, String description1, String price1) {
            this.title.setText(title1);
            this.description.setText(description1);
            this.price.setText(price1);
            Glide.with(this.itemView.getContext()).load(resource).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.product)).into(this.productImage);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ViewHolder.this.itemView.getContext().startActivity(new Intent(ViewHolder.this.itemView.getContext(), ProductDetailsActivity.class));
                }
            });
        }
    }
}
