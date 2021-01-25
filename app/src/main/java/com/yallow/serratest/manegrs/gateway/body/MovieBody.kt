package com.yallow.serratest.manegrs.gateway.body

import com.yallow.serratest.modules.master.MasterPagingBody



/**
 * @author mohammad rababah
 * desc movie body data holder to send it to discover api
 *
 */
class MovieBody : MasterPagingBody() {

    var language = "en-us"
    var sortBy = MovieSortBy.POPULAR
    var includeAdult = false.toString()
    var includeVideo = false

    /**
     * @author mohammad rababah
     * desc movie body data as a Hashmap to send it to retrofit gateway manger
     * @return hashMap to use as key value
     */
    fun loadHashMapParam(): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap["language"] = language
        hashMap["sort_by"] = sortBy.toString()
        hashMap["include_adult"] = includeAdult
        hashMap["include_video"] = includeVideo.toString()
        hashMap["page"] = page.toString()
        return hashMap

    }
    /**
     * @author mohammad rababah
     * desc enum class for sort by to prevent wornge sort value
     *
     */
    enum class MovieSortBy{

        TOP_RATED,POPULAR,REVENUE;

        override fun toString(): String {
            return when(this){
                TOP_RATED -> "vote_average.desc"
                POPULAR -> "popularity.desc"
                REVENUE -> " revenue.desc"
            }

        }

    }
}
