package com.example.mygrocery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.MyWishListModel;

import java.util.List;

public class MyWishListAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Boolean wishList;
    List<MyWishListModel> wishListModelList;

    public MyWishListAdapter(List<MyWishListModel> wishListModelList2, Boolean wishList2) {
        this.wishListModelList = wishListModelList2;
        this.wishList = wishList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.wishlist_item_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        int i = position;
        String resource = this.wishListModelList.get(i).getProductImage();
        String title = this.wishListModelList.get(i).getProducttitle();
        String productPrice = this.wishListModelList.get(i).getProductPrice();
        long freeCoupens = this.wishListModelList.get(i).getFreeCoupens();
        String rating = this.wishListModelList.get(i).getRating();
        long totaorating = this.wishListModelList.get(i).getTotalRatings();
        holder.setWishlistItems(resource, title, productPrice, this.wishListModelList.get(i).getProductCuttedPrice(), freeCoupens, rating, totaorating, this.wishListModelList.get(i).getPayementMethod());
    }

    public int getItemCount() {
        return this.wishListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coupenIcon;
        private TextView cuutedPrice;
        private ImageButton delete;
        private TextView freeCoupens;
        private TextView payementmethod;
        private View priceCut;
        private ImageView productImage;
        private TextView productPrice;
        private TextView productTitle;
        private TextView rating;
        private TextView totalRatings;

        public ViewHolder(View itemView) {
            super(itemView);
            this.productImage = (ImageView) itemView.findViewById(C0181R.C0184id.wishlistproductImage);
            this.productTitle = (TextView) itemView.findViewById(C0181R.C0184id.wishlistproductTitle);
            this.freeCoupens = (TextView) itemView.findViewById(C0181R.C0184id.freeCoupens);
            this.coupenIcon = (ImageView) itemView.findViewById(C0181R.C0184id.coupenIcon);
            this.productPrice = (TextView) itemView.findViewById(C0181R.C0184id.productPrice);
            this.cuutedPrice = (TextView) itemView.findViewById(C0181R.C0184id.productCuttedPrice);
            this.payementmethod = (TextView) itemView.findViewById(C0181R.C0184id.payementMethod);
            this.rating = (TextView) itemView.findViewById(C0181R.C0184id.tv_ratings_mini_view);
            this.totalRatings = (TextView) itemView.findViewById(C0181R.C0184id.totalRatings);
            this.priceCut = itemView.findViewById(C0181R.C0184id.price_cut);
            this.delete = (ImageButton) itemView.findViewById(C0181R.C0184id.delete);
        }

        /* access modifiers changed from: private */
        public void setWishlistItems(String resource, String title, String price, String cutPrice, long freeCoupensNum, String avgRate, long totalRatingsNum, boolean payMethod) {
            Glide.with(this.itemView.getContext()).load(resource).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.product)).into(this.productImage);
            this.productTitle.setText(title);
            this.cuutedPrice.setText(cutPrice);
            if (freeCoupensNum != 0) {
                this.coupenIcon.setVisibility(0);
                if (freeCoupensNum == 1) {
                    this.freeCoupens.setText("free " + freeCoupensNum + " coupen");
                } else {
                    this.freeCoupens.setText("free " + freeCoupensNum + " coupens");
                }
            } else {
                this.coupenIcon.setVisibility(4);
                this.freeCoupens.setVisibility(4);
            }
            this.rating.setText(avgRate);
            this.totalRatings.setText(totalRatingsNum + "(Ratings)");
            if (payMethod) {
                this.payementmethod.setVisibility(0);
            } else {
                this.payementmethod.setVisibility(4);
            }
            if (MyWishListAdapter.this.wishList.booleanValue()) {
                this.delete.setVisibility(0);
            } else {
                this.delete.setVisibility(8);
            }
            this.delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(ViewHolder.this.itemView.getContext(), "Delete", 0).show();
                }
            });
        }
    }
}
