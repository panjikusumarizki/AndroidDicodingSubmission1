package com.goji.githubusers

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataUsername = resources.getStringArray(R.array.data_username)
            val dataAvatar = resources.obtainTypedArray(R.array.data_avatar)
            val dataCompany = resources.getStringArray(R.array.data_company)
            val dataLocation = resources.getStringArray(R.array.data_location)
            val dataRepository = resources.obtainTypedArray(R.array.data_repository)
            val dataFollower = resources.obtainTypedArray(R.array.data_follower)
            val dataFollowing = resources.obtainTypedArray(R.array.data_following)
            val listUser = ArrayList<User>()

            for (i in dataName.indices) {
                val user = User(
                    dataName[i],
                    dataUsername[i],
                    dataAvatar.getResourceId(i, -1),
                    dataCompany[i],
                    dataLocation[i],
                    dataRepository.getInt(i, -1),
                    dataFollower.getInt(i, -1),
                    dataFollowing.getInt(i, -1)
                )
                listUser.add(user)
            }

            return listUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }

        val listUserAdapter = ListUserAdapter(list)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        val users = User(
            user.name,
            user.username,
            user.avatar,
            user.company,
            user.location,
            user.repository,
            user.follower,
            user.following
        )

        val detailActivity = Intent(this@MainActivity, DetailActivity::class.java)
        detailActivity.putExtra(DetailActivity.EXTRA_USER, users)
        startActivity(detailActivity)
    }
}