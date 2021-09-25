package com.example.myapplicationcatrecicleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationcatrecicleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  lateinit  var list:MutableList<Cat>
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        list = mutableListOf<Cat>(Cat("barsik",10),Cat("murzik",10),Cat("lllll",10))

        var adapter = AddAdapter(list)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
            return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
             adapter?.filter?.filter(p0)
                return true
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
    }

}