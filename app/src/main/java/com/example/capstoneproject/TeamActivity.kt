package com.example.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TeamActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)
        list.addAll(listHeroes)
        showRecyclerList()
    }

    private val listHeroes: ArrayList<Team>
        get() {
            val dataName = resources.getStringArray(R.array.data_name2)
            val dataDescription = resources.getStringArray(R.array.data_description2)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo2)
            val listHero = ArrayList<Team>()
            for (i in dataName.indices) {
                val hero = Team(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1))
                listHero.add(hero)
            }
            return listHero
        }
    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = TeamAdapter(list)
        rvHeroes.adapter = listHeroAdapter
    }
}