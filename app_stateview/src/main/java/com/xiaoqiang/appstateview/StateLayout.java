package com.xiaoqiang.appstateview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.xiaoqiang.appstateview.helper.LayoutHelper;

/**
 * 状态试图布局  注意一点  在xml里面 包裹的第一个布局 默认指定为 contentView 即为正常试图
 *
 * @author Android-小强
 * @email: 15075818555@163.com
 * @data: on 2020/3/16 18:42
 */
public class StateLayout extends FrameLayout implements IStateLayout {
    /**
     * 如果 显示其他视图的时候 不携带 drawable 图片资源 那么默认为0 即 没有图片
     */
    public static final int DEFAULT_DRAWABLE_ID = 0;

    private ViewStub errorViewStub;
    private ViewStub netWorkErrorViewStub;
    private ViewStub loadViewStub;
    private ViewStub emptyViewStub;


    //    **********************************************************************


    /**
     * 正常试图
     */
    private View mContentView;
    /**
     * 加载中 视图
     */
    private View mLoadView;
    /**
     * 加载失败视图
     */
    private View mErrorView;
    /**
     * 网络失败视图
     */
    private View mNetWorkErrorView;
    /**
     * 空试图
     */
    private View mEmptyView;


    //    **********************************************************************


    /**
     * 当前的状态视图
     */
    private View mCurrentView;
    /**
     * 点击屏幕重试的 监听器
     */
    private OnClickStateListener mOnClickStateListener;
    /**
     * 状态监听器
     */
    private OnStateLayoutChangeListener mOnStateLayoutChangeListener;

    public StateLayout(@NonNull Context context) {
        this(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    public void initView(Context context, AttributeSet attributeSet) {

        errorViewStub = new ViewStub(context, R.layout.app_state_error_layout);

        netWorkErrorViewStub = new ViewStub(context, R.layout.app_state_network_error_layout);

        loadViewStub = new ViewStub(context, R.layout.app_state_load_layout);

        emptyViewStub = new ViewStub(context, R.layout.app_state_empty_layout);

    }

    @Override
    public OnClickStateListener getOnClickStateListener() {
        return mOnClickStateListener;
    }

    @Override
    public void setOnClickStateListener(OnClickStateListener listener) {
        mOnClickStateListener = listener;
    }

    @Override
    public boolean isContentView() {
        return mCurrentView == null || mCurrentView == mContentView;
    }

    @Override
    public View generateContentView() {
        if (mContentView != null) {
            return mContentView;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            mContentView = detectView(childAt);
            if (mContentView != null) {
                return mContentView;
            }
        }
        return mContentView;
    }

    @Override
    public void showContentView() {
        if (mContentView == null) {
            mContentView = generateContentView();
        }
        mCurrentView = LayoutHelper.changeViewState(mCurrentView, mContentView);
        if(mOnStateLayoutChangeListener != null){
            mOnStateLayoutChangeListener.onChange(isContentView());
        }
    }

    @Override
    public void showLoadingView() {
        showLoadingView("");
    }

    @Override
    public void showLoadingView(String msg) {
        if (mLoadView == null) {
            LayoutHelper.addView(this, loadViewStub);
            mLoadView = LayoutHelper.getViewStubView(this, loadViewStub);
            LayoutHelper.setTagHolder(mLoadView);
        }
        LayoutHelper.setData(msg, DEFAULT_DRAWABLE_ID, mLoadView);
        mCurrentView = LayoutHelper.changeViewState(mCurrentView, mLoadView);
        if(mOnStateLayoutChangeListener != null){
            mOnStateLayoutChangeListener.onChange(isContentView());
        }
    }

    @Override
    public void showErrorView() {
        showErrorView(null, DEFAULT_DRAWABLE_ID);
    }

    @Override
    public void showErrorView(String msg) {
        showErrorView(msg, DEFAULT_DRAWABLE_ID);
    }

    @Override
    public void showErrorView(String msg, int drawableId) {
        if (mErrorView == null) {
            LayoutHelper.addView(this, errorViewStub);
            mErrorView = LayoutHelper.getViewStubView(this, errorViewStub);
            LayoutHelper.setOnClickListener(this, mErrorView);
            LayoutHelper.setTagHolder(mErrorView);
        }
        LayoutHelper.setData(msg, drawableId, mErrorView);
        mCurrentView = LayoutHelper.changeViewState(mCurrentView, mErrorView);
        if(mOnStateLayoutChangeListener != null){
            mOnStateLayoutChangeListener.onChange(isContentView());
        }
    }

    @Override
    public void showNetWorkErrorView() {
        showNetWorkErrorView(null);
    }

    @Override
    public void showNetWorkErrorView(String msg) {
        showNetWorkErrorView(msg, DEFAULT_DRAWABLE_ID);
    }

    @Override
    public void showNetWorkErrorView(String msg, int drawableId) {
        if (mNetWorkErrorView == null) {
            LayoutHelper.addView(this, netWorkErrorViewStub);
            mNetWorkErrorView = LayoutHelper.getViewStubView(this, netWorkErrorViewStub);
            LayoutHelper.setOnClickListener(this, mNetWorkErrorView);
            LayoutHelper.setTagHolder(mNetWorkErrorView);
        }
        LayoutHelper.setData(msg, DEFAULT_DRAWABLE_ID, mNetWorkErrorView);
        mCurrentView = LayoutHelper.changeViewState(mCurrentView, mNetWorkErrorView);
        if(mOnStateLayoutChangeListener != null){
            mOnStateLayoutChangeListener.onChange(isContentView());
        }
    }

    @Override
    public void showEmptyView(String msg) {
        showEmptyView(msg, DEFAULT_DRAWABLE_ID);
    }

    @Override
    public void showEmptyView() {
        showEmptyView(null, DEFAULT_DRAWABLE_ID);
    }

    @Override
    public void showEmptyView(String msg, int drawableId) {
        if (mEmptyView == null) {
            LayoutHelper.addView(this, emptyViewStub);
            mEmptyView = LayoutHelper.getViewStubView(this, emptyViewStub);
            LayoutHelper.setOnClickListener(this, mEmptyView);
            LayoutHelper.setTagHolder(mEmptyView);
        }
        LayoutHelper.setData(msg, DEFAULT_DRAWABLE_ID, mEmptyView);
        mCurrentView = LayoutHelper.changeViewState(mCurrentView, mEmptyView);
        if(mOnStateLayoutChangeListener != null){
            mOnStateLayoutChangeListener.onChange(isContentView());
        }
    }

    /**
     * 检测 view
     *
     * @param childAt
     * @return
     */
    private View detectView(View childAt) {
        if (childAt != null
                && !(childAt instanceof ViewStub)
                && childAt != mEmptyView
                && childAt != mErrorView
                && childAt != mLoadView
                && childAt != mNetWorkErrorView) {
            return childAt;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    public void setOnStateLayoutChangeListener(OnStateLayoutChangeListener onStateLayoutChangeListener) {
        mOnStateLayoutChangeListener = onStateLayoutChangeListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = generateContentView();
        mCurrentView = mContentView;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (mContentView == null) {
            mContentView = detectView(child);
        }
    }

}
