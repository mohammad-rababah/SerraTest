package com.yallow.serratest.manegrs.gateway

import com.yallow.serratest.modules.ErrorModule
/**
 * @author mohammad rababah
 * desc master api response that handle any api response
 *
 */
interface MasterResponse<T> {
    fun onResponseError(errorModule: ErrorModule?){}
    fun onNetworkFail(exception:Throwable?){}
    fun onSuccess(responseObject:T?)

}
