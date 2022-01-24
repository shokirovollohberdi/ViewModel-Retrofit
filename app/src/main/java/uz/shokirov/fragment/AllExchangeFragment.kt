package uz.shokirov.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.shokirov.adapter.OnCLick
import uz.shokirov.adapter.RvAdapter
import uz.shokirov.model.Valyuta
import uz.shokirov.utils.MyViewModels
import uz.shokirov.valyutapro.MainActivity
import uz.shokirov.valyutapro.R
import uz.shokirov.valyutapro.databinding.FragmentAllExchange2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllExchangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllExchangeFragment : Fragment() {
    lateinit var binding: FragmentAllExchange2Binding
    lateinit var myViewModel: MyViewModels
    lateinit var adapter: RvAdapter
    lateinit var list: ArrayList<Valyuta>

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
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllExchange2Binding.inflate(layoutInflater)

        list = ArrayList()
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModels::class.java)
        myViewModel.getUsers().observe(viewLifecycleOwner, {
            list.addAll(it)

            adapter = RvAdapter(list,object :OnCLick{
                override fun click(valyuta: Valyuta, position: Int) {
                    (activity as MainActivity?)?.binding?.viewPager2?.currentItem = 2
                }
            })
            binding.rv.adapter = adapter
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllExchangeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllExchangeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}