package com.example.mygrocery.views;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mygrocery.Adapters.ProductImagesAdapter;
import com.example.mygrocery.C0181R;
import com.example.mygrocery.p016DB.DBqueries;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailsActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static boolean already_addedToWishlist = false;
    private LinearLayout addtocartBtn;
    private Button buyNowBtn;
    /* access modifiers changed from: private */
    public ImageView cod_indicater_image_view;
    private TextView cod_indicater_text_view;
    /* access modifiers changed from: private */
    public TextView cuttedPrice;
    /* access modifiers changed from: private */
    public FirebaseFirestore firebaseFirestore;
    /* access modifiers changed from: private */
    public TextView productTitle;
    /* access modifiers changed from: private */
    public String product_id;
    /* access modifiers changed from: private */
    public TextView product_price_product_list;
    /* access modifiers changed from: private */
    public ViewPager productimageViewPager;
    /* access modifiers changed from: private */
    public Dialog signInDialog;
    /* access modifiers changed from: private */
    public TextView total_ratings_miniview;
    /* access modifiers changed from: private */
    public TextView tv_ratings_mini_view;
    private TabLayout viewPagerIndicater;
    /* access modifiers changed from: private */
    public FloatingActionButton wishListButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_product_details);
        setSupportActionBar((Toolbar) findViewById(C0181R.C0184id.product_toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.productimageViewPager = (ViewPager) findViewById(C0181R.C0184id.product_images_view_pager);
        this.viewPagerIndicater = (TabLayout) findViewById(C0181R.C0184id.view_pager_indicater);
        this.wishListButton = (FloatingActionButton) findViewById(C0181R.C0184id.wishListButton);
        this.buyNowBtn = (Button) findViewById(C0181R.C0184id.buy_now_btn);
        this.cuttedPrice = (TextView) findViewById(C0181R.C0184id.cuttedPrice);
        this.addtocartBtn = (LinearLayout) findViewById(C0181R.C0184id.addTOcartButton);
        this.productTitle = (TextView) findViewById(C0181R.C0184id.productTitle);
        this.cod_indicater_image_view = (ImageView) findViewById(C0181R.C0184id.cod_indicater_image_view);
        this.cod_indicater_text_view = (TextView) findViewById(C0181R.C0184id.tv_cod_indicater);
        this.total_ratings_miniview = (TextView) findViewById(C0181R.C0184id.total_ratings_miniview);
        this.tv_ratings_mini_view = (TextView) findViewById(C0181R.C0184id.tv_ratings_mini_view);
        this.product_price_product_list = (TextView) findViewById(C0181R.C0184id.product_price_product_list);
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        final List<String> productImages = new ArrayList<>();
        this.product_id = getIntent().getStringExtra("PRODUCT_ID");
        this.firebaseFirestore.collection("PRODUCTS").document(this.product_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            public void onComplete(Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    for (long x = 1; x < ((Long) documentSnapshot.get("no_of_product_images")).longValue() + 1; x++) {
                        productImages.add(documentSnapshot.get("product_image_" + x).toString());
                    }
                    ProductDetailsActivity.this.productimageViewPager.setAdapter(new ProductImagesAdapter(productImages));
                    ProductDetailsActivity.this.productTitle.setText(documentSnapshot.get("product_title").toString());
                    ProductDetailsActivity.this.tv_ratings_mini_view.setText(documentSnapshot.get("avg_rating").toString());
                    ProductDetailsActivity.this.total_ratings_miniview.setText("(" + ((Long) documentSnapshot.get("total_ratings")).longValue() + ")ratings");
                    ProductDetailsActivity.this.product_price_product_list.setText(documentSnapshot.get("product_price").toString());
                    ProductDetailsActivity.this.cuttedPrice.setText(documentSnapshot.get("cutted_price").toString());
                    if (((Boolean) documentSnapshot.get("COD")).booleanValue()) {
                        ProductDetailsActivity.this.cod_indicater_image_view.setVisibility(0);
                        ProductDetailsActivity.this.cod_indicater_image_view.setVisibility(0);
                    } else {
                        ProductDetailsActivity.this.cod_indicater_image_view.setVisibility(4);
                        ProductDetailsActivity.this.cod_indicater_image_view.setVisibility(4);
                    }
                    if (DBqueries.wishList.size() == 0) {
                        DBqueries.loadWishList(ProductDetailsActivity.this);
                    }
                    if (DBqueries.wishList.contains(ProductDetailsActivity.this.product_id)) {
                        boolean unused = ProductDetailsActivity.already_addedToWishlist = true;
                    } else {
                        boolean unused2 = ProductDetailsActivity.already_addedToWishlist = false;
                    }
                } else {
                    Toast.makeText(ProductDetailsActivity.this, task.getException().getMessage(), 0).show();
                }
            }
        });
        this.viewPagerIndicater.setupWithViewPager(this.productimageViewPager, true);
        this.wishListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DBqueries.currentUser == null) {
                    ProductDetailsActivity.this.signInDialog.show();
                } else if (ProductDetailsActivity.already_addedToWishlist) {
                    boolean unused = ProductDetailsActivity.already_addedToWishlist = false;
                    ProductDetailsActivity.this.wishListButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#BABABA")));
                } else {
                    Map<String, Object> productId = new HashMap<>();
                    productId.put("product_id_" + String.valueOf(DBqueries.wishList.size()), ProductDetailsActivity.this.product_id);
                    ProductDetailsActivity.this.firebaseFirestore.collection("USERS").document(DBqueries.currentUser.getUid()).collection("USER_DATA").document("MY_WISHLIST").set(productId).addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(Task<Void> task1) {
                            if (task1.isSuccessful()) {
                                Map<String, Object> updateListSize = new HashMap<>();
                                updateListSize.put("list_size", Long.valueOf((long) DBqueries.wishList.size()));
                                ProductDetailsActivity.this.firebaseFirestore.collection("USERS").document(DBqueries.currentUser.getUid()).collection("USER_DATA").document("My_WISHLIST").update(updateListSize).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(Task<Void> task1) {
                                        if (task1.isSuccessful()) {
                                            boolean unused = ProductDetailsActivity.already_addedToWishlist = true;
                                            ProductDetailsActivity.this.wishListButton.setSupportImageTintList(ProductDetailsActivity.this.getResources().getColorStateList(C0181R.C0182color.failred));
                                            DBqueries.wishList.add(ProductDetailsActivity.this.product_id);
                                            return;
                                        }
                                        Toast.makeText(ProductDetailsActivity.this, task1.getException().getMessage(), 0).show();
                                    }
                                });
                                return;
                            }
                            Toast.makeText(ProductDetailsActivity.this, task1.getException().getMessage(), 0).show();
                        }
                    });
                }
            }
        });
        this.buyNowBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DBqueries.currentUser == null) {
                    ProductDetailsActivity.this.signInDialog.show();
                    return;
                }
                ProductDetailsActivity.this.startActivity(new Intent(ProductDetailsActivity.this, DeliveryActivity.class));
            }
        });
        this.addtocartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DBqueries.currentUser == null) {
                    ProductDetailsActivity.this.signInDialog.show();
                }
            }
        });
        Dialog dialog = new Dialog(this);
        this.signInDialog = dialog;
        dialog.setContentView(C0181R.C0185layout.sign_in_diolog);
        this.signInDialog.setCancelable(true);
        this.signInDialog.getWindow().setLayout(-1, -2);
        final Intent registerIntent = new Intent(this, RegisterActivity.class);
        ((Button) this.signInDialog.findViewById(C0181R.C0184id.signInBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProductDetailsActivity.this.signInDialog.dismiss();
                RegisterActivity.setSignUpFragment = false;
                ProductDetailsActivity.this.startActivity(registerIntent);
            }
        });
        ((Button) this.signInDialog.findViewById(C0181R.C0184id.signUpBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProductDetailsActivity.this.signInDialog.dismiss();
                RegisterActivity.setSignUpFragment = true;
                ProductDetailsActivity.this.startActivity(registerIntent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0181R.C0186menu.search_cart_icon_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 16908332) {
            finish();
            return true;
        } else if (id == C0181R.C0184id.action_search_icon) {
            return true;
        } else {
            if (id == C0181R.C0184id.action_cart_icon) {
                if (DBqueries.currentUser == null) {
                    this.signInDialog.show();
                } else {
                    startActivity(new Intent(this, MyCartFragment.class));
                    return true;
                }
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
