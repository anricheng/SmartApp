package com.capgemini.module_sample.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capgemini.repository.SampleRepository2

class SimpleCommunityHomeViewModel @ViewModelInject constructor(private val sampleRepository2: SampleRepository2) : ViewModel(){

    val feedlist = liveData {
        emit(sampleRepository2.getFeeds("", "", "", ""))
    }


    var map:MutableMap<Int, Int> = mutableMapOf<Int, Int>()







}

fun test(){

    val zhou =intArrayOf(1,5,4,6,8,9,56,34,3,5,6,2,4,6,8,1,4,6)
    val hai= intArrayOf(*zhou,)

    var n = 5
    var m = 10
    if (m ==5 && n==10){
        intArrayOf()

    }
}


fun bestSum(target: Int, array: IntArray,memb: MutableMap<Int,IntArray?> = mutableMapOf()): IntArray? {
    if (memb.contains(target)) return  memb[target]

    if (target == 0) return intArrayOf()

    if (target < 0) return null

    array.forEach {
        val reminder = target - it
        val howSum = howSumOptimized(reminder, array)
        if (howSum != null) {
            val newArray = intArrayOf(*howSum,it)
            memb[target] = newArray
            return newArray
        }
    }

    memb[target] = null

    return null
}


fun howSumOptimized(target: Int, array: IntArray,memb: MutableMap<Int,IntArray?> = mutableMapOf()): IntArray? {
    if (memb.contains(target)) return  memb[target]

    if (target == 0) return intArrayOf()
    val 周="This is from zhou"
    if (周.startsWith())

    if (target < 0) return null

    array.forEach {
        val reminder = target - it
        val howSum = howSumOptimized(reminder, array)
        if (howSum != null) {
            val newArray = intArrayOf(*howSum,it)
            memb[target] = newArray
            return newArray
        }
    }

    memb[target] = null

    return null
}



fun canSum(target:Int,array:IntArray):Boolean{
    if (target ==0) return true
    if (target <0)  return false
    array.forEach {
        val reminder=target-it
        if (canSum(reminder,array)){
            return true
        }
    }
    return false
}

fun canSum(target:Int,array:IntArray,memb:MutableMap<Int,Boolean> = mutableMapOf()):Boolean{
    if (memb.containsKey(target)) return memb[target]!!
    if (target ==0) return true
    if (target <0)  return false
    array.forEach {
        val reminder=target-it
        if (canSum(reminder,array)){
            memb[target]=true
            return true
        }
    }
    memb[target]=false
    return false
}



fun gridTraveler(n: Long, m: Long, map: MutableMap<String, Long>): Long {
    val key = "${n},${m}";
   return when{

        map.containsKey(key)-> map[key]!!
        n in 1..1 && m in 1..1 -> 1
        else -> {
            map[key]=gridTraveler(n - 1,m, map) + gridTraveler(n,m-1, map)
            map[key]!!
        }}
}


