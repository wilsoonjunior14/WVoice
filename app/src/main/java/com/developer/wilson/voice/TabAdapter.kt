package com.developer.wilson.voice

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.developer.wilson.voice.fragments.Tab1
import com.developer.wilson.voice.fragments.Tab2
import com.developer.wilson.voice.fragments.Tab3

class TabAdapter(context: Context, fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return Tab1() as Fragment
            1 -> return Tab2() as Fragment
            2 -> return Tab3() as Fragment
        }
        return Tab1() as Fragment
    }

    override fun getCount(): Int {
        return 3;
    }

}