package com.lanou.evernote.homepage;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;

/**
 * Created by zouguohua on 16/7/19.
 */
public class HomepageAty extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        MenuItem.OnMenuItemClickListener {
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private DrawerLayout drawer;


    @Override
    public int setLayout() {

        return R.layout.activity_home_activtiy;

    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    }

    @Override
    protected void initData() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_to_screen) {
            return true;
        }
        if (id == R.id.action_choose_note){
            return true;
        }
        if (id == R.id.setting){
            return true;
        }
        if (id == R.id.sort_ways){
            return true;
        }
        if (id == R.id.synchronize){
            return true;
        }
        if (id == R.id.view_options){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setOnMenuItemClickListener(this);

        if (id == R.id.group_chat) {
            // Handle the camera action

        } else if (id == R.id.all_notes) {

        } else if (id == R.id.note_book) {

        } else if (id == R.id.search_notes) {

        } else if (id == R.id.note_setting) {

        }

        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //  drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
