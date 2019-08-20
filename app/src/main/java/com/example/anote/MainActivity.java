package com.example.anote;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.anote.AccountPage.AccountFragment;
import com.example.anote.CategoryPage.CategoryFragment;
import com.example.anote.HomePage.HomeFragment;
import com.example.anote.Objects.HandNote;
import com.example.anote.SearchPage.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "Tag_559898s4f89a4f";

    private static ArrayList<HandNote> handNotes = new ArrayList<>();

    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private AccountFragment accountFragment;
    private SearchFragment searchFragment;

    public static ArrayList<HandNote> getHandNotes() {
        return handNotes;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(homeFragment);
//                    hideKeyboard();
                    return true;
                case R.id.navigation_category:
                    setFragment(categoryFragment);
                    return true;
                case R.id.navigation_account:
                    setFragment(accountFragment);
                    return true;
                case R.id.navigation_search:
                    setFragment(searchFragment);
//                    showKeyboard();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchData();
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        accountFragment = new AccountFragment();
        searchFragment = new SearchFragment();
        setFragment(homeFragment);

        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initNavigationMenu();   // set up Drawer
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }

    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.open_navigation_bar, R.string.close_navigation_bar) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
//                actionBar.setTitle(item.getTitle());
                drawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
//        drawer.openDrawer(GravityCompat.START);
    }

    private void fetchData() {

        handNotes.add(new HandNote("مهندسی اینترنت", "فروزش", "شهید چمران", R.drawable.ic_mathematical_icon));
        handNotes.add(new HandNote("ریاضی 2", "کاظمی فر", "شهید چمران", R.drawable.ic_statistics));
        handNotes.add(new HandNote("شیمی", "جعفری", "شهید چمران", R.drawable.ic_database));
        handNotes.add(new HandNote("برنامه نویسی", "صفار زاده", "شهید چمران", R.drawable.ic_mathematical_icon));
        handNotes.add(new HandNote("آنالیز عددی", "صالح نژاد", "شهید چمران", R.drawable.ic_statistics));
        handNotes.add(new HandNote("سیستم عامل", "طاهری", "شهید چمران", R.drawable.ic_database));
        handNotes.add(new HandNote("مهندسی اینترنت", "فروزش", "شهید چمران", R.drawable.ic_mathematical_icon));
        handNotes.add(new HandNote("ریاضی 2", "کاظمی فر", "شهید چمران", R.drawable.ic_statistics));
        handNotes.add(new HandNote("برنامه نویسی", "صفار زاده", "شهید چمران", R.drawable.ic_database));
    }

}
