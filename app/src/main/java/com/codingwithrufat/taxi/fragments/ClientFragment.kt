package com.codingwithrufat.taxi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingwithrufat.taxi.R
/**
 * A simple [Fragment] subclass.
 * Use the [ClientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_client, container, false)

        return view
    }
}