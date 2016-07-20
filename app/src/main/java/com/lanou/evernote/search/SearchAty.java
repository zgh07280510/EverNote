package com.lanou.evernote.search;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;


/**
 * Created by zouguohua on 16/7/19.
 */
public class SearchAty extends BaseActivity implements View.OnClickListener {
    private ListView searchRecodeListView;
    private Button btnBack, btnAccurateSearch;
    private EditText searchEt;
    private LinearLayout llSearch;
    private PopupWindow popupWindow;


    @Override
    public int setLayout() {

        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        searchRecodeListView = (ListView) findViewById(R.id.search_listView);
        btnBack = (Button) findViewById(R.id.btn_search_back);
        searchEt = (EditText) findViewById(R.id.et_search);
        llSearch = (LinearLayout) findViewById(R.id.ll_search);
        btnAccurateSearch = (Button) findViewById(R.id.btn_accurate_search);
    }

    @Override
    protected void initData() {
        searchEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if ("".equals(searchEt.getText().toString())) {
                        llSearch.setVisibility(View.VISIBLE);
                    }
                } else {
                    llSearch.setVisibility(View.GONE);
                }
            }
        });
        btnAccurateSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accurate_search:
                initPopupWindow();
                break;
        }
    }

    public void initPopupWindow() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        View popupView = LayoutInflater.from(this).inflate(R.layout.accurate_search_popupwindown, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, displayMetrics.heightPixels);
        popupWindow.setContentView(popupView);

    }
}
