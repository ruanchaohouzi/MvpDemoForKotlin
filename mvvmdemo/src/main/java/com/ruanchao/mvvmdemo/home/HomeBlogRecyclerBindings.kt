package com.ruanchao.mvvmdemo.home

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.ruanchao.mvvmdemo.adapter.HomeBlogAdapter
import com.ruanchao.mvvmdemo.bean.HomeData

object HomeBlogRecyclerBindings{

    //增加数据更新的Adapter
    @BindingAdapter("app:homeDatas")
    @JvmStatic fun setHomeDataLists(recyclerView: RecyclerView, list: MutableList<HomeData>){

        with (recyclerView.adapter as HomeBlogAdapter){
            setHomeDataLists(list)
        }
    }

//    //增加开启关闭下拉式新的加载动画，这是swipeRefreshLayout自带的，不需要自己增加BindingAdapter
//    @BindingAdapter("app:refreshing")
//    @JvmStatic fun setHomeDataLists(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean){
//
//        swipeRefreshLayout.isRefreshing = isRefreshing
//    }

}