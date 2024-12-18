package com.example.project_ylli_joni.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_ylli_joni.R
import com.example.project_ylli_joni.databinding.ActivityMainBinding
import com.example.project_ylli_joni.fragments.GameListFragment
import com.example.project_ylli_joni.fragments.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.LoginFragmentListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
    }

    override fun onLoginSuccess() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GameListFragment())
            .commit()
    }
}