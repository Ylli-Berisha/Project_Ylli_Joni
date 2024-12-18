package com.example.project_ylli_joni.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ylli_joni.R

data class Game(val name: String, val users: Int, val imageResId: Int)

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val gameImage: ImageView = itemView.findViewById(R.id.game_image)
    val gameName: TextView = itemView.findViewById(R.id.game_name)
    val gameUsers: TextView = itemView.findViewById(R.id.game_users)
    val deleteButton: Button = itemView.findViewById(R.id.delete_button)
}

class GameListAdapter(
    private val context: Context,
    private val games: MutableList<Game>,
    private val onItemClick: (Game) -> Unit
) : RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.gameImage.setImageResource(game.imageResId)
        holder.gameName.text = game.name
        holder.gameUsers.text = "${game.users} users"

        holder.deleteButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setMessage("A jeni i sigurte qe deshironi ta fshini item ${game.name}?")
                .setPositiveButton("Po") { _, _ ->
                    games.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, games.size)
                }
                .setNegativeButton("Jo", null)
                .show()
        }

        holder.itemView.setOnClickListener {
            onItemClick(game)
        }
    }

    override fun getItemCount(): Int = games.size
}