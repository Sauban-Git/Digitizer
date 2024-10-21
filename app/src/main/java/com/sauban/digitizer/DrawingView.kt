package com.sauban.digitizer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import android.graphics.Bitmap

class DrawingView(context: Context) : View(context) {
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f // Increased thickness for visibility
    }
    private val path = Path()
    private var bitmap: Bitmap? = null
    private var bitmapCanvas: Canvas? = null
    private var savedBitmap: Bitmap? = null
    private var displayBitmap: Bitmap? = null

    init {
        setBackgroundColor(Color.rgb(255, 182, 193))
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bitmap?.let {canvas.drawBitmap(it, 0f, 0f, null)}
        canvas.drawPath(path, paint)
        displayBitmap?.let {
            val cornerSize = 500
            val scaledBitmap = Bitmap.createScaledBitmap(it, cornerSize, cornerSize, true)
            canvas.drawBitmap(scaledBitmap,10f, 20f, null)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                bitmapCanvas?.drawPath(path, paint)
                invalidate()
            }
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        // Handle the click event here if needed
        return true
    }

    fun clearDrawing() {
        path.reset()
        bitmap = null
        savedBitmap = null
        invalidate()
    }
    fun saveDisplay() {
        savedBitmap = createBitmapFromPath()
    }

    private fun createBitmapFromPath(): Bitmap {
        // Create a new bitmap with the same dimensions as the view
        val newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val newCanvas = Canvas(newBitmap)

        // Fill the new bitmap with a white background
        newCanvas.drawColor(Color.WHITE)

        // Draw the path onto the new canvas
        newCanvas.drawPath(path, paint)
        displayBitmap = newBitmap

        // Optional: Scale the bitmap if you need a smaller version
        return Bitmap.createScaledBitmap(newBitmap, 25, 25, true)
    }

    fun prepareGrayscaleBitmapData(bitmap: Bitmap): DoubleArray {
        val width = 25
        val height = 25
        val inputData = DoubleArray(width * height)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = bitmap.getPixel(x, y)

                // Convert the pixel to a normalized value (0.0 to 1.0) cheated from chatgpt
                val grayValue = (pixel and 0xff) / 255.0
                inputData[y * width + x] = grayValue // Flattening the 2D bitmap to 1D array
            }
        }

        return inputData
    }


}