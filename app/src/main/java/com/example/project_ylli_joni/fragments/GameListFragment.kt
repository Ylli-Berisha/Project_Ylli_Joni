package com.example.project_ylli_joni.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.project_ylli_joni.R

class GameListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        val gameListLayout = view.findViewById<LinearLayout>(R.id.game_list_layout)

        val adapter = GameListAdapter(requireContext()) { game ->
            parentFragmentManager.commit {
                replace(R.id.fragment_container, GameDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString("name", game.name)
                        putInt("users", game.users)
                        putInt("imageResId", game.imageResId)
                    }
                })
                addToBackStack(null)
            }
        }

        adapter.populateGameList(gameListLayout)

        return view
    }
}