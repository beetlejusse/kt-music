package com.app.kt_music

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")
        retrofitData.enqueue(object : retrofit2.Callback<DeezerData> {


            override fun onResponse(
                call: retrofit2.Call<DeezerData>,
                response: retrofit2.Response<DeezerData>
            ) {
                val dataList = response.body()?.data!!
//                val textView = findViewById<TextView>(R.id.helloText)
//                textView.text = dataList.toString()

                myAdapter = DataAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.d("TAG: onResponse", "onResponse: " + response.body())
            }

            override fun onFailure(call: retrofit2.Call<DeezerData>, t: Throwable) {
                Log.d("TAG: onFailure", "onFailure: " + t.message)
                println("Error: ${t.message}")
            }
        })
    }
}