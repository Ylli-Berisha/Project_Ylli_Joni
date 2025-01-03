package com.example.project_ylli_joni.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ylli_joni.R

class GameListFragment : Fragment() {

    private val gameList = mutableListOf<Game>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = GameListAdapter(requireContext(), gameList) { game ->
            parentFragmentManager.commit {
                replace(R.id.fragment_container, GameDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString("game_name", game.name)
                        putInt("game_users", game.users)
                        putInt("game_image", game.imageResId)
                    }
                })
                addToBackStack(null)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        populateGameList()
        adapter.notifyDataSetChanged()

        view.findViewById<RecyclerView>(R.id.recycler_view)?.adapter?.notifyDataSetChanged()

        return view
    }

    private fun populateGameList() {
        gameList.clear()
        gameList.addAll(getGamesFromDataSource())
    }

    private fun getGamesFromDataSource(): List<Game> {
        return listOf(
            Game("Hell divers 2", 1000000, R.drawable.game_image_1),
            Game("Dragons dogma 2", 2000000, R.drawable.game_image_2),
            Game("The Witcher 3", 3000000, R.drawable.game_image_3),
            Game("Cyberpunk 2077", 4000000, R.drawable.game_image_4),
            Game("Red Dead Redemption 2", 5000000, R.drawable.game_image_5),
            Game("God of War", 6000000, R.drawable.game_image_6),
            Game("Horizon Zero Dawn", 7000000, R.drawable.game_image_7),
            Game("Ghost of Tsushima", 8000000, R.drawable.game_image_8),
            Game("Assassin's Creed Valhalla", 9000000, R.drawable.game_image_9),
            Game("Elden Ring", 10000000, R.drawable.game_image_10)
        )
    }
}