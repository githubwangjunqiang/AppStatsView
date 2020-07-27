package com.xiaoqiang.appstatsview;

import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xiaoqiang.appstateview.StateLayout;
import com.xiaoqiang.appstatsview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;

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
                    default:
                        break;
                }
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
}