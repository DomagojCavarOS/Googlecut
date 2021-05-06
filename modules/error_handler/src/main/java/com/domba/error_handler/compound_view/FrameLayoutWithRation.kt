package com.domba.error_handler.compound_view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.domba.error_handler.R

const val INIT_INT = -1

class FrameLayoutWithRatio(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context!!, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null, INIT_INT)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, INIT_INT)

    var aspectRatio: Float = 1f

    init {
        val typedArray = context!!.theme.obtainStyledAttributes(attrs, R.styleable.FrameLayoutWithRatio, 0, 0)
        try {
            aspectRatio = typedArray.getFloat(R.styleable.FrameLayoutWithRatio_frameRatio, 1f)
        } finally {
            typedArray.recycle()
        }
        if (aspectRatio == 0f)
            aspectRatio = 1f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val originalWidth = MeasureSpec.getSize(widthMeasureSpec)
        val wantedHeight = Math.round(originalWidth / aspectRatio)

        super.onMeasure(MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(wantedHeight, MeasureSpec.EXACTLY))
    }
}