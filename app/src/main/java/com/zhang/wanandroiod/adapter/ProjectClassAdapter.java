package com.zhang.wanandroiod.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.model.bean.ProjectClass;

import java.util.List;

/**
 * Created by Administrator on 2018/3/3.
 */

public class ProjectClassAdapter extends BaseQuickAdapter<ProjectClass.DataBean.DatasBean, BaseViewHolder> {

    public ProjectClassAdapter(int layoutResId, @Nullable List<ProjectClass.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectClass.DataBean.DatasBean item) {
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.main_sdv);
        simpleDraweeView.setImageURI(Uri.parse(item.getEnvelopePic()));
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_introduction, item.getDesc());
        helper.setText(R.id.tv_time, "时间：" + item.getNiceDate());
        helper.setText(R.id.tv_author, "作者：" + item.getAuthor());
        if (item.isCollect()) {
            helper.setImageResource(R.id.iv_collection, R.mipmap.collection);
        } else {
            helper.setImageResource(R.id.iv_collection, R.mipmap.collection_nomor);
        }
        helper.addOnClickListener(R.id.iv_collection);
    }
}
