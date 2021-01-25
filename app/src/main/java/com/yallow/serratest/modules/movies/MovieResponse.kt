package com.yallow.serratest.modules.movies

import com.yallow.serratest.master.MasterRecycleViewPagingAdapter
import com.yallow.serratest.modules.master.MasterRecycleViewResponse
import java.util.ArrayList

/**
 * @author mohammad rababah
 * desc movie paging  response object
 */
class MovieResponse : MasterRecycleViewResponse<Movie>() {
    override var results= ArrayList<Movie>()

}