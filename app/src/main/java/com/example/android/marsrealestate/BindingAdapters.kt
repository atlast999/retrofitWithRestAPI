

package com.example.android.marsrealestate

import android.telephony.ims.ImsManager
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.ApiStatus
import com.example.android.marsrealestate.overview.PhotoGridAdapter

@BindingAdapter("imgSrcUrl")
fun ImageView.setImageFromUrl(url: String?){
    url?.let {
        val src = it.toUri().buildUpon().scheme("https").build()
        Glide.with(this)
                .load(src)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(this)
    }
}

@BindingAdapter("imgState")
fun ImageView.bindStatus(status: ApiStatus){
    when(status){
        ApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            this.visibility = View.GONE
        }
    }
}

@BindingAdapter("rvAdapter")
fun RecyclerView.setUpAdapter(adapter: RecyclerView.Adapter<*>){
    this.adapter = adapter
}
@BindingAdapter("listData")
fun RecyclerView.setUpData(data: List<MarsProperty>?){
    this.adapter?.let {
        (it as PhotoGridAdapter).submitList(data)
    }
}