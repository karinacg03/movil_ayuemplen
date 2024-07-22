package com.adso.myapplicationsantes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide

class Home2Fragment : Fragment() {

    private var likeCount = 0
    private var commentCount = 0
    private lateinit var likeButton: Button
    private lateinit var likeCountTextView: TextView
    private lateinit var commentButton: Button
    private lateinit var shareButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home2, container, false)

        // Inicializar vistas
        likeButton = view.findViewById(R.id.likeButton)
        likeCountTextView = view.findViewById(R.id.likeCount)
        commentButton = view.findViewById(R.id.commentButton)
        shareButton = view.findViewById(R.id.shareButton)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Manejar clic en el botón de "Like"
        likeButton.setOnClickListener {
            // Incrementar el contador de likes
            likeCount++
            // Actualizar el texto del contador de likes
            likeCountTextView.text = "$likeCount Likes"
            // Cambiar el icono de "Like" al hacer clic
            likeButton.setBackgroundResource(R.drawable.like2)
        }

        // Manejar clic en el botón de "Comment"
        commentButton.setOnClickListener {

             val intent = Intent(activity, Comment::class.java)
             startActivity(intent)
        }

        // Manejar clic en el botón de "Share"
        shareButton.setOnClickListener {

        }
    }
}