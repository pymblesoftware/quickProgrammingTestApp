package com.pymblesoftware.cashmoney

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.internal.LinkedTreeMap
import com.pymblesoftware.cashmoney.R
import com.pymblesoftware.cashmoney.data.kotlin.CurrencyRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }


    lateinit var ratesData : LinkedTreeMap<String, String>
    lateinit var currencyList : MutableSet<String>
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun fetchData() {
        val repository = CurrencyRepositoryProvider.provideCurrencyRepository()

        compositeDisposable.add(
                repository.searchCurrencies("AUD")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            result ->
                            Log.d("RR:", "Result ${result.rates.javaClass}" )
                            ratesData = result.rates
                            currencyList = result.rates.keys

                            val spinnerArray = currencyList.toTypedArray()

                            val spinnerArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray) //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

//                            currency_spinner.adapter = spinnerArrayAdapter
//                            currency_spinner.setOnClickListener {  doCalc() ; Log.v("RR:", "here" ) }

                        }, { error ->
                            error.printStackTrace()
                        })
        )

    }

    fun doCalc() {


    }


}
