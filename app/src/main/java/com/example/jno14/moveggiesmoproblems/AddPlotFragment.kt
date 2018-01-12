package com.example.jno14.moveggiesmoproblems

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A placeholder fragment containing a simple view.
 */
class AddPlotFragment : Fragment() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_plot, container, false)
    }

    companion object {
        fun newInstance(): AddPlotFragment {
            val fragment = AddPlotFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
