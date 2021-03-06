package com.ruanchao.androidblog.adapter

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ruanchao.androidblog.R
import com.ruanchao.androidblog.bean.DataInfo
import com.ruanchao.androidblog.bean.UserInfo
import com.ruanchao.androidblog.ui.collection.CollectionViewModel
import com.ruanchao.androidblog.ui.login.LoginActivity

class PublicNumberListAdapter(val context: Context, val viewModel: CollectionViewModel,
                              var listener:((dataInfo: DataInfo) ->Unit)?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var datas: MutableList<DataInfo> = mutableListOf()
    var isCollectionAdapter: Boolean = false

    fun resetDatas(datas: List<DataInfo>?){
        if (datas != null) {
            this.datas = datas as MutableList<DataInfo>
            notifyDataSetChanged()
        }
    }

    fun addDatas(datas: List<DataInfo>?) {
        if (datas != null) {
            this.datas.addAll(datas as MutableList<DataInfo>)
            notifyDataSetChanged()
        }
    }

    fun addCollectedData(dataInfo: DataInfo){
        this.datas.add(0, dataInfo)
        notifyDataSetChanged()
    }

    fun removeUnCollectedData(dataInfo: DataInfo){
        this.datas.remove(dataInfo)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        val inflate = LayoutInflater.from(context).inflate(R.layout.public_number_artical_recycler_item, p0, false)
        return ItemViewHolder(inflate)
    }

    override fun getItemCount() = datas.size


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ItemViewHolder
        viewHolder.bindItemView(datas[position], listener)
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var tv_Author: TextView? = null
        var tv_niceDate: TextView? = null
        var tv_title: TextView? = null
        var tv_chapterName: TextView? = null
        var iv_collect: ImageView? = null
        init {
            tv_Author = itemView.findViewById(R.id.tv_Author)
            tv_niceDate = itemView.findViewById(R.id.tv_niceDate)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_chapterName = itemView.findViewById(R.id.tv_chapterName)
            iv_collect = itemView.findViewById(R.id.iv_collect)
        }

        //直接在ViewHolder里面绑定数据
        fun bindItemView(dataInfo: DataInfo, listener: ((dataInfo: DataInfo) ->Unit)?){
            with(dataInfo){
                tv_Author?.text = author
                tv_niceDate?.text = niceDate
                tv_title?.text = title
                tv_chapterName?.text = chapterName
                if (isCollectionAdapter){
                    dataInfo.collect = true
                }
                var resourceId = if (dataInfo.collect) R.mipmap.artical_collected else R.mipmap.artical_discollected

                iv_collect?.setImageResource(resourceId)
                itemView.setOnClickListener{
                    listener?.invoke(dataInfo)
                }
                iv_collect?.setOnClickListener {

                    if (UserInfo.getInstance()?.user == null){
                        Toast.makeText(context, "请先登录", Toast.LENGTH_LONG).show()
                        LoginActivity.start(context as Activity)
                    }else{
                        if (dataInfo.collect){
                            if (isCollectionAdapter){
                                viewModel.unCollectMyArtical(dataInfo)
                            }else {
                                viewModel.unCollectArtical(dataInfo)
                                iv_collect?.setImageResource(R.mipmap.artical_discollected)
                                dataInfo.collect = false
                            }
                        }else{
                            viewModel.collectArtical(dataInfo)
                            iv_collect?.setImageResource(R.mipmap.artical_collected)
                            dataInfo.collect = true
                        }
                    }
                }
            }
        }
    }

}