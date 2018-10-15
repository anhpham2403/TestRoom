package com.example.anhpham.testroomdatabase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.anh.exchangerate.widget.SimpleItemTouchHelperCallback
import com.example.anhpham.testroomdatabase.database.AppDatabase
import com.example.anhpham.testroomdatabase.utils.DatabaseInitializer


class MainActivity : AppCompatActivity(), UserAdapter.OnStartDragListener {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mUserAdapter: UserAdapter
    private lateinit var mItemTouchHelper: ItemTouchHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recycler)
        DatabaseInitializer.populateWithTestData(AppDatabase.getAppDatabase(this)!!)
        mUserAdapter = UserAdapter(DatabaseInitializer.getUsers(AppDatabase.getAppDatabase(this)!!), this)
        mRecyclerView.adapter = mUserAdapter
        val callback = SimpleItemTouchHelperCallback(mUserAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper.startDrag(viewHolder)
    }
}
