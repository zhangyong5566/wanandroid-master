package com.zhang.wanandroiod.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.ui.base.BaseActivity;
import com.zhang.wanandroiod.mvp.ui.fragment.IndexFragment;
import com.zhang.wanandroiod.mvp.ui.fragment.NavigationFragment;
import com.zhang.wanandroiod.mvp.ui.fragment.ProjectListFragment;
import com.zhang.wanandroiod.mvp.ui.fragment.SystemListFragment;
import com.zhang.wanandroiod.mvp.ui.fragment.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {
    private Unbinder unbinder;

    @BindView(R.id.bottom_navigation_bar_container)
    BottomNavigationBar bottom_navigation_bar_container;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private IndexFragment mIndexFragment;
    private SystemListFragment mSystemListFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectListFragment mProjectLisFragment;
    private UserFragment mUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initBottomNavBar();
        setListener();
    }

    private void setListener() {

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SerchActivity.class));

            }
        });

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onTabSelected(int position) {
        hideAllFrag();//先隐藏所有frag

        switch (position) {
            case 0:                     //首页
                if (mIndexFragment == null) {

                    mIndexFragment = IndexFragment.newInstance(null, null);
                }
                addFrag(mIndexFragment);
                getSupportFragmentManager().beginTransaction().show(mIndexFragment).commit();
                break;
            case 1:                     //体系
                if (mSystemListFragment == null) {

                    mSystemListFragment = SystemListFragment.newInstance(null, null);
                }
                addFrag(mSystemListFragment);
                getSupportFragmentManager().beginTransaction().show(mSystemListFragment).commit();

                break;
            case 2:                     //导航
                if (mNavigationFragment == null) {

                    mNavigationFragment = NavigationFragment.newInstance(null, null);
                }
                addFrag(mNavigationFragment);
                getSupportFragmentManager().beginTransaction().show(mNavigationFragment).commit();

                break;
            case 3:                     //项目
                if (mProjectLisFragment == null) {

                    mProjectLisFragment = ProjectListFragment.newInstance(null, null);
                }

                addFrag(mProjectLisFragment);
                getSupportFragmentManager().beginTransaction().show(mProjectLisFragment).commit();

                break;
            case 4:                     //我的
                if (mUserFragment == null) {
                    mUserFragment = UserFragment.newInstance(null, null);

                }

                addFrag(mUserFragment);
                getSupportFragmentManager().beginTransaction().show(mUserFragment).commit();

                break;
            default:
                break;
        }
    }

    private void hideAllFrag() {
        hideFrag(mIndexFragment);
        hideFrag(mSystemListFragment);
        hideFrag(mNavigationFragment);
        hideFrag(mProjectLisFragment);
        hideFrag(mUserFragment);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNavBar() {
        bottom_navigation_bar_container.setAutoHideEnabled(true);//自动隐藏
        bottom_navigation_bar_container.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_navigation_bar_container.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        bottom_navigation_bar_container.setBarBackgroundColor(R.color.white); //背景颜色
        bottom_navigation_bar_container.setInActiveColor(R.color.bottom_nav_normal); //未选中时的颜色
        bottom_navigation_bar_container.setActiveColor(R.color.bottom_nav_selected);//选中时的颜色


        BottomNavigationItem indexItem = new BottomNavigationItem(R.mipmap.icon_index, "首页");
        BottomNavigationItem systemItem = new BottomNavigationItem(R.mipmap.icon_system, "体系");
        BottomNavigationItem navigationItem = new BottomNavigationItem(R.mipmap.icon_navigation, "导航");
        BottomNavigationItem projectItem = new BottomNavigationItem(R.mipmap.icon_project, "项目");
        BottomNavigationItem myItem = new BottomNavigationItem(R.mipmap.icon_user, "我的");

        bottom_navigation_bar_container.addItem(indexItem).addItem(systemItem).addItem(navigationItem).addItem(projectItem).addItem(myItem);
        bottom_navigation_bar_container.setFirstSelectedPosition(0);
        bottom_navigation_bar_container.initialise();
        bottom_navigation_bar_container.setTabSelectedListener(this);

        setDefaultFrag();

    }

    private void setDefaultFrag() {

        if (mIndexFragment == null) {
            mIndexFragment = IndexFragment.newInstance(null, null);
        }
        addFrag(mIndexFragment);

        getSupportFragmentManager().beginTransaction().show(mIndexFragment).commit();
    }


    /**
     * 添加fregment
     *
     * @param fragmentIndex
     */
    private void addFrag(Fragment fragmentIndex) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragmentIndex != null && !fragmentIndex.isAdded()) {
            ft.add(R.id.bottom_nav_content, fragmentIndex);
        }
        ft.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param frag
     */
    private void hideFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (frag != null && frag.isAdded()) {
            ft.hide(frag);
        }
        ft.commit();
    }
}
