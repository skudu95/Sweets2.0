package com.kudu.sweets_admin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kudu.sweets_admin.R
import com.kudu.sweets_admin.databinding.FragmentSoldBinding

class SoldFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sold, container, false)
        val binding = FragmentSoldBinding.bind(view)


        return view
    }

}