package com.goji.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setTitle("Detail User")

        val imgUser: ImageView = findViewById(R.id.img_detail_user)
        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvUsername: TextView = findViewById(R.id.tv_detail_username)
        val tvCompany: TextView = findViewById(R.id.tv_detail_company)
        val tvRepository: TextView = findViewById(R.id.tv_detail_repository)
        val tvFollower: TextView = findViewById(R.id.tv_detail_follower)
        val tvFollowing: TextView = findViewById(R.id.tv_detail_following)
        val tvLocation: TextView = findViewById(R.id.tv_detail_location)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        Glide.with(this)
            .load(user.avatar)
            .circleCrop()
            .into(imgUser)

        tvName.text = user.name
        tvUsername.text = user.username
        tvCompany.text = user.company
        tvRepository.text = user.repository.toString()
        tvFollower.text = user.follower.toString()
        tvFollowing.text = user.following.toString()
        tvLocation.text = user.location
    }
}