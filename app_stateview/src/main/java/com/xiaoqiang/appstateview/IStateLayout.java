package com.xiaoqiang.appstateview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/3/16 18:43
 */
public interface IStateLayout {
    /**
     * 初始化 一些数据
     *
     * @param context
     * @param attributeSet
     */
    void initView(Context context, AttributeSet attributeSet);

    /**
     * 获取 再次刷新 监听器
     *
     * @return
     */
    OnClickStateListener getOnClickStateListener();

    /**
     * 设置 再次刷新 监听器
     *
     * @param listener
     */
    void setOnClickStateListener(OnClickStateListener listener);

    /**
     * 获取当前显示的view 状态 是否是 正常视图
     *
     * @return true->是正常视图  false->不是正常视图
     */
    boolean isContentView();

    /**
     * 生成 content view
     *
     * @return
     */
    View generateContentView();

    /**
     * 显示正常视图
     */
    void showContentView();

    /**
     * 显示加载中 视图
     */
    void showLoadingView();

    /**
     * 显示加载中 视图
     *
     * @param msg
     */
    void showLoadingView(String msg);

    /**
     * 显示失败的 view
     */
    void showErrorView();

    /**
     * 显示失败的 view
     *
     * @param msg
     */
    void showErrorView(String msg);

    /**
     * 显示失败的 view
     *
     * @param msg
     * @param drawableId
     */
    void showErrorView(String msg, int drawableId);

    /**
     * 显示网络失败的 view
     */
    void showNetWorkErrorView();

    /**
     * 显示网络失败的 view
     *
     * @param msg
     */
    void showNetWorkErrorView(String msg);

    /**
     * 显示网络失败的 view
     *
     * @param msg
     * @param drawableId
     */
    void showNetWorkErrorView(String msg, int drawableId);

    /**
     * 显示 空试图
     *
     * @param msg
     */
    void showEmptyView(String msg);

    /**
     * 显示 空试图
     */
    void showEmptyView();

    /**
     * 空试图
     *
     * @param msg
     * @param drawableId
     */
    void showEmptyView(String msg, int drawableId);
}
