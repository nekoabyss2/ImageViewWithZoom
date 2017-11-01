package com.egci428.imageviewwithzoom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

/**
 * Created by 6272user on 11/1/2017 AD.
 */
class imageViewWithZoom(contex: Context) : View(contex) {

    private var image:Drawable? = null
    private var scaleFactor = 1.0F
    private var scaleGestureDetector : ScaleGestureDetector? = null

    init {
        imageViewWithZoom(contex)
    }

    fun imageViewWithZoom(contex: Context){
        image = context.getDrawable(R.drawable.ic_launcher_background)
        setFocusable(true)
        image!!.setBounds(0, 0, image!!.intrinsicWidth, image!!.intrinsicHeight)
        scaleGestureDetector = ScaleGestureDetector(contex, ScaleListener())
    }

    private inner class ScaleListener(): ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= detector!!.scaleFactor
            scaleFactor = Math.max(0.1F,Math.min(scaleFactor, 20.0F))//limit size of image
            invalidate()
            return true

        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas!!.save()
        canvas!!.scale(scaleFactor,scaleFactor)
        image!!.draw(canvas)
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(event)
        invalidate()
        return true
    }
}