package com.mobeedev.employees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavGraph()
    }

    private fun initNavGraph() {
        val navigationController = findNavController(R.id.fragmentNavHost)
        val navInflater = navigationController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_main)

        graph.startDestination = R.id.nav_employees
        navigationController.graph = graph
    }
}