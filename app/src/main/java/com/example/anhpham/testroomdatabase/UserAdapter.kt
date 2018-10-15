package com.example.anhpham.testroomdatabase

import android.graphics.Color
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.anh.exchangerate.widget.ItemTouchHelperAdapter
import com.example.anh.exchangerate.widget.ItemTouchHelperViewHolder
import com.example.anhpham.testroomdatabase.entity.User
import java.util.*


class UserAdapter(data: List<User>, onStartDragListener: OnStartDragListener) : RecyclerView.Adapter<UserAdapter.ViewHoder>(), ItemTouchHelperAdapter {
    private var mOnStartDragListener: OnStartDragListener = onStartDragListener

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mData, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mData, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    private var mData: List<User> = data

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHoder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.item_user, p0, false)
        return ViewHoder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHoder, p1: Int) {
        p0.firstName.text = mData[p1].firstName
        p0.lastName.text = mData[p1].lastName
        p0.age.text = mData[p1].age.toString()
        p0.btn.setOnTouchListener { _, event ->
            if (MotionEventCompat.getActionMasked(event) ==
                    MotionEvent.ACTION_DOWN) {
                mOnStartDragListener.onStartDrag(p0)
            }
            false
        }

    }

    inner class ViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {
        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        var firstName: TextView = itemView.findViewById(R.id.tv_first_name)
        var lastName: TextView = itemView.findViewById(R.id.last_name)
        var age: TextView = itemView.findViewById(R.id.tv_age)
        var btn: ImageView = itemView.findViewById(R.id.btn_order)
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }
}
