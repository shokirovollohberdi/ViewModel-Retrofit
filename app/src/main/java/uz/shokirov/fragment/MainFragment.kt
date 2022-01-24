package uz.shokirov.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.top_tablayout_item.view.*
import uz.shokirov.adapter.ExchangeAdapter
import uz.shokirov.model.Valyuta
import uz.shokirov.transformer.ZoomOutPageTransformer
import uz.shokirov.utils.MyViewModels
import uz.shokirov.valyutapro.R
import uz.shokirov.valyutapro.databinding.FragmentMain2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    lateinit var binding: FragmentMain2Binding
    lateinit var list: ArrayList<Valyuta>
    lateinit var exchangeAdapter: ExchangeAdapter
    lateinit var myViewModel: MyViewModels

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMain2Binding.inflate(layoutInflater)

        list = ArrayList()

        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModels::class.java)
        myViewModel.getUsers().observe(viewLifecycleOwner, {
            list.addAll(it)
            list.forEach {
                if (it.nbu_cell_price == "")
                    it.nbu_cell_price = it.cb_price
                if (it.nbu_buy_price == "")
                    it.nbu_buy_price = it.cb_price
            }
            exchangeAdapter = ExchangeAdapter(list, requireContext())

            binding.viewpager.adapter = exchangeAdapter
            binding.tabLayoutBottom.attachToPager(binding.viewpager)
            var count = 8
            binding.tabLayoutBottom.setDotCount(count)
            binding.viewpager.setPageTransformer(true, ZoomOutPageTransformer())
            setTab()
            binding.tabLayout.setupWithViewPager(binding.viewpager)
            binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
                /**
                 * Called when a tab enters the selected state.
                 *
                 * @param tab The tab that was selected
                 */
                override fun onTabSelected(tab: TabLayout.Tab?) {

                }

                /**
                 * Called when a tab exits the selected state.
                 *
                 * @param tab The tab that was unselected
                 */
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                /**
                 * Called when a tab that is already selected is chosen again by the user. Some applications may
                 * use this action to return to the top level of a category.
                 *
                 * @param tab The tab that was reselected.
                 */
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

        })





        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    private fun setTab() {
        val tabCount = binding.tabLayout.tabCount
        var tablist = ArrayList<String>()
        for (valyuta in list.indices) {
            tablist.add(list[valyuta].code)
        }
        for (i in 0 until tabCount) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.top_tablayout_item, null, false)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = view

            view.tvCountryCode.text = tablist[i]

            if (i == 0) {
                view.tvCountryCode.setTextColor(Color.WHITE)
                view.root.setBackgroundColor(R.color.dark_green)
            } else {
                view.tvCountryCode.setTextColor(Color.GRAY)
                view.root.setBackgroundColor(android.R.color.transparent)
            }
        }
    }
/*
    private fun setTab() {
        val tabCount = tab_layout.tabCount

        val tabList= arrayListOf<String>("Talaba", "Telefon", "Kino")

        for (i in 0 until tabCount){
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null, false)
            val tab = tab_layout.getTabAt(i)
            tab?.customView = view

            view.item_txt_tab.text = tabList[i]

            if (i == 0){
                view.item_txt_tab.setTextColor(Color.RED)
                view.indicator_tab.visibility = View.VISIBLE
            }else{
                view.item_txt_tab.setTextColor(Color.GREEN)
                view.indicator_tab.visibility = View.INVISIBLE
            }
        }
    }
*/

}