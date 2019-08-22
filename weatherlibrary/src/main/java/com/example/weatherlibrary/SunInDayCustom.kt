package com.example.weatherlibrary

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.lang.NullPointerException
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class SunInDayCustom(context: Context, attrs: AttributeSet) : View(context, attrs) {

    val dashPath = DashPathEffect(floatArrayOf(10f, 10f), 1.0.toFloat())

    private val mArcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        pathEffect = dashPath
        isAntiAlias = true
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val mArcPaintAgain = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        pathEffect = dashPath
        isAntiAlias = true
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
    }

    private val mCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
    }

    private val mBitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
    }

    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 18F
        color = Color.GREEN
        textAlign = Paint.Align.CENTER
    }

    private lateinit var mStartHourSun: String
    private lateinit var mEndHourSun: String
    private lateinit var mNowHourSun: String

    private var mCenterX: Float = 0.0f
    private var mCenterY: Float = 0.0f
    private var mPositionSunX: Float = 0.0f
    private var mPositionSunY: Float = 0.0F
    private var mRadiusNow: Double = 0.0
    private var mLeft: Float = 0.0f
    private var mRight: Float = 0.0f
    private var mOval = RectF()


    init {
        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.SunInDayCustom)
        try {
            mStartHourSun = mTypedArray.getString(R.styleable.SunInDayCustom_start_time)!!
            mStartHourSun = mTypedArray.getString(R.styleable.SunInDayCustom_end_time)!!
            mStartHourSun = mTypedArray.getString(R.styleable.SunInDayCustom_now_time)!!
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
            MeasureSpec.AT_MOST -> min(mDesiredWidth, mWidthSize)
            else -> mDesiredWidth
        }

        mHeight = when (mHeightMode) {
            MeasureSpec.EXACTLY -> mHeightSize
            MeasureSpec.AT_MOST -> 350
            else -> mDesireHeight
        }
        setMeasuredDimension(mWidth, mHeight)
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //logic width height of arc

        val width = width.toFloat()
        val height = height.toFloat()

        val radius: Float = 250F


        mCenterX = width / 2
        mCenterY = height - 60F

        mLeft = mCenterX - radius
        val top = mCenterY - radius
        mRight = mCenterX + radius
        val bottom = mCenterY + radius

        mOval.set(mLeft, top, mRight, bottom)


        canvas!!.drawArc(mOval, 180f, 180f, false, mArcPaint)

        canvas.drawLine(0F, mCenterY, width, mCenterY, mLinePaint)

        canvas.drawCircle(mLeft, mCenterY, 10F, mCirclePaint)
        canvas.drawCircle(mRight, mCenterY, 10F, mCirclePaint)

        canvas.drawArc(mOval, 180f, mRadiusNow.toFloat(), false, mArcPaintAgain)

        canvas.drawText(mStartHourSun, mCenterX - radius, mCenterY + 50, mTextPaint)
        canvas.drawText(mEndHourSun, mCenterX + radius, mCenterY + 50, mTextPaint)

        //logic time
        val startHourMinute = formatTime(mStartHourSun)
        val endHourMinutes = formatTime(mEndHourSun)
        val nowHourMinutes = formatTime(mNowHourSun)

        if (nowHourMinutes > startHourMinute && nowHourMinutes < endHourMinutes) {
            val scoreTime = (nowHourMinutes - startHourMinute) / (endHourMinutes - startHourMinute)
            mRadiusNow = 180 * scoreTime

            if (mRadiusNow > 90) {
                mPositionSunX = (mCenterX + sin((Math.toRadians(mRadiusNow - 90))) * radius).toFloat()
                mPositionSunY = (mCenterY - cos((Math.toRadians(mRadiusNow - 90))) * radius).toFloat()
            }
            if (mRadiusNow < 90) {
                mPositionSunX = (mCenterX - sin(Math.toRadians(90 - mRadiusNow)) * radius).toFloat()
                mPositionSunY = (mCenterY - cos(Math.toRadians(90 - mRadiusNow)) * radius).toFloat()
            }

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_sun)

            canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, 30, 30, false), mPositionSunX - 15, mPositionSunY - 15, mBitmapPaint)

        }

        invalidate()
    }

    private fun formatTime(time: String): Double {

        val hour = time.subSequence(0, 2).toString().toInt()
        val minute = time.subSequence(3, 5).toString().toInt()

        return (hour * 60 + minute).toDouble()
    }

    fun updateTime(startTime: String, endTime: String, nowTime: String) {
        mStartHourSun = startTime
        mEndHourSun = endTime
        mNowHourSun = nowTime
    }


}