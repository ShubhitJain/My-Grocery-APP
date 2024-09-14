package com.example.mygrocery.utility;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocery.Adapters.CategoryAdapter;
import com.example.mygrocery.Adapters.HomePageAdapter;
import com.example.mygrocery.Models.CategoryModel;
import com.example.mygrocery.Models.HomePageModel;
import com.example.mygrocery.Models.HorizontalProductScrollModel;
import com.example.mygrocery.Models.MyWishListModel;
import com.example.mygrocery.Models.SlderModel;
import com.example.mygrocery.views.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.example.mygrocery.DB.DBqueries */
public class DBqueries {
    public static FirebaseUser currentUser;
    public static FirebaseAuth firebaseAuth;
    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<List<HomePageModel>> lists = new ArrayList();
    public static List<String> loadedCategoriesName = new ArrayList();
    public static List<CategoryModel> modelList = new ArrayList();
    public static List<String> wishList = new ArrayList();

    static {
        FirebaseAuth instance = FirebaseAuth.getInstance();
        firebaseAuth = instance;
        currentUser = instance.getCurrentUser();
    }

    public static void loadCategories(final RecyclerView categoryRecyclerView, final Context context) {
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Iterator<QueryDocumentSnapshot> it = task.getResult().iterator();
                    while (it.hasNext()) {
                        QueryDocumentSnapshot documentSnapshot = it.next();
                        DBqueries.modelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryname").toString()));
                    }
                    CategoryAdapter categoryAdapter = new CategoryAdapter(DBqueries.modelList);
                    RecyclerView.this.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                    return;
                }
                Toast.makeText(context, task.getException().getMessage(), 0).show();
            }
        });
    }

    public static void setFragmentData(final HomePageAdapter homePageAdapter, final Context context, final int index, String categoryName) {
        firebaseFirestore.collection("CATEGORIES").document(categoryName).collection("TOP_DEALS").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                Iterator<QueryDocumentSnapshot> it;
                int i = 0;
                if (task.isSuccessful()) {
                    Iterator<QueryDocumentSnapshot> it2 = task.getResult().iterator();
                    while (it2.hasNext()) {
                        QueryDocumentSnapshot documentSnapshot = it2.next();
                        if (((Long) documentSnapshot.get("view_type")).longValue() == 0) {
                            List<SlderModel> slderModelList = new ArrayList<>();
                            long no_of_banners = ((Long) documentSnapshot.get("no_of_banners")).longValue();
                            for (long x = 1; x < no_of_banners + 1; x++) {
                                slderModelList.add(new SlderModel(documentSnapshot.get("banner_" + x).toString(), documentSnapshot.get("banner_" + x + "_bg").toString()));
                            }
                            DBqueries.lists.get(index).add(new HomePageModel(i, slderModelList));
                            it = it2;
                        } else if (((Long) documentSnapshot.get("view_type")).longValue() == 1) {
                            DBqueries.lists.get(index).add(new HomePageModel(1, documentSnapshot.get("strip_add_banner").toString(), documentSnapshot.get("background").toString()));
                            it = it2;
                        } else if (((Long) documentSnapshot.get("view_type")).longValue() == 2) {
                            List<MyWishListModel> viewAllProductList = new ArrayList<>();
                            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                            long no_of_products = ((Long) documentSnapshot.get("no_of_products")).longValue();
                            long x2 = 1;
                            while (x2 < no_of_products) {
                                horizontalProductScrollModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_id_" + x2).toString(), documentSnapshot.get("product_image_" + x2).toString(), documentSnapshot.get("product_title_" + x2).toString(), documentSnapshot.get("product_description_" + x2).toString(), documentSnapshot.get("product_price_" + x2).toString()));
                                viewAllProductList.add(new MyWishListModel(documentSnapshot.get("product_image_" + x2).toString(), documentSnapshot.get("product_full_title_" + x2).toString(), ((Long) documentSnapshot.get("free_coupens_" + x2)).longValue(), documentSnapshot.get("avg_rating_" + x2).toString(), ((Long) documentSnapshot.get("total_ratings_" + x2)).longValue(), documentSnapshot.get("product_price_" + x2).toString(), documentSnapshot.get("product_cutted_price_" + x2).toString(), ((Boolean) documentSnapshot.get("COD_" + x2)).booleanValue()));
                                x2++;
                                it2 = it2;
                            }
                            it = it2;
                            DBqueries.lists.get(index).add(new HomePageModel(2, documentSnapshot.get("layout_title").toString(), documentSnapshot.get("layout_background").toString(), horizontalProductScrollModelList, viewAllProductList));
                        } else {
                            it = it2;
                            if (((Long) documentSnapshot.get("view_type")).longValue() == 3) {
                                List<HorizontalProductScrollModel> gridProductScrollModelList = new ArrayList<>();
                                long no_of_products2 = ((Long) documentSnapshot.get("no_of_products")).longValue();
                                for (long x3 = 1; x3 < no_of_products2; x3++) {
                                    gridProductScrollModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_id_" + x3).toString(), documentSnapshot.get("product_image_" + x3).toString(), documentSnapshot.get("product_title_" + x3).toString(), documentSnapshot.get("product_description_" + x3).toString(), documentSnapshot.get("product_price_" + x3).toString()));
                                }
                                DBqueries.lists.get(index).add(new HomePageModel(3, documentSnapshot.get("layout_title").toString(), documentSnapshot.get("layout_background").toString(), gridProductScrollModelList));
                            }
                        }
                        it2 = it;
                        i = 0;
                    }
                    homePageAdapter.notifyDataSetChanged();
                    HomeFragment.swipeRefreshLayout.setRefreshing(false);
                    return;
                }
                Toast.makeText(context, task.getException().getMessage(), 0).show();
            }
        });
    }

    public static void loadWishList(final Context context) {
        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("MY_WISHLIST").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            public void onComplete(Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    for (long x = 0; x <= ((Long) task.getResult().get("list_size")).longValue(); x++) {
                        DBqueries.wishList.add(task.getResult().get("product_id_" + x).toString());
                    }
                    return;
                }
                Toast.makeText(context, task.getException().getMessage(), 0).show();
            }
        });
    }
}
