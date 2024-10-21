package com.sauban.digitizer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputData: DoubleArray

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val drawingView = DrawingView(this)
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)
        frameLayout.addView(drawingView)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        clearButton.setOnClickListener {
            drawingView.clearDrawing()
        }
        saveButton.setOnClickListener {
            inputData = drawingView.prepareGrayscaleBitmapData()
            drawingView.invalidate()
            logInputData(inputData)
        }
    }

    private fun logInputData(data: DoubleArray) {
        val loggedData = data.joinToString(", ")
        Log.d("InputData", loggedData)
    }
}
