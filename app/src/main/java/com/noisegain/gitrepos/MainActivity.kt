package com.noisegain.gitrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.*
import com.noisegain.gitrepos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = Adapter()
    val dataset = arrayListOf(
        Repository("HelloWorld", "I'll be a next Hokage of this programming world", "Kotlin", "User2"),
        Repository("Cells", "I have got a new arm", "Cobol", "User1"),
        Repository("OldKey", "I want to know what are this cellar contains", "Kotlin", "User1"),
        Repository("Sea", "I'll kill all titans and go to the sea, witch contains salt", "Java", "User2")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(this@MainActivity)
        rcView.adapter = adapter
        adapter.refresh(dataset)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val n = arrayListOf<Repository>()
                val toSearch = newText?.lowercase()?:""
                dataset.forEach { x ->
                    if (toSearch in x.userName.lowercase()) {
                        n.add(x)
                    }
                }
                adapter.refresh(n)
                return false
            }

        })
    }
}