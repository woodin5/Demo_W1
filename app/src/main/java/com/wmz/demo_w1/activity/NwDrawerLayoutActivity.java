package com.wmz.demo_w1.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;

import butterknife.BindView;

public class NwDrawerLayoutActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.nw_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nw_nav)
    NavigationView navigationView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_nw_drawerlayout;
    }

    @Override
    protected void initData() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(NwDrawerLayoutActivity.this,drawerLayout,toolbar,R.string.show,R.string.show);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_camera:
                Toast.makeText(NwDrawerLayoutActivity.this,"nav_camera",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
