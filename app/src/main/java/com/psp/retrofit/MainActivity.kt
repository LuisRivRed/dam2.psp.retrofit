package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.psp.retrofit.data.StudentDataRepository
import com.psp.retrofit.data.remote.RetrofitClient
import com.psp.retrofit.presentation.ClassroomFragment
import com.psp.retrofit.presentation.StudentFragment
import com.psp.retrofit.presentation.StudentViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val repository: StudentDataRepository by lazy {
        val apiService = RetrofitClient.apiService
        StudentDataRepository(apiService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_bar)
        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_students -> StudentFragment()
                R.id.nav_classrooms -> ClassroomFragment()
                else -> StudentFragment()
            }
            loadFragment(fragment)
            true
        }
        loadFragment(StudentFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}