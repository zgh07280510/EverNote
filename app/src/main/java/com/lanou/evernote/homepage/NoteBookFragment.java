package com.lanou.evernote.homepage;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

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
public class NoteBookFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<String> data;


    @Override
    protected int setLayout() {
        return R.layout.fragment_note_book;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_notebook_list);
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

    }
}
