package com.ruanchao.mvvmdemo.animal

import com.ruanchao.mvvmdemo.bean.BaseNetBean
import com.ruanchao.mvvmdemo.bean.Projects
import com.ruanchao.mvvmdemo.net.NetWorkManager
import com.ruanchao.mvvmdemo.bean.UserInfo1
import com.ruanchao.mvvmdemo.db.UserDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AnimalModel(private val userDao: UserDao){


    fun getAllProjectByPage(page: Int): Observable<BaseNetBean<Projects>>{
        return NetWorkManager.getInstance().getWanAndroidApi().getAllProjectByPage(page)
    }

    fun getUserInfoById(id: Int): Single<UserInfo1> {
        return userDao.getUserInfoById(id).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun insertAll(list: List<UserInfo1>): Observable<Boolean> {
        //这样写更好
        return Observable.create(){
            userDao.insertAll(list)
        }
    }
}