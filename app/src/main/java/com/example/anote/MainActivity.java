package com.example.anote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.anote.AccountPage.AccountFragment;
import com.example.anote.CategoryPage.CategoryFragment;
import com.example.anote.HomePage.HomeFragment;
import com.example.anote.Objects.HandNote;
import com.example.anote.SearchPage.SearchFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<HandNote> handNotes = new ArrayList<>();

    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private AccountFragment accountFragment;
    private SearchFragment searchFragment;
    private ActionBar actionBar;
    private Toolbar toolbar;

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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_navigation_bar, R.string.close_navigation_bar) {
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

    private void fetchData(){
        handNotes.add(new HandNote("مهندسی اینترنت", "فروزش", "شهید چمران", R.color.colorPrimaryLight));
        handNotes.add(new HandNote("ریاضی 2", "کاظمی فر", "شهید چمران", R.color.colorSecondry));
        handNotes.add(new HandNote("شیمی", "جعفری", "شهید چمران", R.color.colorPrimaryLight));
        handNotes.add(new HandNote("برنامه نویسی", "صفار زاده", "شهید چمران", R.color.colorSecondry));
        handNotes.add(new HandNote("آنالیز عددی", "صالح نژاد", "شهید چمران", R.color.colorPrimaryLight));
        handNotes.add(new HandNote("سیستم عامل", "طاهری", "شهید چمران", R.color.colorSecondry));
        handNotes.add(new HandNote("مهندسی اینترنت", "فروزش", "شهید چمران", R.color.colorPrimaryLight));
        handNotes.add(new HandNote("ریاضی 2", "کاظمی فر", "شهید چمران", R.color.colorSecondry));
        handNotes.add(new HandNote("برنامه نویسی", "صفار زاده", "شهید چمران", R.color.colorPrimaryLight));
    }

}
