package com.ming.motionlayout.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.drake.net.Get
import com.drake.net.utils.scopeNet
import com.google.gson.Gson
import com.ming.motionlayout.R
import com.ming.motionlayout.bean.ArticleBean
import com.ming.motionlayout.bean.WanBean
import com.yanzhenjie.kalle.simple.cache.CacheMode

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        scopeNet {
            // 这个大括号内就属于作用域内部
            //val data = Get<List<WanBean>>("wxarticle/chapters/json").await() // 发起GET请求并返回`String`类型数据
            //val data1 = Get<String>("wxarticle/list/405/1/json?k=Java").await() // 发起GET请求并返回`String`类型数据
            val data1 = Get<String>("subject/25907124/").await() // 发起GET请求并返回`String`类型数据
            println("GsonConvert==请求结果转json=="+Gson().toJson(data1))
        }
        return root
    }
}