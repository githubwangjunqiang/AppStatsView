package com.xiaoqiang.appstateview;

/**
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/6/12 16:36
 */
public interface OnStateLayoutChangeListener {
    /**
     * 状态改变
     * @param isContentView 是否是 contentView
     */
    void onChange(boolean isContentView);
}
