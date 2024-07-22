package com.adso.myapplicationsantes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        val option1: TextView = view.findViewById(R.id.option1)
        val option2: TextView = view.findViewById(R.id.option2)
        val option3: TextView = view.findViewById(R.id.option3)

        btnLogin.setOnClickListener {
            val intent = Intent(activity, ButtonFragment::class.java)
            startActivity(intent)
        }

        option1.setOnClickListener {
            val intent = Intent(activity, ProfileFragment::class.java)
            startActivity(intent)
        }

        option2.setOnClickListener {
            val intent = Intent(activity, ReferenceActivity::class.java)
            startActivity(intent)
        }

        option3.setOnClickListener {
            val intent = Intent(activity, SkillFragment::class.java)
            startActivity(intent)
        }

        return view
    }
}