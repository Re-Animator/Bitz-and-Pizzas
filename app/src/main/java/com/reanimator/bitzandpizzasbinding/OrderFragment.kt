package com.reanimator.bitzandpizzasbinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.reanimator.bitzandpizzasbinding.R
import com.reanimator.bitzandpizzasbinding.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        val toolbar: MaterialToolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        binding.fab.setOnClickListener {
            try {
                val pizzaType = binding.pizzaGroup.checkedRadioButtonId

                if(pizzaType == -1) {
                    throw Exception("You need to choose a pizza type")
                }

                var text = when (pizzaType) {
                    R.id.radio_diavolo -> "Diavolo pizza"
                    else -> "Funghi pizza"
                }

                text += if(binding.parmesan.isChecked) ", extra parmesan" else ""
                text += if(binding.chiliOil.isChecked) ", extra chili oil" else ""

                Snackbar.make(binding.fab, text, Snackbar.LENGTH_LONG).show()

            } catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}