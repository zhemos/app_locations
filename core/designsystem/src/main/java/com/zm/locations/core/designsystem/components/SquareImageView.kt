package com.zm.locations.core.designsystem.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class SquareImageView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : AppCompatImageView(context, attrs, defStyleAttr) {

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context
    ) : this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        )
    }
}