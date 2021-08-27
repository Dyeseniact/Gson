package com.example.gson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gson.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val url = "https://swapi.dev/api/people/1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJedi.setOnClickListener{
            llamarALaFuerza()
        }

        binding.btnSith.setOnClickListener{
            llamarALaFuerza()
        }

        setContentView(binding.root)
    }

    private  fun llamarALaFuerza(isSith: Boolean = false){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val clientBuilder = client.newBuilder()

        clientBuilder.build()
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()

                    try{
                        val jedi = Gson().fromJson(body, Jedi::class.java)

                        runOnUiThread{
                            binding.tvName.text = jedi.name
                            binding.tvHeight.text = jedi.height.toString()
                            binding.tvWeight.text = jedi.mass.toString()
                        }
                    } catch (e: JSONException) {

                    }
                }
            })
    }
}