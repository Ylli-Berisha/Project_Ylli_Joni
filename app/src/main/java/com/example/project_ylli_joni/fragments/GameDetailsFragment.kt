package com.example.project_ylli_joni.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.project_ylli_joni.R

class GameDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_details, container, false)

        val gameImage = view.findViewById<ImageView>(R.id.game_image)
        val gameName = view.findViewById<TextView>(R.id.game_name)
        val gameUsers = view.findViewById<TextView>(R.id.game_users)

        val name = arguments?.getString("game_name")
        val users = arguments?.getInt("game_users")
        val imageResId = arguments?.getInt("game_image")

        if (name != null && users != null && imageResId != null) {
            gameImage.setImageResource(imageResId)
            gameName.text = name
            gameUsers.text = "$users users"
        } else {
            gameName.text = "No game name"
            gameUsers.text = "No users"
            gameImage.setImageResource(R.drawable.game_image_1)
        }

        return view
    }
}