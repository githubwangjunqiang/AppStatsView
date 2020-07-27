# AppStatsView
状态试图布局项目

此库是针对 Android项目下的 状态试图改变封装的一个layout;

开发项目时会遇到 在不同的状态 界面要相应展示不同的状态视图 最普遍的就是 加载中状态、加载失败状态、加载成功后显示正常视图、无网络状态
等.....,在每个界面都要写很多布局的话 既增加了工作量 也增加了悬案复杂性 降低了界面性能，我决心要封装一个layout，实现这些功能并且性能
做到最佳，经过一些时间的开发和调试终于上线了 状态试图 layout 并且在我司项目中使用了一年之久，并没有因为此库而产生的崩溃和性能下降；
同志们放心使用！！！


![image](https://github.com/githubwangjunqiang/AppStatsView/blob/master/img/content.jpg)
![image](https://github.com/githubwangjunqiang/AppStatsView/blob/master/img/empty.jpg)
![image](https://github.com/githubwangjunqiang/AppStatsView/blob/master/img/error.jpg)
![image](https://github.com/githubwangjunqiang/AppStatsView/blob/master/img/loading.jpg)
![image](https://github.com/githubwangjunqiang/AppStatsView/blob/master/img/networkerror.jpg)

此库的优点：

1、侵入性小，调用简单，继承自系统布局 FrameLayout，并未修改系统逻辑，只要在需要多状态显示的地方，用 StateLayout 当作父布局即可，
在业务代码中调用StateLayout相应展示的方法
就会展示不同的状态视图，默认展示你自己的视图即正常试图（contentView），
您还可以把他当作FrameLayout使用，可随便自定义，他就是一个FrameLayout；

2、性能高，虽然 StateLayout 目前拥有5个状态视图，但是他的性能并没有受影响，在您的布局中只是多了一层 FrameLayout 而已，
因为 除了默认的contentView 视图布局，其他的视图布局都是按需加载的，并不是在您项目初始化的时候会添加5套布局在里面，都是
使用按需加载的方式显示状态视图的，所以您不会感到卡顿的原因在这里，比如您如果不调用 无网络视图的话 StateLayout永远不会
加载无网络视图到内存中！！！


使用方法  在项目build.gradle 文件中添加
            allprojects {
            		repositories {
            			...
            			maven { url 'https://jitpack.io' }
            		}
            	}


           在app 的build.fradle文件中添加

           dependencies {
           	        implementation 'com.github.githubwangjunqiang:AppStatsView:Tag'
           	}

           	并重新编译！！！

xml布局中 嵌入 StateLayout


    <com.xiaoqiang.appstateview.StateLayout
        android:id="@+id/app_statlayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <Button
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="我是正常视图" />
    </com.xiaoqiang.appstateview.StateLayout>


代码中 更改状态

    //初始化
    Statlayout Statlayout = findViewById(R.id.app_statlayout)

    //显示 正常视图
    Statlayout.showContentView();
    //显示 失败视图
    Statlayout.showErrorView();
    //显示 空视图
    Statlayout.showEmptyView();
    //显示 网络异常视图
    Statlayout.showNetWorkErrorView();
    //显示 加载中视图
    Statlayout.showLoadingView();


    监听：
        Statlayout.setOnClickStateListener(new OnClickStateListener() {
                    @Override
                    public void onClick(View v) {
                        //当点击 重试按钮时 会回调此方法
                    }
                });

        Statlayout.setOnStateLayoutChangeListener(new OnStateLayoutChangeListener() {
                    @Override
                    public void onChange(boolean isContentView) {
                        // 状态改变了   isContentView-》当前状态是否是 正常视图
                    }
                });

          //当前状态图 是否时 正常视图在展示
         boolean contentView = mMainBinding.appStatlayout.isContentView();



注意：此库继承自 FrameLayout 如果您开启了混淆 只要不要混淆 继承自 FrameLayout 的类即可；
默认您的混淆文件已经拥有了此能力，您可不必添加任何规则，此库会正常运行

