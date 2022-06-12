package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FruitAdapter
    private lateinit var viewModel: MainModel
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Character>()

    companion object {
        const val NAME = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setAdapter()
        rvHeroes = findViewById(R.id.recyclerView)
        rvHeroes.setHasFixedSize(true)
        list.addAll(listHeroes)
        showRecyclerList()

        supportActionBar?.hide()
    }

    private val listHeroes: ArrayList<Character>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listHero = ArrayList<Character>()
            for (i in dataName.indices) {
                val hero = Character(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1)
                )
                listHero.add(hero)
            }
            return listHero
        }
    private fun showRecyclerList() {
        rvHeroes.layoutManager = GridLayoutManager(this@MainActivity, 2)
        val listHeroAdapter = ListCharacterAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListCharacterAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Character) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Character) {
        val person = Character(
            hero.name,
            hero.description,
            hero.photo
        )
        Toast.makeText(this, "You Choose " + hero.name, Toast.LENGTH_SHORT).show()
        val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveIntent.putExtra(NAME, person)
        startActivity(moveIntent)
    }

    fun setAdapter(){
        adapter = FruitAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainModel::class.java]
        viewModel.setSeacrhUsers()

        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

            viewModel.getSearchUser().observe(this@MainActivity, {
                if (it!=null){
                    adapter.setList(it)
                }
            })
        }

    }
}