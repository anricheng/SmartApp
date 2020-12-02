package com.capgemini.lib_common.databinding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.capgemini.lib_common.R
import com.capgemini.lib_common.extendtions.*
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

@BindingAdapter("image_url", "isCircle")
fun setImageUrl(view: ImageView, imageUrl: String, isCircle: Boolean) {
    setImageUrl(view, imageUrl, isCircle, 0);
}

@BindingAdapter("image_url", "isCircle", "radius", requireAll = false)
fun setImageUrl(view: ImageView, imageUrl: String, isCircle: Boolean, radius: Int) {
    val builder = Glide.with(view).load(imageUrl);
    if (isCircle) {
        builder.transform(CircleCrop());
    } else if (radius > 0) {
        builder.transform(RoundedCornersTransformation(radius.toPx, 0));
    }
    val layoutParams = view.layoutParams
    if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
        builder.override(layoutParams.width, layoutParams.height);
    }
    builder.into(view);
}


private fun bindData(view: ImageView, widthPx: Int, heightPx: Int, marginLeft: Int, imageUrl: String) {
    bindData(view, widthPx, heightPx, marginLeft, view.context.screenHeight(), view.context.screenWith(), imageUrl);
}

public fun bindData(view: ImageView, widthPx: Int, heightPx: Int, marginLeft: Int, maxWidth: Int, maxHeight: Int, imageUrl: String) {

    imageUrl.isNullOrEmpty {
        view.visibility = View.GONE
    }.otherwise {
        view.visibility = View.VISIBLE

        (widthPx <= 0 || heightPx <= 0).isTrue {

            Glide.with(view).load(imageUrl).transform(BlurTransformation()).dontAnimate().into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    view.background = resource
                }
            });

        }.otherwise {
            setSize(view, widthPx, heightPx, marginLeft, maxWidth, maxHeight);
            setImageUrl(view, imageUrl, false);
        }
    }

}

private fun setSize(view: ImageView, width: Int, height: Int, marginLeft: Int, maxWidth: Int, maxHeight: Int) {
    val finalWidth = if (width > height) {
        maxWidth
    } else {
        (width / (height * 1.0f / maxHeight)).toInt()
    }
    val finalHeight = if (width > height) {
        (height / (width * 1.0f / finalWidth)).toInt()
    } else {
        maxHeight
    }

    view.layoutParams.width = finalWidth;
    view.layoutParams.height = finalHeight;
    if (view.layoutParams is FrameLayout.LayoutParams) {
        (view.layoutParams as FrameLayout.LayoutParams).leftMargin = if (height > width) {
            marginLeft.toPx
        } else {
            0
        }
    } else if (view.layoutParams is LinearLayout.LayoutParams) {
        (view.layoutParams as LinearLayout.LayoutParams).leftMargin = if (height > width) {
            marginLeft.toPx
        } else {
            0
        }
    }
}

@BindingAdapter("blur_url", "radius")
fun setBlurImageUrl(imageView: ImageView, blurUrl: String, radius: Int) {
    Glide.with(imageView).load(blurUrl).override(radius).transform(BlurTransformation()).dontAnimate().into(object : SimpleTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            imageView.background = resource
        }
    });
}


@BindingAdapter(value = ["android:imageUrl", "android:placeHolder", "android:error"], requireAll = false)
fun loadImage(imageView: ImageView, url: String, @DrawableRes placeHolder: Int = R.drawable.ic_placeholder, @DrawableRes error: Int = R.drawable.ic_placeholder) {
    Glide.with(imageView).load(url).error(error).placeholder(placeHolder).into(imageView)
}