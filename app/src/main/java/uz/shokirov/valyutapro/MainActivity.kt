package uz.shokirov.valyutapro

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import uz.shokirov.adapter.FragmentSlider
import uz.shokirov.valyutapro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sliderAdapter: FragmentSlider

    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sliderAdapter = FragmentSlider(supportFragmentManager, lifecycle)

        binding.viewPager2.adapter = sliderAdapter
        binding.viewPager2.isUserInputEnabled = false
        binding.menuImage.setOnClickListener {
            binding.drawableLayout.openDrawer(Gravity.LEFT)
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_window -> {
                    binding.viewPager2.currentItem = 0
                }
                R.id.menu_all_exchange -> {
                    binding.viewPager2.currentItem = 1
                }
                R.id.menu_calculate -> {
                    binding.viewPager2.currentItem = 2
                }
            }
            true
        }

    }
}