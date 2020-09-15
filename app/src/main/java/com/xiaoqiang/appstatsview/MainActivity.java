package com.xiaoqiang.appstatsview;

import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.xiaoqiang.appstateview.OnClickStateListener;
import com.xiaoqiang.appstateview.OnStateLayoutChangeListener;
import com.xiaoqiang.appstateview.StateLayout;
import com.xiaoqiang.appstatsview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());
        mMainBinding.chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int id) {
                switch (id) {
                    case R.id.chip_content:
                        contentView(null);
                        break;
                    case R.id.chip_error:
                        errorView(null);
                        break;
                    case R.id.chip_empty:
                        emptyView(null);
                        break;
                    case R.id.chip_net_work_error:
                        netWorkView(null);
                        break;
                    case R.id.chip_loading:
                        loadingView(null);
                        break;
                    case R.id.chip_zidyi:
                        zidingyi(null);
                        break;
                    case R.id.chip_zidyi2:
                        zidingyi2(null);
                        break;
                    default:
                        break;
                }
            }
        });
        mMainBinding.appStatlayout.setOnClickStateListener(new OnClickStateListener() {
            @Override
            public void onClick(View v) {
                //当点击 重试按钮时 会回调此方法
            }
        });
        mMainBinding.appStatlayout.setOnStateLayoutChangeListener(new OnStateLayoutChangeListener() {
            @Override
            public void onChange(boolean isContentView) {
                // 状态改变了   isContentView-》当前状态是否是 正常视图
            }
        });
        //当前状态图 是否时 正常视图在展示
        boolean contentView = mMainBinding.appStatlayout.isContentView();


        view = new FrameLayout(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentView(null);
            }
        });
    }

    /**
     * 正常视图
     *
     * @param view
     */
    public void contentView(View view) {
        mMainBinding.appStatlayout.showContentView();
    }

    /**
     * 加载失败视图
     *
     * @param view
     */
    public void errorView(View view) {
        mMainBinding.appStatlayout.showErrorView();
    }

    /**
     * 空试图
     *
     * @param view
     */
    public void emptyView(View view) {
        mMainBinding.appStatlayout.showEmptyView();
    }

    /**
     * 网络异常
     *
     * @param view
     */
    public void netWorkView(View view) {
        mMainBinding.appStatlayout.showNetWorkErrorView();
    }

    /**
     * 加载中
     *
     * @param view
     */
    public void loadingView(View view) {
        mMainBinding.appStatlayout.showLoadingView();
    }

    /**
     * 自定义 view
     *
     * @param o
     */
    private void zidingyi(Object o) {
        mMainBinding.appStatlayout.showCustomView(view);
    }

    /**
     * 自定义 view
     *
     * @param o
     */
    private void zidingyi2(Object o) {
        View view = new FrameLayout(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentView(null);
            }
        });
        mMainBinding.appStatlayout.showCustomView(view);
    }
}