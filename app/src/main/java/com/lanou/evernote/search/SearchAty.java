package com.lanou.evernote.search;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zouguohua on 16/7/19.
 */
public class SearchAty extends BaseActivity {


    @BindView(R.id.btn_search_back)
    Button btnSearchBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.search_listView)
    ListView searchListView;

    @Override
    public int setLayout() {

        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }




    @OnClick(R.id.btn_search_back)
    public void onClick() {
       finish();
    }
}
