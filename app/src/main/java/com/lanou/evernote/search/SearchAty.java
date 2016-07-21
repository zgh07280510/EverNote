package com.lanou.evernote.search;

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
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;
import com.lanou.evernote.tools.DisplayUtil;


/**
 * Created by zouguohua on 16/7/19.
 */
public class SearchAty extends BaseActivity implements View.OnClickListener {
    private ListView searchRecodeListView;
    private Button btnBack, btnAccurateSearch;
    private EditText searchEt;
    private LinearLayout llSearch,llGreen;
    private PopupWindow popupWindow;
    private ImageView ivClear;


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
        llGreen = (LinearLayout) findViewById(R.id.ll_search_green);
        ivClear = (ImageView) findViewById(R.id.iv_clear_search);
        popupWindow = new PopupWindow();
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
        btnBack.setOnClickListener(this);
        ivClear.setOnClickListener(this);
        searchEt.setOnClickListener(this);
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
            case R.id.iv_clear_search:

                break;
            case R.id.btn_use_filter:
                popupWindow.dismiss();
                btnAccurateSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_note:
            showPopupWindow();
                break;
            case R.id.et_search:
                if (searchEt != null){
                    ivClear.setVisibility(View.VISIBLE);
                }else {
                    ivClear.setVisibility(View.GONE);
                }
                break;
        }
    }


    public void initPopupWindow() {
        Button btnUseFilter,btnRemoveFilter;
        LinearLayout llNote;
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        View popupView = LayoutInflater.from(this).inflate(R.layout.accurate_search_popupwindown, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, displayMetrics.heightPixels);
        popupWindow.setContentView(popupView);
        popupWindow.showAsDropDown(btnAccurateSearch);
        btnUseFilter = (Button) popupView.findViewById(R.id.btn_use_filter);
        btnRemoveFilter = (Button) popupView.findViewById(R.id.btn_remove_filter);
        llNote = (LinearLayout)popupView.findViewById(R.id.ll_note);
        btnUseFilter.setOnClickListener(this);
        llNote.setOnClickListener(this);
    }
public void  showPopupWindow(){
    WindowManager windowManager = getWindowManager();
    Display display = windowManager.getDefaultDisplay();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
    View view= LayoutInflater.from(this).inflate(R.layout.note_popupwindow,null);
    popupWindow = new PopupWindow(view,displayMetrics.widthPixels/5*4,displayMetrics.heightPixels/4*3);
    popupWindow.setContentView(view);
    popupWindow.showAsDropDown(searchEt);
}
}
