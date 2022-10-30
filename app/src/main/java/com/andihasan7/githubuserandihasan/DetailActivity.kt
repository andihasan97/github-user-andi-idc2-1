package com.andihasan7.githubuserandihasan

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.andihasan7.githubuserandihasan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent?.let {
            val user = it.getParcelableExtra<User>(FIELD)
            if (user != null) {
                val id = this.resources.getIdentifier(user.avatar.toString(), "drawable" ,this.packageName)

                Handler(Looper.getMainLooper())
                    .postDelayed(
                        {
                            binding.apply {
                                dtImgAvatarUser.setImageResource(id)
                                dtTvName.text = user.name.toString()
                                dtTvUName.text = user.username.toString()
                                dtTvCompany.text = user.company.toString()
                                dtTvLocation.text = user.location.toString()
                                tvRepository.text = user.repository.toString()
                                tvFollowers.text = user.followers.toString()
                                tvFollowing.text = user.following.toString()
                            }
                        }, 300
                    )
            }
        }

    }

    companion object {
        const val FIELD = "field"
    }


}