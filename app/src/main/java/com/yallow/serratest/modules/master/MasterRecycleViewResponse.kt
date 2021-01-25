package com.yallow.serratest.modules.master


import java.util.ArrayList
/**
 * @author mohammad rababah
 * desc master recycle paging view module which responsible for any paging recycle view
 * @sample MovieResponse
 */
abstract class MasterRecycleViewResponse<T>: MasterModule() {
    var total_results = 0
    var page = "0"
    abstract var results: ArrayList<T>

}
