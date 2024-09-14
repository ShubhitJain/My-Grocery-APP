package com.example.mygrocery.Adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.Models.HomePageModel;
import com.example.mygrocery.Models.HorizontalProductScrollModel;
import com.example.mygrocery.Models.MyWishListModel;
import com.example.mygrocery.Models.SlderModel;
import com.example.mygrocery.views.ViewALlActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private int lPosition = -1;
    /* access modifiers changed from: private */
    public RecyclerView.RecycledViewPool recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList2) {
        this.homePageModelList = homePageModelList2;
        this.recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    public int getItemViewType(int position) {
        switch (this.homePageModelList.get(position).getType()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return -1;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new BannerSliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.sliding_ad_banner, parent, false));
            case 1:
                return new StripAdBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.strip_ad_layout, parent, false));
            case 2:
                return new HorizontalProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.deals_scroll_layout, parent, false));
            case 3:
                return new GridProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0181R.C0185layout.grid_product_layout, parent, false));
            default:
                return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (this.homePageModelList.get(position).getType()) {
            case 0:
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(this.homePageModelList.get(position).getSlderModelList());
                break;
            case 1:
                ((StripAdBannerViewHolder) holder).setStripAd(this.homePageModelList.get(position).getResource(), this.homePageModelList.get(position).getBgColor());
                break;
            case 2:
                String color1 = this.homePageModelList.get(position).getBgColor();
                HorizontalProductViewHolder horizontalProductViewHolder = (HorizontalProductViewHolder) holder;
                horizontalProductViewHolder.setHorizontalProductLayout(this.homePageModelList.get(position).getHorizontalProductScrollModelList(), this.homePageModelList.get(position).getTitle(), color1, this.homePageModelList.get(position).getViewAllProductList());
                break;
            case 3:
                ((GridProductViewHolder) holder).setGridProductLayout(this.homePageModelList.get(position).getHorizontalProductScrollModelList(), this.homePageModelList.get(position).getTitle());
                return;
            default:
                return;
        }
        if (this.lPosition < position) {
            holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), C0181R.anim.fade_in));
            this.lPosition = position;
        }
    }

    public int getItemCount() {
        return this.homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ViewPager bannerSliderViewPager;
        /* access modifiers changed from: private */
        public int currentPage = 2;
        private final long delayTime = 2000;
        private final long periodTime = 2000;
        private Timer timer;

        static /* synthetic */ int access$408(BannerSliderViewHolder x0) {
            int i = x0.currentPage;
            x0.currentPage = i + 1;
            return i;
        }

        public BannerSliderViewHolder(View itemView) {
            super(itemView);
            this.bannerSliderViewPager = (ViewPager) itemView.findViewById(C0181R.C0184id.banner_slider_viewpager);
        }

        /* access modifiers changed from: private */
        public void setBannerSliderViewPager(final List<SlderModel> slderModelList) {
            this.bannerSliderViewPager.setAdapter(new SliderAdapter(slderModelList));
            this.bannerSliderViewPager.setClipToPadding(false);
            this.bannerSliderViewPager.setPageMargin(20);
            this.bannerSliderViewPager.setCurrentItem(this.currentPage);
            this.bannerSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                public void onPageSelected(int position) {
                    int unused = BannerSliderViewHolder.this.currentPage = position;
                }

                public void onPageScrollStateChanged(int state) {
                    if (state == 0) {
                        BannerSliderViewHolder.this.pageLooper(slderModelList);
                    }
                }
            });
            StartBannerSlideShow(slderModelList);
            this.bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    BannerSliderViewHolder.this.pageLooper(slderModelList);
                    BannerSliderViewHolder.this.StopBannerSlideShow();
                    if (motionEvent.getAction() != 1) {
                        return false;
                    }
                    BannerSliderViewHolder.this.StartBannerSlideShow(slderModelList);
                    return false;
                }
            });
        }

        /* access modifiers changed from: private */
        public void pageLooper(List<SlderModel> slderModelList) {
            if (this.currentPage == slderModelList.size() - 2) {
                this.currentPage = 2;
                this.bannerSliderViewPager.setCurrentItem(2, false);
            }
            if (this.currentPage == 1) {
                int size = slderModelList.size() - 3;
                this.currentPage = size;
                this.bannerSliderViewPager.setCurrentItem(size, false);
            }
        }

        /* access modifiers changed from: private */
        public void StartBannerSlideShow(final List<SlderModel> slderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                public void run() {
                    if (BannerSliderViewHolder.this.currentPage >= slderModelList.size()) {
                        int unused = BannerSliderViewHolder.this.currentPage = 1;
                    }
                    BannerSliderViewHolder.this.bannerSliderViewPager.setCurrentItem(BannerSliderViewHolder.access$408(BannerSliderViewHolder.this), true);
                }
            };
            Timer timer2 = new Timer();
            this.timer = timer2;
            timer2.schedule(new TimerTask() {
                public void run() {
                    handler.post(update);
                }
            }, 2000, 2000);
        }

        /* access modifiers changed from: private */
        public void StopBannerSlideShow() {
            this.timer.cancel();
        }
    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout stripAdCointainer;
        private ImageView stripAdImage;

        public StripAdBannerViewHolder(View itemView) {
            super(itemView);
            this.stripAdImage = (ImageView) itemView.findViewById(C0181R.C0184id.strip_ad_image);
            this.stripAdCointainer = (ConstraintLayout) itemView.findViewById(C0181R.C0184id.strip_ad_container);
        }

        /* access modifiers changed from: private */
        public void setStripAd(String resource, String color) {
            Glide.with(this.itemView.getContext()).load(resource).apply((BaseRequestOptions<?>) new RequestOptions().placeholder((int) C0181R.C0183drawable.strip)).into(this.stripAdImage);
            this.stripAdCointainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView horizontalRecyclerView;
        private TextView horizontallayoutTitle;
        private Button horizontalviewAllButton;
        private ConstraintLayout layout;

        public HorizontalProductViewHolder(View itemView) {
            super(itemView);
            this.horizontallayoutTitle = (TextView) itemView.findViewById(C0181R.C0184id.horizonta_scroll_layout_title);
            this.horizontalviewAllButton = (Button) itemView.findViewById(C0181R.C0184id.horizonta_scroll_layout_button);
            RecyclerView recyclerView = (RecyclerView) itemView.findViewById(C0181R.C0184id.horizonta_scroll_layout_recyclerView);
            this.horizontalRecyclerView = recyclerView;
            recyclerView.setRecycledViewPool(HomePageAdapter.this.recycledViewPool);
            this.layout = (ConstraintLayout) itemView.findViewById(C0181R.C0184id.hs_cointainer);
        }

        /* access modifiers changed from: private */
        public void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, final String title, String color, final List<MyWishListModel> viewAllProductsList) {
            this.layout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            this.horizontallayoutTitle.setText(title);
            if (horizontalProductScrollModelList.size() > 8) {
                this.horizontalviewAllButton.setVisibility(0);
                this.horizontalviewAllButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ViewALlActivity.wishListModelList = viewAllProductsList;
                        Intent viewAllIntent = new Intent(HorizontalProductViewHolder.this.itemView.getContext(), ViewALlActivity.class);
                        viewAllIntent.putExtra("title", title);
                        viewAllIntent.putExtra("layout_code", 0);
                        HorizontalProductViewHolder.this.itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                this.horizontalviewAllButton.setVisibility(4);
            }
            HorizontalProductsScrollAdapter horizontalProductsScrollAdapter = new HorizontalProductsScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.itemView.getContext());
            linearLayoutManager.setOrientation(0);
            this.horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            this.horizontalRecyclerView.setAdapter(horizontalProductsScrollAdapter);
            horizontalProductsScrollAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        private Button gridLauoutButton;
        private TextView gridLayoutTitle;
        private GridView gridLayoutproduct;

        public GridProductViewHolder(View itemView) {
            super(itemView);
            this.gridLayoutTitle = (TextView) itemView.findViewById(C0181R.C0184id.grid_layout_title);
            this.gridLauoutButton = (Button) itemView.findViewById(C0181R.C0184id.grid_layout_button);
            this.gridLayoutproduct = (GridView) itemView.findViewById(C0181R.C0184id.grid_layput_gridView);
        }

        /* access modifiers changed from: private */
        public void setGridProductLayout(final List<HorizontalProductScrollModel> horizontalProductScrollModelList, final String title) {
            this.gridLayoutTitle.setText(title);
            this.gridLayoutproduct.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));
            this.gridLauoutButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ViewALlActivity.horizontalProductScrollModelList = horizontalProductScrollModelList;
                    Intent viewAllIntent = new Intent(GridProductViewHolder.this.itemView.getContext(), ViewALlActivity.class);
                    viewAllIntent.putExtra("layout_code", 1);
                    viewAllIntent.putExtra("title", title);
                    GridProductViewHolder.this.itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }
}
