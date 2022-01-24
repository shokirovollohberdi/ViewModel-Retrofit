package uz.shokirov.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.shokirov.fragment.AllExchangeFragment
import uz.shokirov.fragment.CalculateFragment
import uz.shokirov.fragment.MainFragment

class FragmentSlider(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                return MainFragment()
            }
            1->{
                return AllExchangeFragment()
            }
            2->{
                return CalculateFragment()
            }
            else -> MainFragment()
        }
    }
}