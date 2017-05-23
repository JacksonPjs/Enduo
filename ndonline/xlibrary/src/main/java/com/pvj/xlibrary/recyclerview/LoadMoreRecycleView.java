package com.pvj.xlibrary.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.pvj.xlibrary.loadingrecyclerview.LoadMoreRecyclerLoadingLayout;

/**
 *  刚好占一个屏幕时 上拉加载不会有。
 *   仅仅限于 像Listview 一样 垂直分布 其他未处理
 *   // 填加头部也未处理
 * Created by pvj on 2016/12/22.
 */

public class LoadMoreRecycleView extends RecyclerView {
    private View mFootView;
    private LoadMoreAdapter mLoadMoreAdapter;
    private LoadingListener mLoadingListener;
    DataObserver dataObserver ;

    private  boolean loadmore = true;

    private  boolean end = false;

    int infresh = 0 ;

    public LoadMoreRecycleView(Context context) {
        this(context, null);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //TODO: 2016/12/22   底部view 的初始化
        mFootView = new LoadingMoreFooter(getContext());
        mFootView.setVisibility(INVISIBLE);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        mLoadMoreAdapter = new LoadMoreAdapter(adapter);
        super.setAdapter(mLoadMoreAdapter);

        adapter.registerAdapterDataObserver(dataObserver =new DataObserver());
        dataObserver.onChanged();
    }



    private class LoadMoreAdapter extends RecyclerView.Adapter<ViewHolder> {


        private static final int TYPE_NORMAL = 0x11;
        private static final int TYPE_BOTTOM = 0x12;

        private RecyclerView.Adapter adapter;

        public LoadMoreAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_NORMAL) {

            } else if (viewType == TYPE_BOTTOM) {
                return new LoadMoreViewHolder(mFootView);
            }

            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int adjposition = position;

            System.out.println("---adjposition:" + adjposition);
            if (adapter != null) {
                int adapterCount = adapter.getItemCount();
                if (adjposition == adapterCount) {

                } else {
                    adapter.onBindViewHolder(holder, adjposition);
                }
            }
        }

        @Override
        public int getItemCount() {

            if (adapter != null) {
                return adapter.getItemCount() + 1;
            } else {
                return 1;
            }
        }


        public int getItemViewType(int position) {

            if (position == getItemCount() - 1) {
                return TYPE_BOTTOM;
            } else {
                return TYPE_NORMAL;
            }

        }
    }


    private class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

        System.out.println("---lastVisibleItemPosition:" + lastVisibleItemPosition);

        System.out.println("---layoutManager.getItemCount():" + layoutManager.getItemCount());

        System.out.println("---layoutManager.getChildCount():" + layoutManager.getChildCount());
        System.out.println("---layoutManager.findFirstVisibleItemPosition:" + ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition());
        if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition()==0&&mRecyScrollListener!=null){
                mRecyScrollListener.onRecScrollToTop();
        }else {
            if (mRecyScrollListener!=null)
            mRecyScrollListener.onRecScrollNotTop();
        }
        if (layoutManager.getChildCount() > 0
                && lastVisibleItemPosition >= layoutManager.getItemCount() - 1&&layoutManager.getItemCount() > layoutManager.getChildCount()&&mLoadingListener!=null) {
            mFootView.setVisibility(View.VISIBLE);

            if (loadmore){
                if (!end){
                    mLoadingListener.onLoadMore(infresh);
                    loadmore = false ;
                }

            }

        } else {
          //  mFootView.setVisibility(View.GONE);
        }


    }


    private RecScrollListener mRecyScrollListener;
    public void setRecScrollListener(RecScrollListener scrollListener) {
        this.mRecyScrollListener = scrollListener;
    }

    private RecScrollListener mScrollListener;
    public interface RecScrollListener{
        void onRecScrollToTop();
        void onRecScrollNotTop();
    }

    public void setInfresh(int infresh){
        this.infresh = infresh;
    }

    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mLoadMoreAdapter != null) {
                if (mFootView!=null)
                mFootView.setVisibility(View.INVISIBLE);
                mLoadMoreAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mLoadMoreAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mLoadMoreAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mLoadMoreAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mLoadMoreAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mLoadMoreAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //解决和CollapsingToolbarLayout冲突的问题
        AppBarLayout appBarLayout = null;
        ViewParent p = getParent();
        while (p != null) {
            if (p instanceof CoordinatorLayout) {
                break;
            }
            p = p.getParent();
        }
        if(p instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout)p;
            final int childCount = coordinatorLayout.getChildCount();
            for (int i = childCount - 1; i >= 0; i--) {
                final View child = coordinatorLayout.getChildAt(i);
                if(child instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout)child;
                    break;
                }
            }
            if(appBarLayout != null) {
//                appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//                    @Override
//                    public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                        appbarState = state;
//                    }
//                });
            }
        }
    }



    public void setLoadingListener(LoadingListener listener) {
        mLoadingListener = listener;
    }

    public interface LoadingListener {
        void onLoadMore(int inrefesh);
    }



    public void loadMoreComplete(){
        this.loadmore = true ;
    }


    public void setTextEnd(){
        ((LoadingMoreFooter)mFootView).setTextEnd();

        end = true ;

    }

    public void setTextStart(){
        end = false ;
        ((LoadingMoreFooter)mFootView).setTextStart();
    }

}
