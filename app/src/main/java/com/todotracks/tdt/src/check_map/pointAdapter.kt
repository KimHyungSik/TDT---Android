package com.todotracks.tdt.src.check_map

import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import com.naver.maps.map.overlay.InfoWindow
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import com.naver.maps.map.overlay.InfoWindow.DefaultViewAdapter
import com.todotracks.tdt.R


class pointAdapter(context: Context, parent: ViewGroup, my_check: Boolean) : DefaultViewAdapter(context) {
    private val mContext: Context
    private val mParent: ViewGroup
    val checked = my_check
    override fun getContentView(infoWindow: InfoWindow): View {
        val view: View =
            LayoutInflater.from(mContext).inflate(R.layout.item_point, mParent, false) as View
        val txtTitle = view.findViewById(R.id.map_sub_text) as TextView
        val check_point: CheckBox = view.findViewById(R.id.sub_text_check) as CheckBox
        txtTitle.text = "제주특별자치도청"
        check_point.isChecked = checked
        return view
    }

    init {
        mContext = context
        mParent = parent
    }
}