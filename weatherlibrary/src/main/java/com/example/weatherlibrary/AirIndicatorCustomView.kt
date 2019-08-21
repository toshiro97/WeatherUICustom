package com.toshiro.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.weatherlibrary.R
import java.lang.NullPointerException

class AirIndicatorCustomView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private var mListProgressItem = mutableListOf<ProgressItem>()
    private var mPositionProgress: Int = 0
    private var tvAirIndicator: TextView
    private var tvTitle: TextView
    private var tvDescription: TextView
    private var progressCustom: ProgressCustom

    init {
        inflate(context, R.layout.custom_air_indicator_layout, this)

        tvAirIndicator = findViewById(R.id.tvAirIndicator)
        tvTitle = findViewById(R.id.tvTitle)
        tvDescription = findViewById(R.id.tvDescription)
        progressCustom = findViewById(R.id.progress_custom)

        mListProgressItem = progressCustom.initListProgressItem()

        val mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.AirIndicatorCustomView)
        try {
            mPositionProgress = mTypedArray.getString(R.styleable.AirIndicatorCustomView_progress_position)!!.toInt()
        } catch (e: NullPointerException) {

        } finally {
            mTypedArray.recycle()
        }
    }

    fun updateProgress(progressItem: Int) {

        progressCustom.mPositionProgress = progressItem

        tvAirIndicator.text = progressItem.toString()

        when (progressItem) {
            in 0..50 -> {
                tvTitle.text = mListProgressItem[0].title
                tvDescription.text = mListProgressItem[0].description
            }

            in 50..100 -> {
                tvTitle.text = mListProgressItem[1].title
                tvDescription.text = mListProgressItem[1].description
            }

            in 100..150 -> {
                tvTitle.text = mListProgressItem[2].title
                tvDescription.text = mListProgressItem[2].description
            }

            in 150..200 -> {
                tvTitle.text = mListProgressItem[3].title
                tvDescription.text = mListProgressItem[3].description
            }

            in 200..300 -> {
                tvTitle.text = mListProgressItem[4].title
                tvDescription.text = mListProgressItem[4].description
            }

            in 300..500 -> {
                tvTitle.text = mListProgressItem[5].title
                tvDescription.text = mListProgressItem[5].description
            }
        }
    }
}