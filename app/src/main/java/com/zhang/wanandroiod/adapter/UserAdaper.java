package com.zhang.wanandroiod.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhang.wanandroiod.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class UserAdaper extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserAdaper(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item, item);
    }
}
