package com.example.todo_app.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo_app.R
import com.example.todo_app.adapters.ListAdapter2
import com.example.todo_app.databinding.FragmentChildBinding
import com.example.todo_app.databinding.FragmentTaskBinding
import com.example.todo_app.databinding.List2Binding
import com.example.todo_app.model.MovieViewModel
import com.example.todo_app.room.ListEntity
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskFragment : Fragment() {
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

    lateinit var binding: FragmentTaskBinding
    lateinit var listAdapter2: ListAdapter2
    lateinit var vm: MovieViewModel
    private val TAG = "TaskFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(layoutInflater,container,false)
        vm = ViewModelProvider(this).get(MovieViewModel::class.java)

        setRv()
        setUi()



        return binding.root
    }

    private fun setUi() {

        val cal = Calendar.getInstance()
        val myYear = cal.get(Calendar.YEAR)
        val myMonth = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        binding.calendar.setOnClickListener {
            val datePickerDialog = DatePickerDialog(binding.root.context,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                binding.calendarDate.setText("Date:" + dayOfMonth + "/ " + (month +1) + "/ "+ year)
                binding.calendarDate.text = "${dayOfMonth}.${month +1}.${year}"
            },myYear,myMonth,day)
            datePickerDialog.show()
            binding.calendarDate.visibility = View.VISIBLE
            binding.calendar1.visibility = View.VISIBLE
        }

        binding.alarm.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.alarmTime.text = SimpleDateFormat("HH:mm").format(cal.time)
                binding.alarmTime.visibility = View.VISIBLE
                binding.alarm1.visibility = View.VISIBLE
            }
            TimePickerDialog(binding.root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

    }

    private fun setRv() {
        vm.allMovies.observe(this, Observer { items ->

            listAdapter2 = ListAdapter2(items,object : ListAdapter2.OnItemClickListener{
                override fun onItemClick(
                    malumotlar: ListEntity,
                    malumotItemBinding: List2Binding,
                    position: Int
                ) {
                    Toast.makeText(binding.root.context, "${malumotlar.title}", Toast.LENGTH_SHORT)
                        .show()
                }


            })
            binding.categoryRv.adapter = listAdapter2


        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}