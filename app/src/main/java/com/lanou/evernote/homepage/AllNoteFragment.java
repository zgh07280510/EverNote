package com.lanou.evernote.homepage;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseFragment;
import com.lanou.evernote.base.ListViewCommonAdapter;
import com.lanou.evernote.base.ViewHolder;

import java.util.ArrayList;


/**
 * Created by dllo on 16/7/21.
 */
public class AllNoteFragment extends BaseFragment {
    private ListView listView;
    private TextView textView;
    private ArrayList<String> data;

    @Override
    protected int setLayout() {
        return R.layout.fragment_all_note;
    }

    @Override
    protected void initView(View view) {

        listView = (ListView) view.findViewById(R.id.allnote_list_view);
        textView = (TextView) view.findViewById(R.id.homepage_header_tv);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            data.add("halou"+i);
        }
        listView.setAdapter(new ListViewCommonAdapter<String>(context,data,R.layout.list_item_hompage) {

            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.text_,s);
            }
        });

        View header = View.inflate(context,R.layout.home_list_header,null);
        listView.addHeaderView(header);
        listView.addHeaderView(View.inflate(context,R.layout.homepage_action,null));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }
        });

    }
}
