package com.capgemini.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

/**
 * id : 364
 * itemId : 6739143063064549000
 * itemType : 2
 * createTime : 1569079017
 * duration : 299.435
 * feeds_text : 当中国地图出来那一幕，我眼泪都出来了！
 * 太震撼了！
 * authorId : 3223400206308231
 * activityIcon : null
 * activityText : null
 * width : 640
 * height : 368
 * url : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6739143063064549643.mp4
 * cover :
 */
@Entity(tableName = "feed")
@Parcelize
data class Feed(
    var id: Int = 0,
    var itemType: Int = 0,
    var duration: Float = 0.0f,
    var width: Int = 0,
    var height: Int = 0,
    var itemId: Long = 0,
    var createTime: Long = 0,
    var topComment: Comment? = null,
    var url: String? = null,
    var cover: String? = null,
    var author: User? = null,
    var feeds_text: String = "",
    var authorId: Long = 0,
    var activityIcon: String? = null,
    var activityText: String? = null,
    var ugc: Ugc? = null
) : Parcelable

/**
 * id : 962
 * userId : 3223400206308231
 * name : 二师弟请随我来
 * avatar :
 * description :
 * likeCount : 0
 * topCommentCount : 0
 * followCount : 0
 * followerCount : 0
 * qqOpenId : null
 * expires_time : 0
 * score : 0
 * historyCount : 0
 * commentCount : 0
 * favoriteCount : 0
 * feedCount : 0
 * hasFollow : false
 */
@Parcelize
data class User(
    var id: Int = 0,
    var userId: Long = 0,
    var name: String? = null,
    var avatar: String? = null,
    var description: String? = null,
    var likeCount: Int = 0,
    var topCommentCount: Int = 0,
    var followCount: Int = 0,
    var followerCount: Int = 0,
    var qqOpenId: String? = null,
    var expires_time: Long = 0,
    var score: Int = 0,
    var historyCount: Int = 0,
    var commentCount: Int = 0,
    var favoriteCount: Int = 0,
    var feedCount: Int = 0,
    var hasFollow: Boolean = false
) : Parcelable

/**
 * id : 784
 * itemId : 6739143063064549000
 * commentId : 6739212214408380000
 * userId : 65200808093
 * commentType : 1
 * createTime : 1569095152
 * commentCount : 4454
 * likeCount : 152
 * commentText : 看见没。比甜蜜暴击好看一万倍！
 * imageUrl : null
 * videoUrl : null
 * width : 0
 * height : 0
 * hasLiked : false
 * author : {"id":978,"userId":65200808093,"name":"带鱼裹上面包糠","avatar":"","description":null,"likeCount":0,"topCommentCount":0,"followCount":0,"followerCount":0,"qqOpenId":null,"expires_time":0,"score":0,"historyCount":0,"commentCount":0,"favoriteCount":0,"feedCount":0,"hasFollow":false}
 * ugc : {"likeCount":153,"shareCount":0,"commentCount":4454,"hasFavorite":false,"hasLiked":true}
 */
@Parcelize
data class Comment(
    var id: Int = 0,
    var itemId: Long = 0,
    var commentId: Long = 0,
    var userId: Long = 0,

    var createTime: Long = 0,
    var commentCount: Int = 0,
    var likeCount: Int = 0,
    var commentType: Int = 0,
    var width: Int = 0,
    var height: Int = 0,
    var commentText: String? = null,
    var imageUrl: String? = null,
    var videoUrl: String? = null,

    var hasLiked: Boolean = false,
    var author: User? = null,
    var ugc: Ugc? = null
) : Parcelable

/**
 * likeCount : 153
 * shareCount : 0
 * commentCount : 4454
 * hasFavorite : false
 * hasLiked : true
 * hasdiss:false
 */
@Parcelize
data class Ugc(
    var likeCount: Int = 0,
    var shareCount: Int = 0,
    var commentCount: Int = 0,
    var hasdiss: Boolean = false,
    var hasLiked: Boolean = false
) : Parcelable

