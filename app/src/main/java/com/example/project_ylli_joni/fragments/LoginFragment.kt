package com.example.project_ylli_joni.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.project_ylli_joni.R

data class User(val username: String, val password: String)

class LoginFragment : Fragment() {

    interface LoginFragmentListener {
        fun onLoginSuccess()
    }

    private var listener: LoginFragmentListener? = null

    private val dummyUsers = listOf(
        User("user1", "password1"),
        User("user2", "password2"),
        User("user3", "password3")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (dummyUsers.contains(User(username, password))) {
                    Toast.makeText(activity, "Login successful", Toast.LENGTH_SHORT).show()
                    listener?.onLoginSuccess()
                } else {
                    Toast.makeText(activity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement LoginFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}