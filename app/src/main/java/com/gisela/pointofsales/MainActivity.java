package com.gisela.pointofsales;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gisela.pointofsales.Adapter.BarangAdapter;
import com.gisela.pointofsales.Adapter.UserAdapter;
import com.gisela.pointofsales.entity.Barang;
import com.gisela.pointofsales.entity.User;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,UserAdapter.UserDataListener,
        BarangAdapter.BarangDataListener {

    private UserAdapter userAdapter;
    private UserListFragment userListFragment;

    private BarangAdapter barangAdapter;
    private BarangListFragment barangListFragment;

    public BarangListFragment getBarangListFragment() {
        if(barangListFragment == null){
            barangListFragment = new BarangListFragment();
            barangListFragment.getBarangAdapter().setBarangDataClickedListener(this);
        }
        return barangListFragment;
    }

    public UserListFragment getUserListFragment() {
        if(userListFragment == null){
            userListFragment = new UserListFragment();
            userListFragment.getUserAdapter().setUserDataClickedListener(this);
        }
        return userListFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.finish();
            MainActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kasir) {

        } else if (id == R.id.nav_laporan) {

        } else if (id == R.id.nav_user) {
            UserFragment userFragment = new UserFragment();
            FragmentTransaction userTransaction = getSupportFragmentManager().beginTransaction();
            userTransaction.replace(R.id.frame_for_fragment_left, userFragment);
            userTransaction.replace(R.id.frame_for_fragment_right, getUserListFragment());
            userTransaction.commit();

        } else if (id == R.id.nav_barang) {
            BarangFragment barangFragment = new BarangFragment();
            FragmentTransaction barangTransaction = getSupportFragmentManager().beginTransaction();
            barangTransaction.replace(R.id.frame_for_fragment_left, barangFragment);
            barangTransaction.replace(R.id.frame_for_fragment_right, getBarangListFragment());
            barangTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //RECYCLE VIEW
    public UserAdapter getUserAdapter() {
        if(userAdapter == null){
            userAdapter = new UserAdapter();
            userAdapter.setUserDataClickedListener(this);
        }
        return userAdapter;
    }
    public BarangAdapter getBarangAdapter() {
        if (barangAdapter == null) {
            barangAdapter = new BarangAdapter();
            barangAdapter.setBarangDataClickedListener(this);
        }
        return barangAdapter;
    }

    @Override
    public void onUserClicked(User user) {
        if (findViewById(R.id.frame_for_fragment_left) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(UserFragment.ARG_USER, user);
            UserFragment fragment = new UserFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_for_fragment_left, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onBarangClicked(Barang barang) {
        if (findViewById(R.id.frame_for_fragment_left) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(BarangFragment.ARG_BARANG, barang);
            BarangFragment fragment = new BarangFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_for_fragment_left, fragment);
            transaction.commit();
        }
    }
}
