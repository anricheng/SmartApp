package com.capgemini.module_sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capgemini.module_sample.R
import kotlinx.android.synthetic.main.simple_listfragment_list.*

class ListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view:View = inflater.inflate(R.layout.simple_listfragment_list,container,false)
       return view
    }
}