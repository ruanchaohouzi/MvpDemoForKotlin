package com.ruanchao.mvvmdemo.view

import android.os.Bundle

import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


abstract class LazyLoadFragment : Fragment() {
    private var isViewCreated: Boolean = false // 界面是否已创建完成
    private var isVisibleToUser: Boolean = false // 是否对用户可见

    // 实现具体的数据请求逻辑
    protected abstract fun loadData()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        tryLoadData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView(inflater, container)
    }

    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewCreated = true;
        tryLoadData();
    }

    fun tryLoadData() {
        if (isViewCreated && isVisibleToUser) {
            loadData()
        }
    }
}