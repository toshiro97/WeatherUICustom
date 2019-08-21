package com.toshiro.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.weatherlibrary.R
import java.lang.NullPointerException


class MoonCustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val mCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.parseColor("#F5EDED")
        strokeWidth = 3F

    }

    private val mCirclePaintInside = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#F5EDED")

    }

    private val mOvalPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#F5EDED")
    }

    private var mPercentMoon: Double = 0.0
    private var mPercent: Float = 0F

    init {
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MoonCustomView)
        try {
            mPercentMoon = mTypedArray.getString(R.styleable.MoonCustomView_percent_moon)!!.toDouble()
        } catch (e: NullPointerException) {

        } finally {
            mTypedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val mDesiredWidth = width
        val mDesireHeight = height
        val mWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val mWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val mHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val mHeightSize = MeasureSpec.getSize(heightMeasureSpec)

        val mWidth: Int
        val mHeight: Int

        mWidth = when (mWidthMode) {
            MeasureSpec.EXACTLY -> mWidthSize
            MeasureSpec.AT_MOST -> 110
            else -> mDesiredWidth
        }

        mHeight = when (mHeightMode) {
            MeasureSpec.EXACTLY -> mHeightSize
            MeasureSpec.AT_MOST -> 110
            else -> mDesireHeight
        }
        setMeasuredDimension(mWidth, mHeight)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (mPercentMoon >= 0.5) {
            mPercent = (mPercentMoon * 100).toFloat()

            val mCircleRectF = RectF(2F, 2F, 102f, 102f)
            canvas!!.drawOval(mCircleRectF, mCirclePaint)

            val mOvalRecF = RectF(102 - mPercent, 2F, mPercent, 102F)
            canvas.drawOval(mOvalRecF, mOvalPaint)

            val mArcRecF = RectF(2F, 2F, 102F, 102F)
            canvas.drawArc(mArcRecF, 91F, 180F, false, mOvalPaint)
        } else {

            mPercent = (mPercentMoon * 100).toFloat()

            val mCircleRectFInside = RectF(2F, 2F, 102F, 102F)
            canvas!!.drawOval(mCircleRectFInside, mCirclePaintInside)

            val mCircleRectF = RectF(2F, 2F, 102f, 102f)
            canvas.drawOval(mCircleRectF, mCirclePaint)

            mOvalPaint.color = Color.BLACK

            val mOvalRecF = RectF(mPercent, 2F, 102 - mPercent, 102F)
            canvas.drawOval(mOvalRecF, mOvalPaint)

            val mArcRecF = RectF(2F, 2F, 102F, 102F)
            canvas.drawArc(mArcRecF, -91F, 180F, false, mOvalPaint)


        }

    }

    fun updateMoon(percent: Double) {
        mPercentMoon = percent
    }

}