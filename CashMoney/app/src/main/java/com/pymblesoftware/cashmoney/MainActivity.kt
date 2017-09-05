package com.pymblesoftware.cashmoney

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.internal.LinkedTreeMap
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
    lateinit var spinnerArray : Array<String>
    lateinit var valuesArray : Array<String>

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
                            valuesArray = result.rates.values.toTypedArray()

                            spinnerArray = currencyList.toTypedArray()

                            val spinnerArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray) //selected item will look like a spinner set from XML
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            currency_spinner.adapter = spinnerArrayAdapter
                            currency_spinner.setOnItemClickListener { parent, view, position, id -> doCalc( position) ; }

                        }, { error ->
                            error.printStackTrace()
                        })
        )

    }

    fun doCalc(  pos :Int ) {

        val input = input_editext.text.to(Double) as Double
        val amt = valuesArray[pos].toDouble() as Double
        val calcd = input * amt
        Log.v("RR:", "here ${pos} , ${amt} " )
        output_text.setText( "${amt}" )
    }


}
