package com.zhang.wanandroiod.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.R;
import com.zhang.wanandroiod.mvp.model.bean.CommonlyUsedWeb;
import com.zhang.wanandroiod.mvp.model.bean.IndexArticle;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */

public class LoadIndexArticleAdapter extends BaseMultiItemQuickAdapter<IndexArticle.DataBean.DatasBean, BaseViewHolder> {

    private List<CommonlyUsedWeb.DataBean> flowDatas = new ArrayList<>();
    private OnTagClickListener mOnTagClickListener;
    public LoadIndexArticleAdapter(List<IndexArticle.DataBean.DatasBean> data,OnTagClickListener mOnTagClickListener) {
        super(data);
        this.mOnTagClickListener = mOnTagClickListener;
        addItemType(0, R.layout.index_hot_web);
        addItemType(1, R.layout.index_article_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexArticle.DataBean.DatasBean item) {
        switch (item.getItemType()) {
            case 0:

                if (flowDatas.size()!=0){
                    TagFlowLayout flowlayout = helper.getView(R.id.flowlayout);
                    flowlayout.setAdapter(new TagAdapter<CommonlyUsedWeb.DataBean>(flowDatas) {
                        @Override
                        public View getView(FlowLayout parent, int position, CommonlyUsedWeb.DataBean dataBean) {
                            View inflate = View.inflate(MyApplication.getApplictaion(), R.layout.hot_tagflow_item, null);
                            TextView tv_tag = inflate.findViewById(R.id.tv_tag);
                            tv_tag.setText(dataBean.getName());
                            return inflate;
                        }
                    });

                    flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            mOnTagClickListener.onTagClickListener(flowDatas.get(position));
                            return false;
                        }
                    });
                }
                helper.addOnClickListener(R.id.tv_change);
                break;
            case 1:
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_author, "作者：" + item.getAuthor());
                helper.setText(R.id.tv_type, "分类：" + item.getChapterName());
                helper.setText(R.id.tv_time, "时间：" +  item.getNiceDate());
                if (item.isCollect()){
                    helper.setImageResource(R.id.iv_collection,R.mipmap.collection);
                }else {
                    helper.setImageResource(R.id.iv_collection,R.mipmap.collection_nomor);
                }
                helper.addOnClickListener(R.id.iv_collection);
                break;
            default:
                break;
        }

    }


    public void setFlowDatas(List<CommonlyUsedWeb.DataBean> flowDatas) {
        this.flowDatas = flowDatas;

    }

    public interface OnTagClickListener{
        void onTagClickListener(CommonlyUsedWeb.DataBean bean);
    }
}
