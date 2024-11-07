package com.bugbender.customviewxml.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bugbender.customviewxml.R
import com.bugbender.customviewxml._1_basic_shapes.BasicShapesFragment
import com.bugbender.customviewxml._2_drag_circle.DragCircleFragment
import com.bugbender.customviewxml._2_drag_circle.DragCircleView
import com.bugbender.customviewxml._3_basic_animation.BasicAnimationFragment
import com.bugbender.customviewxml._4_path_animation.PathAnimationFragment
import com.bugbender.customviewxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val items = listOf(
        MainItem("1.Basic shapes(line, rectangle, circle)", BasicShapesFragment::class.java),
        MainItem("2.Drag circle", DragCircleFragment::class.java),
        MainItem("3.Basic animation", BasicAnimationFragment::class.java),
        MainItem("4.Path animation", PathAnimationFragment::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val adapter = MainAdapter(items = items, fragmentManager = supportFragmentManager)
        binding.mainRecyclerView.adapter = adapter
    }
}