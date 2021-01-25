package com.yallow.serratest.modules.master
/**
 * @author mohammad rababah
 * desc master paging module which will hold the page
 */

 open class MasterPagingBody {
    var page = 1

    override fun toString(): String {
        return page.toString()
    }
}