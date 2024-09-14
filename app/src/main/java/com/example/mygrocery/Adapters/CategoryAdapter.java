package com.example.mygrocery.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.CategoryModel;
import com.example.mygrocery.views.CategoryActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<CategoryModel> list;
    private int lposition = -1;

    public CategoryAdapter(List<CategoryModel> list2) {
        this.list = list2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.category_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        String icon = this.list.get(position).getCategoryIcon();
        holder.setCategory(this.list.get(position).getCategoryName(), position);
        holder.setCategoryIcons(icon);
        if (this.lposition < position) {
            holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), C0181R.anim.fade_in));
            this.lposition = position;
        }
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.categoryIcon = (ImageView) itemView.findViewById(C0181R.C0184id.categoryIcon);
            this.categoryName = (TextView) itemView.findViewById(C0181R.C0184id.categoryName);
        }

        public void setCategoryIcons(String iconUrl) {
            if (!iconUrl.equals("null")) {
                Glide.with(this.itemView.getContext()).load(iconUrl).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.ic_baseline_home_24)).into(this.categoryIcon);
            }
        }

        /* access modifiers changed from: private */
        public void setCategory(final String name, final int position) {
            this.categoryName.setText(name);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (position != 0) {
                        Intent categoryIntent = new Intent(ViewHolder.this.itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("Category Name", name);
                        ViewHolder.this.itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });
        }
    }
}
