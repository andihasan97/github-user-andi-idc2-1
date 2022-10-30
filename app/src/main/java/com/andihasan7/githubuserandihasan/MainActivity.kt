package com.andihasan7.githubuserandihasan

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.andihasan7.githubuserandihasan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()

    }

    private fun showRecyclerList() {
        /*
        menambah GridLayout hanya saat orientasi landscape
         */
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }

        val listUserAdapter = UsersAdapter(list)
        binding.rvUsers.adapter = listUserAdapter
        // memanggil data ke Activity
        listUserAdapter.setOnItemClickCallBack(object : UsersAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        Toast.makeText(this, "Kamu memilih "+ user.name, Toast.LENGTH_SHORT).show()
    }

    private val listUsers: ArrayList<User>
    get() {
        val datAvatar = resources.obtainTypedArray(R.array.avatar)
        val datName = resources.getStringArray(R.array.name)
        val datUserName = resources.getStringArray(R.array.username)
        val datLocation = resources.getStringArray(R.array.location)
        val datCompany = resources.getStringArray(R.array.company)
        val datRepository = resources.getStringArray(R.array.repository)
        val datFollowers = resources.getStringArray(R.array.followers)
        val datFollowing = resources.getStringArray(R.array.following)
        val listUser = ArrayList<User>()
        for (i in datName.indices) {
            val userM = User(
                datAvatar.getResourceId(i, -1), datName[i], datUserName[i], datLocation[i], datCompany[i], datRepository[i], datFollowers[i], datFollowing[i])
            listUser.add(userM)
        }
        return listUser
    }
}