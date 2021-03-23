package ctatum.animecatalog.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ctatum.animecatalog.databinding.MainFragmentBinding
import ctatum.animecatalog.models.*
import ctatum.animecatalog.repository.GetData
import ctatum.animecatalog.repository.RetrofitClient
import ctatum.animecatalog.viewmodels.MainViewModel
import retrofit2.Call
import retrofit2.Response


class MainFragment : Fragment() {

    // View Binding has taken the place of synthetics, and the naming is basically the module name + "Binding". Autocomplete finds the object name
    // after view binding has been set to 'true' in the Gradle file.

    private var _binding: MainFragmentBinding? = null               // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private var items = listOf<Result>()

    private var animeItemAdapter: AnimeItemAdapter? = null

    private lateinit var viewModel: MainViewModel

    private var AnimeImages: AnimeImage? = null

    private var imageCounter: Int = 0
    private var totalImageCount: Int = 0


    // RecyclerView support
    // Credit: https://developer.android.com/guide/topics/ui/layout/recyclerview
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnSubmit.setOnClickListener {

            val searchArg = binding.edtSearchArg.text.toString()
            var missingInput: Boolean = false


            if (searchArg == null) {
                missingInput = true
            } else {
                if (searchArg == "") {
                    missingInput = true
                }
            }
            if (missingInput) {
                Toast.makeText(activity, "Please enter a search value.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val service = RetrofitClient.retrofitInstance.create(GetData::class.java)
            val call = service.getImageResults(searchArg)
            //val call = service.getImageResults(searchArg)

            println(">>> call = $call.toString()")
            println(">>> searchArg = $searchArg")

            // NOW, PARSE THE RESULTS

            // Execute the request asynchronously
            // NOTE the qualifier for "Callback" - there are two kinds

            call.enqueue(object : retrofit2.Callback<AnimeImage> {

                // Handle a successful response

                override fun onResponse(call: Call<AnimeImage>, response: Response<AnimeImage>) {

                    println(">>> **************************** RESPONSE *******************************")
                    println(">>> *********************************************************************")
                    println(">>> **************************** RESPONSE *******************************")

                    println(">>> RESPONSE BODY = " + response.body())

                    // Credit:
                    // https://www.journaldev.com/13639/retrofit-android-example-tutorial

                    AnimeImages =
                        response.body()         // response is a JSON List of AnimeImages so this works

                    println(">>> hash: ${AnimeImages?.getRequestHash()}")
                    println(">>> last page: ${AnimeImages?.getLastPage()}")
                    println(">>> expiry: ${AnimeImages?.getRequestCacheExpiry()}")
                    println(">>> size: ${AnimeImages?.getResults()?.size}")

                    totalImageCount = AnimeImages!!.getResults()!!.size

                    items = AnimeImages!!.getResults() as List<Result>

                    for (n in AnimeImages!!.getResults()!!.indices) {
                        var imageResult = AnimeImages?.getResults()!![n]
                        println(">>> imageResult URL $n: ${imageResult?.getImageUrl()}")
                    }

                    // HERE'S WHERE WE MOVE DATA TO THE RCV

                    println(">>> MOVING DATA TO RCV")
                    animeItemAdapter = AnimeItemAdapter(
                        AnimeImages?.getResults() as List<Result>,
                        { item -> animeItemTapped(item) }
                    )

                    binding.rcvAnimeImages.adapter = animeItemAdapter
                    binding.rcvAnimeImages.layoutManager =
                        LinearLayoutManager(context)     // default orientation is vertical

                    refreshRecyclerView()

                    if (items.size < 50) {
                        Toast.makeText(activity, "$items items returned.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            activity,
                            "Maximum of 50 items returned.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    println(">>> ****************************** END **********************************")
                    println(">>> *********************************************************************")
                    println(">>> ****************************** END **********************************")

                    //doPicasso()

                }


                // Handle execution failures

                override fun onFailure(call: Call<AnimeImage>, throwable: Throwable) {

                    //If the request fails, then display the following toast//

                    println(">>> throwable = " + throwable.message)
                    println(">>> throwable = " + throwable.stackTrace)

                    Toast.makeText(activity, "Unable to get AnimeImages", Toast.LENGTH_SHORT).show()

                }

            })

        }

        return view

    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // TODO: Use the ViewModel

    }

    private fun refreshRecyclerView() {

        viewManager = LinearLayoutManager(activity)

        viewAdapter = AnimeItemAdapter(items) { item -> animeItemTapped(item) }

        binding.rcvAnimeImages.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = AnimeItemAdapter(items, { item -> animeItemTapped(item) })

        }
    }

    private fun animeItemTapped(result: Result) {
        println(">>> animeItemTapped() fired from MainFragment")
        Toast.makeText(activity, "Showing information for ${result.getTitle()}", Toast.LENGTH_SHORT).show()

        val i = Intent(activity, AnimeDetailActivity::class.java)
        i.putExtra("result", result)
        startActivity(i)
    }

}