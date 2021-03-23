package ctatum.animecatalog.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AnimeImage
{

    // Properties

    @SerializedName("request_hash")
    @Expose
    private var requestHash: String? = null

    @SerializedName("request_cached")
    @Expose
    private var requestCached: Boolean? = null

    @SerializedName("request_cache_expiry")
    @Expose
    private var requestCacheExpiry: Int? = null

    @SerializedName("results")
    @Expose
    private var results: List<Result>? = null

    @SerializedName("last_page")
    @Expose
    private var lastPage: Int? = null


    // Methods

    fun getRequestHash(): String? {
        return requestHash
    }

    fun setRequestHash(requestHash: String?) {
        this.requestHash = requestHash
    }

    fun getRequestCached(): Boolean? {
        return requestCached
    }

    fun setRequestCached(requestCached: Boolean?) {
        this.requestCached = requestCached
    }

    fun getRequestCacheExpiry(): Int? {
        return requestCacheExpiry
    }

    fun setRequestCacheExpiry(requestCacheExpiry: Int?) {
        this.requestCacheExpiry = requestCacheExpiry
    }

    fun getResults(): List<Result?>? {
        return results
    }

    fun setResults(results: List<Result>?) {
        this.results = results
    }

    fun getLastPage(): Int? {
        return lastPage
    }

    fun setLastPage(lastPage: Int?) {
        this.lastPage = lastPage
    }

}
