package com.lanou.evernote.homepage;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;

import com.lanou.evernote.base.ListViewCommonAdapter;
import com.lanou.evernote.base.ViewHolder;
import com.lanou.evernote.search.SearchAty;
import com.lanou.evernote.tools.BitmapToByte;
import com.lanou.evernote.tools.SingleLiteOrm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by zouguohua on 16/7/19.
 */
public class HomepageAty extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        MenuItem.OnMenuItemClickListener, View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FloatingActionMenu mFloatingActionMenu;
    private FloatingActionButton mCameraFab;
    private FloatingActionButton mAccessoryFab;
    private FloatingActionButton mChatFab;
    private FloatingActionButton mWriteFab;
    private FloatingActionButton mRemindFab;
    private AllNoteFragment allNoteFragment;
    private NoteBookFragment noteBookFragment;
    private Bitmap bitmap;
    private BitmapToByte bitmapToByte;

    @Override
    public int setLayout() {

        return R.layout.activity_home_activtiy;

    }

    @Override
    protected void initView() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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


    }


    @Override
    protected void initData() {
        allNoteFragment = new AllNoteFragment();
        noteBookFragment = new NoteBookFragment();
        bitmapToByte = new BitmapToByte();
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

        int id = item.getItemId();


        if (id == R.id.add_to_screen) {
            return true;
        }
        if (id == R.id.action_choose_note) {
            return true;
        }
        if (id == R.id.setting) {
            return true;
        }
        if (id == R.id.sort_ways) {
            return true;
        }
        if (id == R.id.synchronize) {
            return true;
        }
        if (id == R.id.view_options) {
            return true;
        }
        if (id == R.id.search_tool) {
            Intent intent = new Intent(HomepageAty.this, SearchAty.class);
            startActivity(intent);
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
            getSupportFragmentManager().beginTransaction().replace(R.id.homepage_framlayout, allNoteFragment)
                    .commit();
            drawer.closeDrawers();

        } else if (id == R.id.note_book) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homepage_framlayout, noteBookFragment)
                    .commit();
            drawer.closeDrawers();
        } else if (id == R.id.search_notes) {

        } else if (id == R.id.note_setting) {

        }

        //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //  drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_fab_camrea:

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory("android.intent.category.DEFAULT");
                startActivityForResult(intent, 1);

                break;

            case R.id.main_fab_accessory:

                break;
            case R.id.main_fab_chat:

                break;

            case R.id.main_fab_no_remind:

                break;
            case R.id.main_fab_sprit:

                break;
        }
        mFloatingActionMenu.toggle(false);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String sdStaUs = Environment.getExternalStorageState();
            //检测sd是否可用
            if (!sdStaUs.equals(Environment.MEDIA_MOUNTED)) {

                return;
            }
            String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");//获取相机返回的数据,并转为Bitmap图片格式

            bitmapToByte.setImage(bitmap);
            SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(bitmapToByte);
            Log.d("HomepageAty", "bitmapToByte.getImage():" + bitmapToByte.getImage()+" ");
            FileOutputStream b = null;
            File file = new File("/sdcard/myImage");
            file.mkdirs();//创建文件夹
            String fileName = "/sdcard/myImage" + name;


            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);//将流写入文件

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
