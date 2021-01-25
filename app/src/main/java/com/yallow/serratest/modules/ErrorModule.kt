package com.yallow.serratest.modules

import com.google.gson.annotations.SerializedName

/**
 * @author mohammad rababah
 * desc server error module in error case such as missing data
 */

class ErrorModule {
    @SerializedName("status_code")
    var statusCode  = -1
    @SerializedName("status_message")
    var statusMessage = ""
     var success =  false
}