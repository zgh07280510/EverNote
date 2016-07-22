package com.lanou.evernote.homepage;

import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseFragment;
import com.lanou.evernote.base.ListViewCommonAdapter;
import com.lanou.evernote.base.ViewHolder;
import com.lanou.evernote.tools.BitmapToByte;
import com.lanou.evernote.tools.SingleLiteOrm;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/21.
 */
public class NoteBookFragment extends BaseFragment implements View.OnClickListener {
    private ListView listView;
    private ArrayList<String> data;
    private SearchView searchView;
    private ImageView returnIv;
    private RelativeLayout relativeLayout;


    @Override
    protected int setLayout() {
        return R.layout.fragment_note_book;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_notebook_list);
        searchView = (SearchView) view.findViewById(R.id.note_book_search);
        returnIv = (ImageView) view.findViewById(R.id.notebook_return);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.notebook_relayout);

    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("lanou" + i);
        }
        ArrayList<BitmapToByte> datas = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(BitmapToByte.class);
        Log.d("NoteBookFragment", "datas:" + datas.size());

        listView.setAdapter(new ListViewCommonAdapter<String>(context,data,R.layout.fragment_notebook_item) {

            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.fragment_notebook_tv,s);
            }
        });
        searchView.setQueryHint("查找笔记本");
        returnIv.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.notebook_return:
                Intent intent1 = new Intent("com.lanou.evernote.notebook.return");
                context.sendBroadcast(intent1);
                returnIv.setVisibility(View.GONE);
                break;
            case R.id.notebook_relayout:
                Intent intent = new Intent("com.lanou.evernote.notebook");
                context.sendBroadcast(intent);
                returnIv.setVisibility(View.VISIBLE);
                break;
        }

    }
}
