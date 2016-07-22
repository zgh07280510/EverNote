package com.lanou.evernote.homepage;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseFragment;
import com.lanou.evernote.base.ListViewCommonAdapter;
import com.lanou.evernote.base.MyApplication;
import com.lanou.evernote.base.ViewHolder;
import com.lanou.evernote.tools.BitmapToByte;
import com.lanou.evernote.tools.DisplayUtil;
import com.lanou.evernote.tools.LayoutParamsUtil;
import com.lanou.evernote.tools.SingleLiteOrm;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

/**
 * Created by dllo on 16/7/21.
 */
public class NoteBookFragment extends BaseFragment implements View.OnClickListener {
    private ListView listView;
    private ArrayList<String> data;
    private SearchView searchView;
    private ImageView returnIv;
    private RelativeLayout relativeLayout;
    private PopupWindow popupWindow;
    private TextView alphaView;



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
        alphaView = (TextView) view.findViewById(R.id.note_book_tv);

    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("lanou" + i);
        }
    //    ArrayList<BitmapToByte> datas = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(BitmapToByte.class);


        listView.setAdapter(new ListViewCommonAdapter<String>(context,data,R.layout.fragment_notebook_item) {

            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.fragment_notebook_tv,s);
                holder.setImgResource(R.id.note_book_iv,R.mipmap.ic_action_more);
                holder.getView(R.id.note_book_iv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popuWindows();
                    }
                });

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
   public void popuWindows(){

        View view= LayoutInflater.from(context).inflate(R.layout.note_book_popu, null);
       popupWindow = new PopupWindow(view, DisplayUtil.px2dip(context,4850), ViewGroup.LayoutParams.WRAP_CONTENT, false) {
           @Override
           public void dismiss() {
               alphaView.setVisibility(View.GONE);
               super.dismiss();

           }
       };
       //外部获得焦点
       popupWindow.setOutsideTouchable(true);
       //内部获得焦点
       popupWindow.setFocusable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       alphaView.setVisibility(View.VISIBLE);
       popupWindow.setInputMethodMode(popupWindow.INPUT_METHOD_NEEDED);
       popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, DisplayUtil.px2dip(context,730));

   }
}
