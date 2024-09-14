package com.example.mygrocery.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.RewardModel;

import java.util.List;

public class RewardsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<RewardModel> rewardModelList;

    public RewardsAdapter(List<RewardModel> rewardModelList2) {
        this.rewardModelList = rewardModelList2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.rewards_item_layout, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setRewards(this.rewardModelList.get(position).getTitle(), this.rewardModelList.get(position).getExpiryDate(), this.rewardModelList.get(position).getCoupenBody());
    }

    public int getItemCount() {
        return this.rewardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView coupenBody;
        TextView expirydate;
        TextView rewardtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rewardtitle = (TextView) itemView.findViewById(C0181R.C0184id.rewards_coupen_title);
            this.expirydate = (TextView) itemView.findViewById(C0181R.C0184id.Expiry_date);
            this.coupenBody = (TextView) itemView.findViewById(C0181R.C0184id.coupenBody);
        }

        public void setRewards(String title, String validity, String body) {
            this.rewardtitle.setText(title);
            this.expirydate.setText(validity);
            this.coupenBody.setText(body);
        }
    }
}
