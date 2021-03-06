package com.example.orbitmvidemopost.app.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.orbitmvidemopost.R

class SeparatorDecoration(
    context: Context, @DimenRes marginStart: Int, @DimenRes marginEnd: Int
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()
    private val thickness: Int
    private val startMargin: Int
    private val endMargin: Int

    init {
        paint.color = ContextCompat.getColor(context, R.color.separator)

        thickness = context.resources.getDimensionPixelSize(R.dimen.separator_thickness)
        startMargin = context.resources.getDimensionPixelSize(marginStart)
        endMargin = context.resources.getDimensionPixelSize(marginEnd)

        paint.strokeWidth = thickness.toFloat()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as RecyclerView.LayoutParams

        if (!isLastItem(params, state)) {
            outRect.set(0, 0, 0, thickness)
        } else {
            outRect.isEmpty
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // Line is drawn offset by half the width to ensure correct positioning
        val offset = (paint.strokeWidth / 2).toInt()

        val layoutDirection = ViewCompat.getLayoutDirection(parent)
        val leftMargin = if (layoutDirection == View.LAYOUT_DIRECTION_LTR) startMargin else endMargin
        val rightMargin = if (layoutDirection == View.LAYOUT_DIRECTION_LTR) endMargin else startMargin

        for (view in parent.children) {
            val params = view.layoutParams as RecyclerView.LayoutParams

            if (!isLastItem(params, state)) {
                canvas.drawLine(
                    (view.left + leftMargin).toFloat(),
                    (view.bottom + offset).toFloat(),
                    (view.right - rightMargin).toFloat(),
                    (view.bottom + offset).toFloat(),
                    paint
                )
            }
        }
    }

    private fun isLastItem(params: RecyclerView.LayoutParams, state: RecyclerView.State) =
        params.viewAdapterPosition == state.itemCount - 1
}