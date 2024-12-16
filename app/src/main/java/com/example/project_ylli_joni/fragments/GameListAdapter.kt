package com.example.project_ylli_joni.fragments

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.project_ylli_joni.R

data class Game(val name: String, val users: Int, val imageResId: Int)
class GameListAdapter(
    private val context: Context,
    private val onItemClick: (Game) -> Unit
) {

    private val games = mutableListOf(
        Game("Hell divers 2", 1000000, R.drawable.game_image_1),
        Game("Dragons dogma 2", 2000000, R.drawable.game_image_2),
        Game("Palworld", 3000000, R.drawable.game_image_3),
        Game("WWE 2k24", 4000000, R.drawable.game_image_4),
        Game("Persona 3 reload", 5000000, R.drawable.game_image_5),
        Game("Suicide squad", 6000000, R.drawable.game_image_6),
        Game("tekken", 7000000, R.drawable.game_image_7),
        Game("Final fantasy", 800000000, R.drawable.game_image_8),
        Game("Skull and bones", 90000000, R.drawable.game_image_9),
        Game("MLB the show", 1000000, R.drawable.game_image_10)
    )

    fun populateGameList(container: LinearLayout) {
        container.removeAllViews()
        val inflater = LayoutInflater.from(context)

        games.forEach { game ->
            val gameView = inflater.inflate(R.layout.item_game, container, false)
            val gameImage = gameView.findViewById<ImageView>(R.id.game_image)
            val gameName = gameView.findViewById<TextView>(R.id.game_name)
            val gameUsers = gameView.findViewById<TextView>(R.id.game_users)
            val deleteButton = gameView.findViewById<Button>(R.id.delete_button)

            gameImage.setImageResource(game.imageResId)
            gameName.text = game.name
            gameUsers.text = "${game.users} users"

            deleteButton.setOnClickListener {
                AlertDialog.Builder(context)
                    .setMessage("A jeni i sigurte qe deshironi ta fshini item ${game.name}?")
                    .setPositiveButton("Po") { _, _ ->
                        games.remove(game)
                        populateGameList(container)
                    }
                    .setNegativeButton("Jo", null)
                    .show()
            }

            gameView.setOnClickListener {
                onItemClick(game)
            }

            container.addView(gameView)
        }
    }
}