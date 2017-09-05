package com.pymblesoftware.cashmoney.data.kotlin

/**
 * Created by reganrussell on 5/9/17.
 * (C) 2017 PymbleSoftware Pty Ltd.
 */


object CurrencyRepositoryProvider {

    fun provideCurrencyRepository(): CurrencyRepository {
        return CurrencyRepository(CurrencyApiService.create())
    }

}