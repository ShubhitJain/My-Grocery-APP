package com.example.mygrocery.Adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.MyOrderitemModel;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<MyOrderitemModel> orderitemModelList;

    public MyOrderAdapter(List<MyOrderitemModel> orderitemModelList2) {
        this.orderitemModelList = orderitemModelList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.my_orders_item_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setOrderDetails(this.orderitemModelList.get(position).getOrderImage(), this.orderitemModelList.get(position).getOrderTitle(), this.orderitemModelList.get(position).getDileveryStatus(), this.orderitemModelList.get(position).getRating());
    }

    public int getItemCount() {
        return this.orderitemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dileverStatus;
        ImageView dileveryIndicator;
        ImageView myOrderImage;
        TextView myOrderTitle;
        private LinearLayout rateNowCointainer;

        public ViewHolder(View itemView) {
            super(itemView);
            this.myOrderImage = (ImageView) itemView.findViewById(C0181R.C0184id.order_title_image);
            this.myOrderTitle = (TextView) itemView.findViewById(C0181R.C0184id.order_title);
            this.dileverStatus = (TextView) itemView.findViewById(C0181R.C0184id.order_dilevered_date);
            this.dileveryIndicator = (ImageView) itemView.findViewById(C0181R.C0184id.order_indicater);
            this.rateNowCointainer = (LinearLayout) itemView.findViewById(C0181R.C0184id.rate_now_container);
        }

        public void setOrderDetails(int resource, String title, String dileverydate, int rating) {
            this.myOrderImage.setImageResource(resource);
            this.myOrderTitle.setText(title);
            if (dileverydate.equals("Cancelled")) {
                this.dileveryIndicator.setImageTintList(ColorStateList.valueOf(this.itemView.getContext().getResources().getColor(C0181R.C0182color.failred)));
            } else {
                this.dileveryIndicator.setImageTintList(ColorStateList.valueOf(this.itemView.getContext().getResources().getColor(C0181R.C0182color.sucsessGreen)));
            }
            this.dileverStatus.setText(dileverydate);
            setRating(rating);
            for (int x = 0; x < this.rateNowCointainer.getChildCount(); x++) {
                final int starPosition = x;
                this.rateNowCointainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ViewHolder.this.setRating(starPosition);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public void setRating(int starposition) {
            for (int x = 0; x < this.rateNowCointainer.getChildCount(); x++) {
                ImageView starBtn = (ImageView) this.rateNowCointainer.getChildAt(x);
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if (x <= starposition) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }
}
