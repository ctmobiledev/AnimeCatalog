package ctatum.animecatalog.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Result : Serializable
{

    // Properties

    @SerializedName("mal_id")
    @Expose
    private var malId: Int = 0

    @SerializedName("url")
    @Expose
    private var url: String = ""

    @SerializedName("image_url")
    @Expose
    private var imageUrl: String = ""

    @SerializedName("title")
    @Expose
    private var title: String = ""

    @SerializedName("airing")
    @Expose
    private var airing: Boolean = true

    @SerializedName("synopsis")
    @Expose
    private var synopsis: String = ""

    @SerializedName("type")
    @Expose
    private var type: String = ""

    @SerializedName("episodes")
    @Expose
    private var episodes: Int = 0

    @SerializedName("score")
    @Expose
    private var score: Double = 0.0

    @SerializedName("start_date")
    @Expose
    private var startDate: String = ""

    @SerializedName("end_date")
    @Expose
    private var endDate: String = ""

    @SerializedName("members")
    @Expose
    private var members: Int = 0

    @SerializedName("rated")
    @Expose
    private var rated: String = ""


    // Methods

    fun getMalId(): Int {
        return malId
    }

    fun setMalId(malId: Int) {
        this.malId = malId
    }

    fun getUrl(): String {
        return url
    }

    fun setUrl(url:String) {
        this.url = url
    }

    fun getImageUrl(): String {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String ) {
        this.imageUrl = imageUrl
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title;
    }

    fun getAiring(): Boolean {
        return airing
    }

    fun setAiring(airing: Boolean) {
        this.airing = airing
    }

    fun getSynopsis(): String {
        return synopsis
    }

    fun setSynopsis(synopsis: String) {
        this.synopsis = synopsis
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getEpisodes(): Int {
        return episodes
    }

    fun setEpisodes(episodes: Int) {
        this.episodes = episodes
    }

    fun getScore(): Double {
        return score
    }

    fun setScore(score: Double) {
        this.score = score
    }

    fun getStartDate(): String {
        return startDate
    }

    fun setStartDate(startDate: String) {
        this.startDate = startDate
    }

    fun getEndDate(): String {
        return endDate
    }

    fun setEndDate(endDate: String) {
        this.endDate = endDate
    }

    fun getMembers(): Int {
        return members
    }

    fun setMembers(members: Int) {
        this.members = members
    }

    fun getRated(): String {
        return rated;
    }

    fun setRated(rated: String) {
        this.rated = rated
    }

}
