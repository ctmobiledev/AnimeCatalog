package ctatum.animecatalog.views

import android.R
import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ctatum.animecatalog.models.*

class AnimeItemAdapter(
    private val myDataset: List<Result>,
    private val clickListener: (Result) -> Unit
) : RecyclerView.Adapter<AnimeItemAdapter.MyViewHolder>() {

    private var data: List<Result>? = null

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var cardImageItem = view.findViewById(ctatum.animecatalog.R.id.cardImageItem) as CardView
        var imgAnimeThumb = view.findViewById(ctatum.animecatalog.R.id.imgAnimeThumb) as ImageView
        var txtTitle = view.findViewById(ctatum.animecatalog.R.id.txtTitle) as TextView
        var txtMembers = view.findViewById(ctatum.animecatalog.R.id.txtMembers) as TextView
        var txtRated = view.findViewById(ctatum.animecatalog.R.id.txtRated) as TextView

        fun bind(item: Result) {
            println(">>> bind() fired with Result: " + item.getTitle())
            txtTitle.text = item.getTitle()
            txtMembers.text = item.getMembers().toString() + " members"
            txtRated.text = item.getRated()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeItemAdapter.MyViewHolder {
        // create a new view
        var rcView = LayoutInflater.from(parent.context)
            .inflate(ctatum.animecatalog.R.layout.image_item_layout, parent, false)      // as TextView

        return MyViewHolder(rcView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        val picasso = Picasso.get()
        picasso.load(myDataset[position].getImageUrl()).into(holder.imgAnimeThumb)

        holder.txtTitle.text = myDataset[position].getTitle()
        holder.txtMembers.text = myDataset[position].getMembers().toString() + " members"
        holder.txtRated.text = myDataset[position].getRated()

        val tappedItem = myDataset[position]
        holder.bind(tappedItem)

        holder.cardImageItem.setOnClickListener {
            println(">>> CLICKED!")
            clickListener(tappedItem)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

}