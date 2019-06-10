package com.ruanchao.mvvmdemo.ui.home

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView
import com.ruanchao.mvvmdemo.adapter.HomeBlogAdapter
import com.ruanchao.mvvmdemo.bean.HomeData

object HomeBlogRecyclerBindings{

    //增加数据更新的Adapter
//    @BindingAdapter("app:homeDatas")
//    @JvmStatic fun RecyclerView.setHomeDataLists(list: MutableList<HomeData>){
//
//        with (this.adapter as HomeBlogAdapter){
//            setHomeDataLists(list)
//        }
//    }

    //增加开启关闭下拉式新的加载动画，这是swipeRefreshLayout自带的，不需要自己增加BindingAdapter
    @BindingAdapter("app:refreshing")
    @JvmStatic fun setHomeDataLists(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean){

        swipeRefreshLayout.isRefreshing = isRefreshing
    }

}