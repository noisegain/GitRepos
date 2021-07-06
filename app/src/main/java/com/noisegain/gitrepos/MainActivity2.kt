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
        textView.text = userset[from].name
        reposList.layoutManager = LinearLayoutManager(this)
        reposList.adapter = adapter
        println(userset[from].repositories.size)
        adapter.refresh(userset[from].repositories)
    }
}