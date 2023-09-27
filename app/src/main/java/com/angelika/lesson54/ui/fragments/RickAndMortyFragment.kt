package com.angelika.lesson54.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.angelika.lesson54.R
import com.angelika.lesson54.databinding.FragmentRickAndMortyBinding
import com.angelika.lesson54.ui.adapters.CharacterAdapter

class RickAndMortyFragment : Fragment() {

    private var _binding: FragmentRickAndMortyBinding? = null
    private val binding get() = _binding!!
    private val characterAdapter = CharacterAdapter()
    private val viewModel by viewModels<RickAndMortyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRickAndMortyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribeToCharacter()
    }

    private fun initialize() {
        binding.recycler.apply {
            adapter = characterAdapter
        }
    }

    private fun subscribeToCharacter() {
        viewModel.characterLiveData.observe(viewLifecycleOwner) {
            it.error?.let {
                Log.e("error", "You have big problems bro")
                Toast.makeText(requireContext(), "You have big problems bro", Toast.LENGTH_SHORT)
                    .show()
            }
            viewModel.getData()
            it.success?.let { character ->
                characterAdapter.submitList(character)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
