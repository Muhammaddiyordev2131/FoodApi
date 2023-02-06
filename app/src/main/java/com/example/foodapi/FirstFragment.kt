package com.example.foodapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapi.adapter.FoodAdapter
import com.example.foodapi.databinding.FragmentFirstBinding
import com.example.foodapi.model.FoodData
import com.example.foodapi.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val adapter2 by lazy { FoodAdapter() }
    private val TAG = "@@@"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allCode()

    }
    private fun allCode() {
        binding.recyclerView2.apply {
            adapter = adapter2
            layoutManager = LinearLayoutManager(requireContext())
        }
        getAllUsers()
        binding.foodSearch.setOnClickListener {
            RetroInstance.apiService().searchFoods("4033fe66e0ab4f31b72846fe85317235",binding.textInputLayout.text.toString().trim()).enqueue(object  :
                Callback<FoodData> {
                override fun onResponse(
                    call: Call<FoodData>,
                    response: Response<FoodData>
                ) {
                    if (response.isSuccessful){

                        adapter2.submitList(response.body()?.foodList)
                    }
                }

                override fun onFailure(call: Call<FoodData>, t: Throwable) {
                    Toast.makeText(requireContext(), "Errorrrrr", Toast.LENGTH_SHORT).show()
                }
            })


        }

    }
    private fun getAllUsers() {
        RetroInstance.apiService().getFoods().enqueue(object : Callback<FoodData> {
            override fun onResponse(call: Call<FoodData>, response: Response<FoodData>) {
                if (response.isSuccessful){
                    adapter2.submitList(response.body()?.foodList)
                    Log.d("@@@", "onResponse: ${response.body()?.foodList}")
                    binding.progressBar.isVisible =  false
                }
                else{
                    Toast.makeText(requireContext(), "THIISI ERRRPR", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FoodData>, t: Throwable) {
                Toast.makeText(requireContext(), "Errorrrrr", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}