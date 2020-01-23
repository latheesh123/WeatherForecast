package latheesh.com.openweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import latheesh.com.openweather.adapter.ForecastListAdapter


class MainFragment : Fragment() {

    var navController: NavController? = null
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recyclerView = view.findViewById(R.id.recycler)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager

        view.findViewById<Button>(R.id.weatherSubmitButton).setOnClickListener {
            viewModel.getDetails(
                view.findViewById<EditText>(R.id.weatherCityEntryText).text.toString()
            )
        }

        viewModel.weatherResponse.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = ForecastListAdapter(viewModel.weatherResponse.value)

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}
