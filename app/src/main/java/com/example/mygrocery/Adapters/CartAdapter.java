package com.example.mygrocery.Adapters;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.CartItemModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {
    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList2) {
        this.cartItemModelList = cartItemModelList2;
    }

    public int getItemViewType(int position) {
        switch (this.cartItemModelList.get(position).getType()) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return -1;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new cartItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.cart_item_layout, parent, false));
            case 1:
                return new cartTotslAmountViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.cart_total_amount_layout, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (this.cartItemModelList.get(position).getType()) {
            case 0:
                ((cartItemViewHolder) holder).setItemDetails(this.cartItemModelList.get(position).getProductimage(), this.cartItemModelList.get(position).getProductTitle(), this.cartItemModelList.get(position).getFreeCoupens(), this.cartItemModelList.get(position).getProductprice(), this.cartItemModelList.get(position).getCuttedPrice(), this.cartItemModelList.get(position).getOffersApplied());
                return;
            case 1:
                String totalItems = this.cartItemModelList.get(position).getTotal_items();
                String totalItemPrice = this.cartItemModelList.get(position).getTotal_items_price();
                String dileveryPrice = this.cartItemModelList.get(position).getDeliverprice();
                String savedAmount = this.cartItemModelList.get(position).getSaved_amount();
                ((cartTotslAmountViewHolder) holder).setTotalCartAmount(totalItems, totalItemPrice, this.cartItemModelList.get(position).getTotal_amount(), dileveryPrice, savedAmount);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.cartItemModelList.size();
    }

    class cartItemViewHolder extends RecyclerView.ViewHolder {
        private TextView cartProductCuttedPrice;
        private ImageView cartProductImage;
        /* access modifiers changed from: private */
        public TextView cartProductQuantity;
        private TextView cartProductTitle;
        private TextView cartProductprice;
        private TextView coupensApplied;
        private ImageView freeCoupenImage;
        private TextView freeCoupens;
        private TextView offerAplied;

        public cartItemViewHolder(View itemView) {
            super(itemView);
            this.cartProductImage = (ImageView) itemView.findViewById(C0181R.C0184id.cart_product_image);
            this.cartProductTitle = (TextView) itemView.findViewById(C0181R.C0184id.cart_product_title);
            this.freeCoupenImage = (ImageView) itemView.findViewById(C0181R.C0184id.free_coupen_icon);
            this.cartProductprice = (TextView) itemView.findViewById(C0181R.C0184id.cart_product_price);
            this.cartProductCuttedPrice = (TextView) itemView.findViewById(C0181R.C0184id.cart_cutted_price);
            this.freeCoupens = (TextView) itemView.findViewById(C0181R.C0184id.tv_free_coupan);
            this.offerAplied = (TextView) itemView.findViewById(C0181R.C0184id.cart_offer_applied);
            this.coupensApplied = (TextView) itemView.findViewById(C0181R.C0184id.cart_coupens_applied);
            this.cartProductQuantity = (TextView) itemView.findViewById(C0181R.C0184id.product_quantity);
        }

        /* access modifiers changed from: private */
        public void setItemDetails(int resourse, String title, int freeCoupensNum, String productprice, String cuttedPrice, int offersAppliedNum) {
            this.cartProductImage.setImageResource(resourse);
            this.cartProductTitle.setText(title);
            if (freeCoupensNum > 0) {
                this.freeCoupenImage.setVisibility(0);
                this.freeCoupens.setVisibility(0);
                if (freeCoupensNum == 1) {
                    this.freeCoupens.setText("free " + freeCoupensNum + " Coupen");
                } else {
                    this.freeCoupens.setText("free " + freeCoupensNum + " Coupens");
                }
            } else {
                this.freeCoupenImage.setVisibility(4);
                this.freeCoupens.setVisibility(4);
            }
            this.cartProductprice.setText(productprice);
            this.cartProductCuttedPrice.setText(cuttedPrice);
            if (offersAppliedNum > 0) {
                this.offerAplied.setVisibility(0);
                this.offerAplied.setText(offersAppliedNum + " offer Applied");
            } else {
                this.offerAplied.setVisibility(4);
            }
            this.cartProductQuantity.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    final Dialog quantityDialog = new Dialog(cartItemViewHolder.this.itemView.getContext());
                    quantityDialog.setContentView(C0181R.C0185layout.quantity_diolog_layout);
                    quantityDialog.setCancelable(false);
                    final EditText quantityNum = (EditText) quantityDialog.findViewById(C0181R.C0184id.quantity_count);
                    ((Button) quantityDialog.findViewById(C0181R.C0184id.cancel_quantity)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            quantityDialog.dismiss();
                        }
                    });
                    ((Button) quantityDialog.findViewById(C0181R.C0184id.ok_quantity)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            cartItemViewHolder.this.cartProductQuantity.setText("Qty: " + quantityNum.getText());
                            quantityDialog.dismiss();
                        }
                    });
                    quantityDialog.show();
                }
            });
        }
    }

    class cartTotslAmountViewHolder extends RecyclerView.ViewHolder {
        private TextView dileveryPrice;
        private TextView savedAmount;
        private TextView totalAmount;
        private TextView totalItemPrice;
        private TextView totalItems;

        public cartTotslAmountViewHolder(View itemView) {
            super(itemView);
            this.totalItems = (TextView) itemView.findViewById(C0181R.C0184id.total_items);
            this.totalItemPrice = (TextView) itemView.findViewById(C0181R.C0184id.total_item_price);
            this.dileveryPrice = (TextView) itemView.findViewById(C0181R.C0184id.dilevery_price);
            this.totalAmount = (TextView) itemView.findViewById(C0181R.C0184id.total_price);
            this.savedAmount = (TextView) itemView.findViewById(C0181R.C0184id.saved_amount);
        }

        /* access modifiers changed from: private */
        public void setTotalCartAmount(String totalItemText, String totalItemPricetext, String totalAmountText, String dileveryAmountText, String savedAmountText) {
            this.totalAmount.setText(totalAmountText);
            this.totalItemPrice.setText(totalItemPricetext);
            this.totalItems.setText(totalItemText);
            this.dileveryPrice.setText(dileveryAmountText);
            this.savedAmount.setText(savedAmountText);
        }
    }
}
