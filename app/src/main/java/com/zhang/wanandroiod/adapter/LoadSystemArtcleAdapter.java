package com.zhang.wanandroiod.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.model.bean.SystemArtcle;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

public class LoadSystemArtcleAdapter extends BaseQuickAdapter<SystemArtcle.DataBean.DatasBean, BaseViewHolder> {
    public LoadSystemArtcleAdapter(int layoutResId, @Nullable List<SystemArtcle.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemArtcle.DataBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_author, "作者：" + item.getAuthor());
        helper.setText(R.id.tv_type, "分类：" + item.getChapterName());
        helper.setText(R.id.tv_time, "时间：" + item.getNiceDate());
        if (item.isCollect()){
            helper.setImageResource(R.id.iv_collection,R.mipmap.collection);
        }else {
            helper.setImageResource(R.id.iv_collection,R.mipmap.collection_nomor);
        }
        helper.addOnClickListener(R.id.iv_collection);
    }

}
