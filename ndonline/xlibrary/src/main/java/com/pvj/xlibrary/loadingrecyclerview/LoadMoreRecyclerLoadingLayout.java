package com.pvj.xlibrary.loadingrecyclerview;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.pvj.xlibrary.R;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.recyclerview.LoadMoreRecycleView;
import com.pvj.xlibrary.recyclerview.LoadingMoreFooter;

/**
 * Created by pvj on 2016/12/23.
 */

public class LoadMoreRecyclerLoadingLayout extends LoadingLayout implements SwipeRefreshLayout.OnRefreshListener, LoadMoreRecycleView.LoadingListener {
    SwipeRefreshLayout swipeRefreshLayout ;
    LoadMoreRecycleView loadMoreRecycleView ;
    OnRefreshAndLoadMoreListener onRefreshAndLoadMoreListener ;

    int refresh = 0 ; // 当前第几次刷新   传给LoadMoreRecycler  再回传  防止刷新 与加载更多不确定性的bug   // 也可以终止前一次请求接口 暂时还不会如何终止。
  //  int inrefresh = 0 ;

    public LoadMoreRecyclerLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public LoadMoreRecyclerLoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
    }

    public LoadMoreRecyclerLoadingLayout(Context context) {
        this(context, null);
    }


    private void inflateView(Context context) {
        inflate(context, R.layout.loadmore_recycler_loading_layout,this);
    }

    public LoadMoreRecyclerLoadingLayout setRecycleViewBackgroundColor(@ColorInt int color) {
        loadMoreRecycleView.setBackgroundColor(color);
        return this;
    }

    /**
     * onFinishInflate 当View中所有的子控件均被映射成xml后触发
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.xlib_swipeRefreshLayout);
        loadMoreRecycleView = (LoadMoreRecycleView) findViewById(R.id.xlib_recyclerView);

    //    swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.x_red,
                R.color.x_blue,
                R.color.x_yellow,
                R.color.x_green
        );
        swipeRefreshLayout.setOnRefreshListener(this);
        loadMoreRecycleView.setLoadingListener(this);


        setStatus(LoadingLayout.Loading);

    }

    @Override
    public void onRefresh() {

        refresh ++ ;
        loadMoreRecycleView.setInfresh(refresh);
        if (onRefreshAndLoadMoreListener!=null)
        onRefreshAndLoadMoreListener.onRefresh();
    }

    //处理的不是很好   inrefresh 是表示在第几次的刷新下加载的
    @Override
    public void onLoadMore(int inrefresh) {

            if (onRefreshAndLoadMoreListener!=null)
                onRefreshAndLoadMoreListener.onLoadMore(inrefresh );

    }

    public LoadMoreRecyclerLoadingLayout verticalLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        loadMoreRecycleView.setLayoutManager(manager);
        return this;
    }

    public LoadMoreRecyclerLoadingLayout setAdapter(RecyclerView.Adapter adapter){
        loadMoreRecycleView.setAdapter(adapter);
        return  this ;
    }
    public LoadMoreRecyclerLoadingLayout setSrcoll(LoadMoreRecycleView.RecScrollListener listener){
        loadMoreRecycleView.setRecScrollListener(listener);
        return this;
    }

    ///mRecyclerView.addItemDecoration(new RecycleViewDivider(
   // mContext, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.divide_gray_color)));
  public LoadMoreRecyclerLoadingLayout addItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        loadMoreRecycleView.addItemDecoration(itemDecoration);
        return  this ;
    }

    public LoadMoreRecyclerLoadingLayout setOnReloadListener(OnReloadListener listener){
        super.setOnReloadListener(listener);
        return  this ;
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    public void loadMoreComplete() {
        loadMoreRecycleView.loadMoreComplete();
    }

    public interface OnRefreshAndLoadMoreListener {
        void onRefresh();
        void onLoadMore(int inrefresh);
    }

    public LoadMoreRecyclerLoadingLayout setOnRefreshAndLoadMoreListener(OnRefreshAndLoadMoreListener onRefreshAndLoadMoreListener) {
        this.onRefreshAndLoadMoreListener = onRefreshAndLoadMoreListener;
        return this;
    }

    public  boolean isRefreshing(){
        return swipeRefreshLayout.isRefreshing();
    }

    public  int getRefreshCount(){
        return refresh ;
    }

    public void setTextEnd(){
        loadMoreRecycleView.setTextEnd();

    }

    public void setTextStart(){
        loadMoreRecycleView.setTextStart();
    }
}
