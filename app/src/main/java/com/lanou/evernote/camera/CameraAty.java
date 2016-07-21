package com.lanou.evernote.camera;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.lanou.evernote.R;
import com.lanou.evernote.base.BaseActivity;
import com.lanou.evernote.tools.LayoutParamsUtil;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zouguohua on 16/7/20.
 */
public class CameraAty extends BaseActivity implements View.OnClickListener {
    private ImageView attachIv;//曲别针图片按钮
    private PopupWindow popupWindow;
    private ImageView shareIv;
    private View cameraPopView;
    private View alphaView;
    private RelativeLayout cameraLv;

    @Override
    public int setLayout() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        attachIv = (ImageView) findViewById(R.id.attach_enabled);
        shareIv = (ImageView) findViewById(R.id.share_enabled);
        alphaView = (View) findViewById(R.id.alpha_view);
        cameraLv = (RelativeLayout) findViewById(R.id.camera_lv);

    }

    @Override
    protected void initData() {
        attachIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attach_enabled:
            loadPop();
                break;
            case R.id.share_enabled:
                share();
                break;
        }
    }

    public void share() {
        ShareSDK.initSDK(CameraAty.this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        //oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(CameraAty.this);
    }

    public void loadPop() {
        cameraPopView = LayoutInflater.from(this).inflate(R.layout.item_camera_popup, null);
        popupWindow = new PopupWindow(cameraPopView, LayoutParamsUtil.getScreenWidth(this), ViewGroup.LayoutParams.WRAP_CONTENT, false) {
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
        popupWindow.showAtLocation(cameraLv, Gravity.BOTTOM, 0, 0);


    }
}
