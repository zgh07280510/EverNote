package com.lanou.evernote.camera;

import android.widget.ImageView;
import android.widget.PopupWindow;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;

/**
 * Created by zouguohua on 16/7/20.
 */
public class CameraAty extends BaseActivity {
    private ImageView attachIv;//曲别针图片按钮
    private PopupWindow popupWindow;

    @Override
    public int setLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        attachIv = (ImageView) findViewById(R.id.attach_enabled);

    }

    @Override
    protected void initData() {

        popupWindow = new PopupWindow(this);

    }
}
