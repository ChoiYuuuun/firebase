package com.example.test5.recycler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.test5.R
import com.example.test5.util.ItemTouchHelperListener
import com.google.android.material.snackbar.Snackbar

// Listener 인터페이스를 구현
class ListAdapter(val nameList: MutableList<String>)
    : RecyclerView.Adapter<RecyclerViewHolder>(), ItemTouchHelperListener {
    // 뷰 초기화하는 메소드
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample_item, parent, false)
        return RecyclerViewHolder(view)
    }

    // 항목 초기화
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(nameList[position])
    }

    // 리스트의 사이즈
    override fun getItemCount(): Int {
        return nameList.size
    }

    // 아이템을 드래그되면 호출되는 메소드
    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val name = nameList[from_position]
        // 리스트 갱신
        nameList.removeAt(from_position)
        nameList.add(to_position, name)

        // fromPosition에서 toPosition으로 아이템 이동 공지
        notifyItemMoved(from_position, to_position)
        return true
    }

    // 아이템 스와이프되면 호출되는 메소드
    override fun onItemSwipe(position: Int) {
        // 리스트 아이템 삭제
        nameList.removeAt(position)
        // 아이템 삭제되었다고 공지
        notifyItemRemoved(position)

    }

}

// ViewHolder 클래스
class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameView = view.findViewById<TextView>(R.id.name_view)
    val ClickView = view.findViewById<LinearLayout>(R.id.vhLayout)

    // 뷰에 값 셋팅
    fun onBind(name: String) {
        nameView.text = name
        ClickView.setOnClickListener{
            Snackbar.make(it, "이 항목을 옮기려면 위/아래로 드래고, 없애려면 왼쪽으로 밀어주세요.", Snackbar.LENGTH_SHORT).show()
        }

    }
}
