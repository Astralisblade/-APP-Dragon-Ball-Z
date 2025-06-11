package com.example.dragonballz.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonballz.R
import com.example.dragonballz.data.CharacterAdapter
import com.example.dragonballz.data.CharacterResponse
import com.example.dragonballz.data.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        recyclerView = findViewById(R.id.rvCharacters)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getCharacters()
    }

    private fun getCharacters() {
        val call = RetrofitClient.instance.getCharacters()
        call.enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    val characters = response.body()?.items
                    if (characters != null) {
                        recyclerView.adapter = CharacterAdapter(characters)
                    } else {
                        showError("No se encontraron personajes")
                    }
                } else {
                    showError("Error del servidor: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                showError("Fallo de conexión: ${t.message}")
                Log.e("API_ERROR", t.stackTraceToString())
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this@ListActivity, message, Toast.LENGTH_LONG).show()
    }
}
