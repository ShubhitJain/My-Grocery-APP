package com.example.mygrocery.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;

import java.util.List;

public class ProductImagesAdapter extends PagerAdapter {
    private List<String> productImages;

    public ProductImagesAdapter(List<String> productImages2) {
        this.productImages = productImages2;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());
        Glide.with(container.getContext()).load(this.productImages.get(position)).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.almonds)).into(productImage);
        container.addView(productImage, 0);
        return productImage;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    public int getCount() {
        return this.productImages.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
