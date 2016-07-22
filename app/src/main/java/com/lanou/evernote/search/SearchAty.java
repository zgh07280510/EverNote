package com.lanou.evernote.search;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private ArrayList<LocalData> searchHistory;
    private String svContent;
    private LocalData localData;
    private String etContent;
    private TextView tvFactor;
    private View view;

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
        view = LayoutInflater.from(this).inflate(R.layout.note_popupwindow, null);
        tvFactor = (TextView) view.findViewById(R.id.tv_factor_pop);

        localData = new LocalData();
        searchHistory = new ArrayList<>();
        for (int i = 0; i < searchHistory.size(); i++) {
            searchHistory.add(localData);
        }
        searchRecodeListView.setTextFilterEnabled(true);
        svSearch.setIconifiedByDefault(false);
        svSearch.setQueryHint("搜索笔记");

        svSearch.setSubmitButtonEnabled(true);
        btnAccurateSearch.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        svSearch.setOnClickListener(this);

        searchRecodeListView.setAdapter(new ListViewCommonAdapter<LocalData>(this, searchHistory, R.layout.history_item_list) {

            @Override
            public void convert(ViewHolder holder, LocalData localData) {
              holder.setText(R.id.tv_history,localData.getEtContent());
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
                if (TextUtils.isEmpty(newText)) {
                    searchRecodeListView.clearTextFilter();
                } else {
                    searchRecodeListView.setFilterText(newText);
//                    etContent = newText;

                    localData.setEtContent(newText);
                    SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(localData);
                    Log.d("SearchAty", newText);
                    searchHistory = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(LocalData.class);
                    Log.d("SearchAty", "searchHistory:" + searchHistory);
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

                tvFactor.setText("笔记本");

                break;

            case R.id.ll_label:
                tvFactor.setText("标签");
                showPopupWindow();
                break;
            case R.id.ll_date:
                tvFactor.setText("日期");
                showPopupWindow();
                break;
            case R.id.ll_address:
                tvFactor.setText("地点");
                showPopupWindow();
                break;
            case R.id.ll_source_type:
                tvFactor.setText("来源和类型");
                showPopupWindow();
                break;
            case R.id.ll_attachment:
                tvFactor.setText("附件");
                showPopupWindow();
                break;
            case R.id.ll_todo:
                tvFactor.setText("待办事项");
                showPopupWindow();
                break;
            case R.id.btn_note_pop_clear:
                popupWindow.dismiss();
                break;
            case R.id.btn_note_pop_use:
                popupWindow.dismiss();
                break;
        }
    }


    public void initPopupWindow() {
        Button btnUseFilter, btnRemoveFilter;
        LinearLayout llNote, llLabel, llDate, llAddress, llSource, llAttachment, llTodo;
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
        llLabel = (LinearLayout) popupView.findViewById(R.id.ll_label);
        llDate = (LinearLayout) popupView.findViewById(R.id.ll_date);
        llAddress = (LinearLayout) popupView.findViewById(R.id.ll_address);
        llSource = (LinearLayout) popupView.findViewById(R.id.ll_source_type);
        llAttachment = (LinearLayout) popupView.findViewById(R.id.ll_attachment);
        llTodo = (LinearLayout) popupView.findViewById(R.id.ll_todo);
        btnUseFilter.setOnClickListener(this);
        llNote.setOnClickListener(this);
        llLabel.setOnClickListener(this);
        llDate.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        llSource.setOnClickListener(this);
        llAttachment.setOnClickListener(this);
        llTodo.setOnClickListener(this);
    }

    public void showPopupWindow() {
        WindowManager noteWindowManager = getWindowManager();
        Display display = noteWindowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        popupWindow = new PopupWindow(view, displayMetrics.widthPixels / 5 * 4, displayMetrics.heightPixels / 4 * 3);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(svSearch);
        Button btnClear = (Button) view.findViewById(R.id.btn_note_pop_clear);
        Button btnUse = (Button) view.findViewById(R.id.btn_note_pop_use);
        ListView listView = (ListView) view.findViewById(R.id.factor_listView);

        btnClear.setOnClickListener(this);
        btnUse.setOnClickListener(this);

    }
}
