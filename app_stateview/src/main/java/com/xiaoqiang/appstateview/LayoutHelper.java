package com.xiaoqiang.appstateview;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;


/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/3/17 13:41
 */
class LayoutHelper {
    /**
     * 通过 viewstub 找到view 并添加进 父布局文件中
     *
     * @param viewGroup
     * @param viewStub
     * @return
     */
    public static View getViewStubView(ViewGroup viewGroup, ViewStub viewStub) {
        try {
            if (viewGroup == null || viewStub == null) {
                return null;
            }

            View view = viewStub.inflate();
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置监听器
     *
     * @param stateLayout
     * @param view
     */
    public static void setOnClickListener(IStateLayout stateLayout, View view) {
        if (view == null || stateLayout == null) {
            return;
        }
        view.setOnClickListener(stateLayout.getOnClickStateListener());
    }


    /**
     * 改变 状态试图 更新为最新状态
     *
     * @param fromView
     * @param toView
     */
    public static View changeViewState(View fromView, View toView) {
        if (toView == null) {
            return fromView;
        }
        if (toView == fromView) {
            return fromView;
        }
        if (fromView != null && fromView.getVisibility() == View.VISIBLE) {
            fromView.setVisibility(View.GONE);
        }
        if (toView != null && toView.getVisibility() == View.GONE) {
            toView.setVisibility(View.VISIBLE);
        }
        return toView;
    }


    /**
     * 设置数据
     *
     * @param msg
     * @param drawableId
     * @param view
     */
    public static void setData(String msg, int drawableId, View view) {
        if (view == null || view.getTag() == null) {
            return;
        }
        Object tag = view.getTag();
        if (tag instanceof BaseHolder) {
            ((BaseHolder) tag).setData(msg, drawableId);
        }
    }

    /**
     * 设置 holder 的tag
     *
     * @param tagView
     */
    public static void setTagHolder(View tagView) {
        if (null == tagView) {
            return;
        }
        BaseHolder holder = new BaseHolder(tagView);
        tagView.setTag(holder);
    }

    /**
     * 把 各种视图 添加到 statelayout
     *
     * @param stateLayout
     * @param view
     */
    public static void addView(ViewGroup stateLayout, View view) {
        if (stateLayout == null || view == null) {
            return;
        }
        if (view.getParent() == null) {
            stateLayout.addView(view, 0);
        }

    }
}
