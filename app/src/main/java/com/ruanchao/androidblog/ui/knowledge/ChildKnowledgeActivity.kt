package com.ruanchao.androidblog.ui.knowledge

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruanchao.androidblog.R

class ChildKnowledgeActivity : AppCompatActivity() {

    companion object {
        val KEY_CID = "cid"
        val KEY_NAME = "name"

        fun start(context: Context, cid:Int?, name: String?){
            var intent = Intent(context, ChildKnowledgeActivity::class.java)
            intent.putExtra(KEY_CID, cid)
            intent.putExtra(KEY_NAME,name)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_knowledge)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content,KnowledgeChildListFragment.newInstance(
                intent.getIntExtra(KEY_CID, 60),
                    intent.getStringExtra(KEY_NAME)))
            .commit()

    }
}
