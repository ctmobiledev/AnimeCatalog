package ctatum.animecatalog.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ctatum.animecatalog.R
import ctatum.animecatalog.databinding.ActivityAnimeDetailBinding
import ctatum.animecatalog.databinding.MainFragmentBinding

class AnimeDetailActivity : AppCompatActivity() {

    // View Binding has taken the place of synthetics, and the naming is basically the module name + "Binding". Autocomplete finds the object name
    // after view binding has been set to 'true' in the Gradle file.

    private var binding: ActivityAnimeDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val resultObject = intent.extras!!.get("result") as ctatum.animecatalog.models.Result?

        if (resultObject == null) {
            println(">>> NULL FOUND")
        }

        println(">>> onCreate fired")
        println(">>> resultObject values: ")
        println(">>> Title: " + resultObject?.getTitle())

        /*
         {
         "mal_id":20,
         "url":"https:\/\/myanimelist.net\/anime\/20\/Naruto",
         "image_url":"https:\/\/cdn.myanimelist.net\/images\/anime\/13\/17405.jpg?s=59241469eb470604a792add6fbe7cce6",
         "title":"Naruto",
         "airing":false,
         "synopsis":"Moments prior to Naruto Uzumaki's birth, a huge demon known as the Kyuubi, the Nine-Tailed Fox, attacked Konohagakure, the Hidden Leaf Village, and wreaked havoc. In order to put an end to the Kyuubi'...",
         "type":"TV",
         "episodes":220,
         "score":7.91,
         "start_date":"2002-10-03T00:00:00+00:00",
         "end_date":"2007-02-08T00:00:00+00:00",
         "members":1780972,
         "rated":"PG-13"
          },
         */

        binding?.txtTitle?.text = resultObject?.getTitle()
        binding?.txtDetailInfo?.text =
                                "Members: " + resultObject?.getTitle().toString() + "\n" +
                                "Rated: " + resultObject?.getRated().toString() + "\n" +
                                "MAL ID: " + resultObject?.getMalId().toString() + "\n" +
                                "URL: " + resultObject?.getUrl().toString() + "\n" +
                                "Image URL: " + resultObject?.getImageUrl().toString() +"\n" +
                                "Airing: " + resultObject?.getAiring().toString() +"\n" +
                                "Type: " + resultObject?.getType().toString() +"\n" +
                                "Episodes: " + resultObject?.getEpisodes().toString() +"\n" +
                                "Score: " + resultObject?.getScore().toString() +"\n" +
                                "Start Date: " + resultObject?.getStartDate().toString() +"\n" +
                                "End Date: " + resultObject?.getEndDate().toString() +"\n\n" +
                                "Synopsis:\n" + resultObject?.getSynopsis().toString()

    }

}