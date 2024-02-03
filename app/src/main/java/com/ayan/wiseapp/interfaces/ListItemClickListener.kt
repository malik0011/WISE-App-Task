package com.ayan.wiseapp.interfaces

import com.ayan.wiseapp.models.Drink

interface ListItemClickListener {
    fun onItemClicked(data: Drink)
}