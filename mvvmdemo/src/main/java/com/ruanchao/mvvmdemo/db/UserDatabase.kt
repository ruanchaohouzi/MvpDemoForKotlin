package com.ruanchao.mvvmdemo.db

import android.arch.persistence.room.*
import android.content.Context
import com.ruanchao.mvvmdemo.bean.UserInfo1


@Database(entities = [UserInfo1::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        @Volatile private var mInstance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase?{
            if (mInstance == null){
                synchronized(this){
                    if (mInstance == null){
                        mInstance = buildDatabase(context)
                    }
                }
            }
            return mInstance
        }

        private fun buildDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(context,UserDatabase::class.java,"user.db")
                .build()
        }
    }

    abstract fun userDao(): UserDao


}