package com.pymblesoftware.cashmoney.data.kotlin

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap


/**
 * Created by reganrussell on 5/9/17.
 * (C) 2017 PymbleSoftware Pty Ltd.
 */
class CurrencyResponse {
}

data class codeRates(
        @SerializedName("code") val code: String,
        @SerializedName("rate") val rate: Float
)

/**
 * Entire search result data class
 */
data class Result (
        val base: String,
        val date: String,
//        val rates: Any
        val rates: LinkedTreeMap<String,String>
//        val rates: List<codeRates>
)