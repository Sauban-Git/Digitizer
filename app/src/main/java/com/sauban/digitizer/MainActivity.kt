package com.sauban.digitizer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawingView = DrawingView(this)
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)
        frameLayout.addView(drawingView)
        val clearButton = findViewById<Button>(R.id.clearButton)
        clearButton.setOnClickListener {
            drawingView.clearDrawing()
        }
    }
}
