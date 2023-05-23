package com.example.test5.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // 드래그 방향
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        // 스와이프 방향
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        // 이동을 만드는 메소드
        return makeMovementFlags(dragFlags, swipeFlags)
    }
    // 드래그된 item을 이전 위치에서 새로운 위치로 옮길 때 호출
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // 리스너의 onMove 메소드 호출
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    // 사용자에 의해 swipe될 때 호출
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 리스너의 onItemSwipe 메소드 호출
        listener.onItemSwipe(viewHolder.adapterPosition)
    }
}