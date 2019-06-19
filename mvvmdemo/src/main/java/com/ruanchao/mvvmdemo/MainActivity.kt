package com.ruanchao.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ruanchao.mvvmdemo.databinding.ActivityMainBinding
import com.ruanchao.mvvmdemo.ui.home.HomeBlogFragment
import com.ruanchao.mvvmdemo.ui.knowledge.KnowledgeFragment
import com.ruanchao.mvvmdemo.ui.login.LoginViewModel
import com.ruanchao.mvvmdemo.ui.login.UserFragment
import com.ruanchao.mvvmdemo.ui.publicnumber.PublicNumberFragment
import com.ruanchao.mvvmdemo.ui.test.TestFragment
import com.ruanchao.mvvmdemo.utils.PreferencesUtil
import com.ruanchao.mvvmdemo.utils.obtainViewModel
import com.ruanchao.mvvmdemo.utils.schedule
import com.ruanchao.mvvmdemo.utils.toast
import com.ruanchao.mvvmdemo.view.BottomTabLayoutView
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var clickStartTime:Long = 0
    private val CLICK_INTERVAL: Long = 2000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        supportActionBar?.hide()
        //第一次进入，判断用户是否已经登录
        val cookie = PreferencesUtil.getSet(PreferencesUtil.COOKIE_KEY)
        if (cookie != null){
            val loginViewModel = obtainViewModel(LoginViewModel::class.java)
            loginViewModel.getUserInfo()
        }

        initTab()
    }

    private fun initTab() {
        val fragments = ArrayList<Fragment>()
        fragments.add(PublicNumberFragment())
        fragments.add(HomeBlogFragment())
        fragments.add(KnowledgeFragment())
        fragments.add(TestFragment())
        fragments.add(UserFragment())
        home_tab_layout.initView(supportFragmentManager, fragments)
    }

    override fun onBackPressed() {
        exit()
    }

    private fun exit(){

        if (System.currentTimeMillis() - clickStartTime > CLICK_INTERVAL){
            clickStartTime = System.currentTimeMillis()
            toast("再按一次退出应用")
        }else{
            super.onBackPressed()
        }
    }

}
