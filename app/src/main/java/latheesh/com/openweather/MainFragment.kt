package latheesh.com.openweather

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
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

        viewModel.dialog.observe(viewLifecycleOwner, Observer {
            it?.let {
                dialog(requireContext())
            }
        })
        recyclerView = view.findViewById(R.id.recycler)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager

        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE

        view.findViewById<Button>(R.id.weatherSubmitButton).setOnClickListener {
            viewModel.getDetails(
                view.findViewById<EditText>(R.id.weatherCityEntryText).text.toString(),
                requireContext()
            )
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE

        }

        viewModel.weatherResponse.observe(viewLifecycleOwner, Observer {
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
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

    fun dialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)

        // set message of alert dialog
        dialogBuilder.setMessage("Please enter the Correct city name ")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("City Name")
        // show alert dialog
        alert.show()
    }
}
