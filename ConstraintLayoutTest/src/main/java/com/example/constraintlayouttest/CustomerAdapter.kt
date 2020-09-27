package com.example.constraintlayouttest

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class CustomerAdapter(layoutResId: Int, data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {
    override fun convert(baseViewHolder: BaseViewHolder, s: String) {
        baseViewHolder.setText(R.id.textView,s)
    }
}