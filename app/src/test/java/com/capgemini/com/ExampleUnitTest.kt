package com.capgemini.com
//
//import com.capgemini.lib_common.extendtions.isTrue
//import com.capgemini.lib_common.extendtions.otherwise
//
//
//class Node(val value: Int) {
//    var leftNode: Node? = null
//    var rightNode: Node? = null
//}
//
//
//fun buildBST(arr: IntArray, left: Int, right: Int): Node? =
//    (right < left).isTrue {
//        null
//    }.otherwise {
//
//        val mid = left + (right - left) / 2
//        val root = Node(arr[mid])
//        root.leftNode = buildBST(arr, left, mid)
//        root.rightNode = buildBST(arr, mid, right)
//        root
//    }
//
//fun main() {
//    val zhou = intArrayOf(1,2,3,4,5,6,7,8,9)
//
//    val root = buildBST(zhou,0,zhou.size)
//
//    print(root)
//}