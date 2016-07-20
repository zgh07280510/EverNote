package com.lanou.evernote.homepage;


import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;
import com.lanou.evernote.base.ListViewCommonAdapter;
import com.lanou.evernote.base.ViewHolder;

import java.util.ArrayList;

/**
 * Created by zouguohua on 16/7/19.
 */
public class HomepageAty extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        MenuItem.OnMenuItemClickListener,View.OnClickListener {
    private MultipleStatusView multipleStatusView;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FloatingActionMenu mFloatingActionMenu;
    private FloatingActionButton mCameraFab;
    private FloatingActionButton mAccessoryFab;
    private FloatingActionButton mChatFab;
    private FloatingActionButton mWriteFab;
    private FloatingActionButton mRemindFab;
    private ListView listView;
    private ArrayList<String> data;






    @Override
    public int setLayout() {

        return R.layout.activity_home_activtiy;

    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.list_view);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        multipleStatusView = (MultipleStatusView) findViewById(R.id.main_multiplestatusview);
        mFloatingActionMenu = (FloatingActionMenu) findViewById(R.id.main_fab_menu);
        mCameraFab = (FloatingActionButton) findViewById(R.id.main_fab_camrea);
        mAccessoryFab = (FloatingActionButton) findViewById(R.id.main_fab_accessory);
        mChatFab = (FloatingActionButton) findViewById(R.id.main_fab_chat);
        mWriteFab = (FloatingActionButton) findViewById(R.id.main_fab_sprit);
        mRemindFab = (FloatingActionButton) findViewById(R.id.main_fab_no_remind);
        mCameraFab.setOnClickListener(this);
        mAccessoryFab.setOnClickListener(this);
        mChatFab.setOnClickListener(this);
        mWriteFab.setOnClickListener(this);
        mRemindFab.setOnClickListener(this);


        multipleStatusView.setOnRetryClickListener(onRetryClickListener);
       // multipleStatusView.showLoading();

    }
    private final View.OnClickListener onRetryClickListener = new View.OnClickListener() {
        @Override public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"您点击了重试视图",Toast.LENGTH_SHORT).show();
            multipleStatusView.showLoading();
        }
    };

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            data.add("halou"+i);
        }
        listView.setAdapter(new ListViewCommonAdapter<String>(this,data,R.layout.list_item_hompage) {

            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.text_,s);
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

    @Override
    public void onClick(View v) {



        mFloatingActionMenu.toggle(false);

    }
}
