package com.pymblesoftware.cashmoney.data.kotlin

//import com.segunfamisa.kotlin.samples.retrofit.data.kotlin.GithubApiService
//import com.segunfamisa.kotlin.samples.retrofit.data.kotlin.Result

/**
 * Created by reganrussell on 5/9/17.
 * (C) 2017 PymbleSoftware Pty Ltd.
 */
class CurrencyRepository(val apiService: CurrencyApiService) {
    fun searchCurrencies(base: String): io.reactivex.Observable<Result> {
        return apiService.search(query = "base:$base")
    }
}