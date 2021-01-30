package com.example.veggierecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class InstructionsFragment(private val recipe:Recipe) : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addDetails(view)
    }

    private fun addDetails(view: View) {

        view.findViewById<TextView>(R.id.instruction).text = recipe.instructions

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_instructions, container, false)

    }


}