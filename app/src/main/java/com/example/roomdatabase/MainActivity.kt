package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.ActivityAddUserBinding
import com.example.roomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserListAdapter
    val repo:UserRepository by lazy {
        UserRepository(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        adapter = UserListAdapter()
        binding.recyclerviewUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewUsers.adapter = adapter

        adapter.setOnItemClick(object : ListClickListener<Users>{
            override fun onClick(data: Users, position: Int) {
                val intent = Intent(this@MainActivity,AddUserActivity::class.java)
                intent.putExtra("user", data)
                startActivity(intent)
            }

            override fun onDelete(user: Users) {
                repo.deleteUser(user)
                fetchUsers()
            }
        })


        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity,AddUserActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        val allUsers = repo.getAllUsers()
        adapter.setUsers(allUsers)
    }
}