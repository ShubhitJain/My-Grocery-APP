package com.example.mygrocery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int Cart_Fragment = 1;
    private static final int Home_Fragment = 0;
    private static final int MyAccount_Fragment = 5;
    private static final int Order_Fragment = 2;
    private static final int Rewards_Fragment = 4;
    private static final int Wishlist_Fragment = 3;
    private static int current_fragment = -1;
    private ImageView ActionbarLogo;
    private AppBarConfiguration mAppBarConfiguration;
    FrameLayout main_frameLayout;
    private NavigationView navigationView;
    /* access modifiers changed from: private */
    public Dialog signInDialog;
    private Toolbar toolbar;
    private Window window;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0181R.C0185layout.activity_main);
        this.toolbar = (Toolbar) findViewById(C0181R.C0184id.toolbar);
        this.ActionbarLogo = (ImageView) findViewById(C0181R.C0184id.main_actionBar_logo);
        setSupportActionBar(this.toolbar);
        Window window2 = getWindow();
        this.window = window2;
        window2.addFlags(Integer.MIN_VALUE);
        DrawerLayout drawer = (DrawerLayout) findViewById(C0181R.C0184id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, this.toolbar, C0181R.string.navigation_drawer_open, C0181R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView2 = (NavigationView) findViewById(C0181R.C0184id.nav_view);
        this.navigationView = navigationView2;
        navigationView2.setNavigationItemSelectedListener(this);
        this.navigationView.getMenu().getItem(0).setChecked(true);
        this.main_frameLayout = (FrameLayout) findViewById(C0181R.C0184id.main_framelayout);
        setFragment(new HomeFragment(), 0);
        if (DBqueries.currentUser == null) {
            this.navigationView.getMenu().getItem(this.navigationView.getMenu().size() - 1).setEnabled(false);
        } else {
            this.navigationView.getMenu().getItem(this.navigationView.getMenu().size() - 1).setEnabled(true);
        }
        Dialog dialog = new Dialog(this);
        this.signInDialog = dialog;
        dialog.setContentView(C0181R.C0185layout.sign_in_diolog);
        this.signInDialog.setCancelable(true);
        this.signInDialog.getWindow().setLayout(-1, -2);
        final Intent registerIntent = new Intent(this, RegisterActivity.class);
        ((Button) this.signInDialog.findViewById(C0181R.C0184id.signInBtn)).setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            MainActivity.this.signInDialog.dismiss();
            RegisterActivity.setSignUpFragment = false;
            MainActivity.this.startActivity(registerIntent);
        }
    });
        ((Button) this.signInDialog.findViewById(C0181R.C0184id.signUpBtn)).setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            MainActivity.this.signInDialog.dismiss();
            RegisterActivity.setSignUpFragment = true;
            MainActivity.this.startActivity(registerIntent);
        }
    });
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(C0181R.C0184id.drawer_layout);
        if (drawer.isDrawerOpen((int) GravityCompat.START)) {
            drawer.closeDrawer((int) GravityCompat.START);
        } else if (current_fragment == 0) {
            super.onBackPressed();
        } else {
            this.ActionbarLogo.setVisibility(0);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(), 0);
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (current_fragment != 0) {
            return true;
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getMenuInflater().inflate(C0181R.C0186menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == C0181R.C0184id.action_search_icon || id == C0181R.C0184id.action_notification_icon) {
            return true;
        }
        if (id == C0181R.C0184id.action_cart_icon) {
            if (DBqueries.currentUser == null) {
                this.signInDialog.show();
            } else {
                goToFragment("My Cart", new MyCartFragment(), 1);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToFragment(String title, Fragment fragment, int fragmentNum) {
        this.ActionbarLogo.setVisibility(8);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle((CharSequence) title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNum);
        if (fragmentNum == 1) {
            this.navigationView.getMenu().getItem(3).setChecked(true);
        }
    }

    public void setFragment(Fragment fragment, int fragmentNum) {
        if (fragmentNum != current_fragment) {
            if (fragmentNum == 4) {
                this.window.setStatusBarColor(Color.parseColor("#5B04B1"));
                this.toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
            } else {
                this.window.setStatusBarColor(getResources().getColor(C0181R.C0182color.failred));
                this.toolbar.setBackgroundColor(getResources().getColor(C0181R.C0182color.failred));
            }
            current_fragment = fragmentNum;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(C0181R.anim.fade_in, C0181R.anim.fade_out);
            fragmentTransaction.replace(this.main_frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(C0181R.C0184id.drawer_layout);
        if (DBqueries.currentUser != null) {
            int id = item.getItemId();
            if (id == C0181R.C0184id.nav_mall) {
                this.ActionbarLogo.setVisibility(0);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), 0);
            } else if (id == C0181R.C0184id.nav_orders) {
                goToFragment("My Orders", new MyOrdersFragment(), 2);
            } else if (id == C0181R.C0184id.nav_rewards) {
                goToFragment("My Rewards", new MyRewardsFragment(), 4);
            } else if (id == C0181R.C0184id.nav_cart) {
                goToFragment("My Cart", new MyCartFragment(), 1);
            } else if (id == C0181R.C0184id.nav_wishlist) {
                goToFragment("My WishList", new MywishListFragment(), 3);
            } else if (id == C0181R.C0184id.nav_account) {
                goToFragment("My ACCOUNT ", new MyAccountFragment(), 5);
            } else if (id == C0181R.C0184id.nav_signOut) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
            }
            drawerLayout.closeDrawer((int) GravityCompat.START);
            return true;
        }
        this.signInDialog.show();
        drawerLayout.closeDrawer((int) GravityCompat.START);
        return false;
    }
}