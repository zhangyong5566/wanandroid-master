package com.zhang.wanandroiod.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.model.bean.CollectionArtcle;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class CollectionAdapter extends BaseQuickAdapter<CollectionArtcle.DataBean.DatasBean, BaseViewHolder> {
    public CollectionAdapter(int layoutResId, @Nullable List<CollectionArtcle.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionArtcle.DataBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_author, "作者：" + item.getAuthor());
        helper.setText(R.id.tv_type, "分类：" + item.getChapterName());
        helper.setImageResource(R.id.iv_collection,R.mipmap.collection);
        helper.setText(R.id.tv_time, "时间：" + item.getNiceDate());

    }
}
