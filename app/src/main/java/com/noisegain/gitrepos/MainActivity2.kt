package com.noisegain.gitrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    private val adapter = ReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val from = intent.getIntExtra("User", 0)
        val fromMain = intent.getBooleanExtra("Main", true)
        reposList.layoutManager = LinearLayoutManager(this)
        reposList.adapter = adapter
        if (fromMain) {
            title = filtred_userset[from].name
            println(filtred_userset[from].repositories.size)
            adapter.refresh(filtred_userset[from].repositories)
        } else {
            val user = intent.getStringExtra("Fav")
            title = user
        }
    }
}