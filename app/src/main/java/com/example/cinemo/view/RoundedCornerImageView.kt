package com.example.cinemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundedCornerImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val path = Path()
    private val radius = 20f // adjust the radius as per your needs

    override fun onDraw(canvas: Canvas) {
        val clipPath = Path()
        clipPath.addRoundRect(
            0f, 0f, width.toFloat(), height.toFloat(), radius, radius,
            Path.Direction.CW
        )
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }
}