package com.zm.locations.core.designsystem.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.zm.locations.core.designsystem.R
import com.zm.locations.core.designsystem.ext.dp

class AddButtonView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_WIDTH_STROKE = 2f
    }

    private var widthStroke: Float = DEFAULT_WIDTH_STROKE.dp

    private val paintMain = Paint().also {
        it.color = Color.BLACK
    }
    private val paintContent = Paint().also {
        it.color = Color.parseColor("#FECE57")
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context
    ) : this(context, null)

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.AddButtonView, 0, 0
        ).apply {
            try {
                initAttrs(this)
            } finally {
                recycle()
            }
        }
    }

    private fun initAttrs(typed: TypedArray) = with(typed) {
        widthStroke = getDimension(R.styleable.AddButtonView_widthStroke, DEFAULT_WIDTH_STROKE.dp)
    }

    private val rectHorizontal = RectF()
    private val rectVertical = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = width / 2f
        canvas.drawCircle(width / 2f, height / 2f, radius - 4f.dp, paintMain)
        paintMain.strokeWidth = widthStroke
        rectHorizontal.set(
            0f,
            height.toFloat() / 2 - widthStroke,
            width.toFloat(),
            height.toFloat() / 2 + widthStroke
        )
        rectVertical.set(
            width.toFloat() / 2 - widthStroke,
            0f,
            width.toFloat() / 2 + widthStroke,
            height.toFloat()
        )
        canvas.drawRoundRect(rectHorizontal, 10f, 10f, paintMain)
        canvas.drawRoundRect(rectVertical, 10f, 10f, paintMain)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        )
    }
}