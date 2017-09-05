package com.pymblesoftware.cashmoney.data.kotlin

//import com.segunfamisa.kotlin.samples.retrofit.data.kotlin.Result


/**
 * Created by reganrussell on 5/9/17.
 * (C) 2017 PymbleSoftware Pty Ltd.
 */

// http://api.fixer.io/latest?base=USD


//fun search(@retrofit2.http.Query("q") query: String,
//               @retrofit2.http.Query("page") page: Int = 1,
//               @retrofit2.http.Query("per_page") perPage: Int = 20): io.reactivex.Observable<Result>
//

interface CurrencyApiService {

    @retrofit2.http.GET("/latest")
    fun search(@retrofit2.http.Query("/latest?base") query: String): io.reactivex.Observable<Result>


    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): CurrencyApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("http://api.fixer.io/")
                    .build()

            return retrofit.create(CurrencyApiService::class.java);
        }
    }
}