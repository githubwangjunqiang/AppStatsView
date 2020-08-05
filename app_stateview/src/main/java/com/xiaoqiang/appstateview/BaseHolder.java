package com.xiaoqiang.appstateview;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoqiang.appstateview.R;
import com.xiaoqiang.appstateview.StateLayout;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/3/17 17:20
 */
 class BaseHolder {

    public ImageView mImageView;
    public TextView mTextView;

    public BaseHolder(View view) {
        try {
            mImageView = view.findViewById(R.id.app_state_layout_error_view_ivimg);
            mTextView = view.findViewById(R.id.app_state_layout_error_view_tvmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置信息
     *
     * @param msg
     * @param drawableId
     */
    public void setData(String msg, int drawableId) {
        if (mImageView != null && drawableId != StateLayout.DEFAULT_DRAWABLE_ID) {
            mImageView.setImageResource(drawableId);
        }
        if (mTextView != null && !TextUtils.isEmpty(msg)) {
            mTextView.setText(msg);
        }
    }
}
