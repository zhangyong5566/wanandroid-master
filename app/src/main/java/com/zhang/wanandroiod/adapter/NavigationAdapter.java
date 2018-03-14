package com.zhang.wanandroiod.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.model.bean.Navigation;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/3/3.
 */

public class NavigationAdapter extends BaseQuickAdapter<Navigation.DataBean, BaseViewHolder> {

    private TagFlowLayoutOnItemClick mTagFlowLayoutOnItemClick;
    private TagAdapter<Navigation.DataBean.ArticlesBean> mTagAdapter;

    public NavigationAdapter(TagFlowLayoutOnItemClick mTagFlowLayoutOnItemClick,int layoutResId, @Nullable List<Navigation.DataBean> data) {
        super(layoutResId, data);
        this.mTagFlowLayoutOnItemClick = mTagFlowLayoutOnItemClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Navigation.DataBean item) {
        helper.setText(R.id.tv_title,item.getName());
        TagFlowLayout flowLayout = helper.getView(R.id.flowlayout);
            mTagAdapter = new TagAdapter<Navigation.DataBean.ArticlesBean>(item.getArticles()) {

                @Override
                public View getView(FlowLayout parent, int position, Navigation.DataBean.ArticlesBean articlesBean) {
                    View inflate = View.inflate(MyApplication.getApplictaion(), R.layout.hot_tagflow_item, null);
                    TextView tv_tag = inflate.findViewById(R.id.tv_tag);
                    tv_tag.setText(articlesBean.getTitle());
                    return inflate;
                }
            };
        flowLayout.setAdapter(mTagAdapter);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mTagFlowLayoutOnItemClick.tagFlowOnItemClick(item.getArticles().get(position));
                return false;
            }
        });
    }

    public interface TagFlowLayoutOnItemClick{
        void tagFlowOnItemClick(Navigation.DataBean.ArticlesBean articlesBean);
    }
}
