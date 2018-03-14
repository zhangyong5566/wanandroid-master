package com.zhang.wanandroiod.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BrowseArticleActivity extends BaseActivity {
    @BindView(R.id.rl_web)
    RelativeLayout rl_web;
    private AgentWeb mAgentWeb;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_article);
        mBind = ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent!=null){

            String link = intent.getStringExtra("link");
            mAgentWeb = AgentWeb.with(this)//传入Activity
                    .setAgentWebParent(rl_web, new RelativeLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                        @Override
                        public void onReceivedTitle(WebView view, String title) {

                        }
                    }) //设置 Web 页面的 title 回调
                    .createAgentWeb()//
                    .ready()
                    .go(link);
        }

    }

    private void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
