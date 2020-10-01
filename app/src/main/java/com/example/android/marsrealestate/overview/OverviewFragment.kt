/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.databinding.FragmentOverviewBinding
import com.example.android.marsrealestate.network.MarApiFilter
import com.example.android.marsrealestate.network.MarsProperty


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
//        val binding = GridViewItemBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        viewModel.clickedProperty.observe(viewLifecycleOwner){
            navToDetailFragment(it)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun navToDetailFragment(property: MarsProperty) {
        NavHostFragment.findNavController(this).navigate(OverviewFragmentDirections.actionShowDetail(property.id))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_all_menu -> viewModel.updateFilter(MarApiFilter.SHOW_ALL)
            R.id.show_buy_menu -> viewModel.updateFilter(MarApiFilter.SHOW_BUY)
            R.id.show_rent_menu -> viewModel.updateFilter(MarApiFilter.SHOW_RENT)
        }

        return true
    }
}
