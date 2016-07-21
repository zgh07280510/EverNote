package com.lanou.evernote.search;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;
import com.lanou.evernote.base.ListViewCommonAdapter;
import com.lanou.evernote.base.ViewHolder;
import com.lanou.evernote.tools.DisplayUtil;
import com.lanou.evernote.tools.LocalData;
import com.lanou.evernote.tools.SingleLiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;


/**
 * Created by zouguohua on 16/7/19.
 */
public class SearchAty extends BaseActivity implements View.OnClickListener {
    private ListView searchRecodeListView;
    private Button btnBack, btnAccurateSearch;
    private SearchView svSearch;
    private LinearLayout llGreen;
    private PopupWindow popupWindow;
    private ArrayList<LocalData> datas;
    private String svContent;



    @Override
    public int setLayout() {

        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        searchRecodeListView = (ListView) findViewById(R.id.search_listView);
        btnBack = (Button) findViewById(R.id.btn_search_back);
        svSearch = (SearchView) findViewById(R.id.sv_search);
         btnAccurateSearch = (Button) findViewById(R.id.btn_accurate_search);
        llGreen = (LinearLayout) findViewById(R.id.ll_search_green);
        popupWindow = new PopupWindow();
    }

    @Override
    protected void initData() {
        searchRecodeListView.setTextFilterEnabled(true);
        svSearch.setIconifiedByDefault(false);
        svSearch.setQueryHint("搜索笔记");

        svSearch.setSubmitButtonEnabled(true);
        btnAccurateSearch.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        svSearch.setOnClickListener(this);
        searchRecodeListView.setAdapter(new ListViewCommonAdapter<LocalData>(this,datas, R.layout.history_item_list) {

            @Override
            public void convert(ViewHolder holder, LocalData localData) {

            }

            
        });
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchAty.this, "您的选择是" + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    searchRecodeListView.clearTextFilter();
                }else {
                    searchRecodeListView.setFilterText(newText);
        SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(newText);
//                   QueryBuilder<>()
//                    SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query("");
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accurate_search:
                initPopupWindow();
                btnAccurateSearch.setVisibility(View.GONE);
                break;
            case R.id.btn_search_back:
                finish();
                break;

            case R.id.btn_use_filter:
                popupWindow.dismiss();
                btnAccurateSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_note:
                showPopupWindow();
                break;
            case R.id.sv_search:

                break;
        }
    }


    public void initPopupWindow() {
        Button btnUseFilter, btnRemoveFilter;
        LinearLayout llNote;
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        View popupView = LayoutInflater.from(this).inflate(R.layout.accurate_search_popupwindown, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, displayMetrics.heightPixels);
        popupWindow.setContentView(popupView);
        popupWindow.showAsDropDown(llGreen, DisplayUtil.px2dip(this, 0), DisplayUtil.px2dip(this, 100));
        btnUseFilter = (Button) popupView.findViewById(R.id.btn_use_filter);
        btnRemoveFilter = (Button) popupView.findViewById(R.id.btn_remove_filter);
        llNote = (LinearLayout) popupView.findViewById(R.id.ll_note);
        btnUseFilter.setOnClickListener(this);
        llNote.setOnClickListener(this);
    }

    public void showPopupWindow() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        View view = LayoutInflater.from(this).inflate(R.layout.note_popupwindow, null);
        popupWindow = new PopupWindow(view, displayMetrics.widthPixels / 5 * 4, displayMetrics.heightPixels / 4 * 3);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(svSearch);
    }
}
