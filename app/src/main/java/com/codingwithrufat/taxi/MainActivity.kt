package com.codingwithrufat.taxi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.codingwithrufat.taxi.fragments.ClientFragment
import com.codingwithrufat.taxi.fragments.DriverFragment
import com.codingwithrufat.taxi.models.DriverModel
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.PagerAdapter as PagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWidgets()

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DriverFragment(), "Drivers")
        adapter.addFragment(ClientFragment(), "Clients")
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)

    }

    private fun getWidgets(){

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

    }

    class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val fragmentTitle: MutableList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentTitle.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String){

            fragmentList.add(fragment)
            fragmentTitle.add(title)

        }

        override fun getPageTitle(position: Int): CharSequence? {

            return fragmentTitle[position]
        }

    }

}