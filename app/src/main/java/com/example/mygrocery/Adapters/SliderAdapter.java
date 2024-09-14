package com.example.mygrocery.Adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.SlderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<SlderModel> slderModelList;

    public SliderAdapter(List<SlderModel> slderModelList2) {
        this.slderModelList = slderModelList2;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(C0181R.C0185layout.slider_layout, container, false);
        ((ConstraintLayout) view.findViewById(C0181R.C0184id.banner_cointainer)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(this.slderModelList.get(position).getBackgroundColor())));
        Glide.with(container.getContext()).load(this.slderModelList.get(position).getBanner()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.almonds)).into((ImageView) view.findViewById(C0181R.C0184id.banner_slide));
        container.addView(view, 0);
        return view;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getCount() {
        return this.slderModelList.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
