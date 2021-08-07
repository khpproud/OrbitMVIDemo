package com.example.orbitmvidemopost.app.common

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.orbitmvidemopost.R
import com.google.android.material.appbar.AppBarLayout

/**
 * Ensures Toolbar is not elevated when a recyclerview or nestedscrollview is scrolled to the top
 */
class ShadowScrollBehavior(
    context: Context, attrs: AttributeSet
) : AppBarLayout.ScrollingViewBehavior(context, attrs) {

    @SuppressLint("PrivateResource")
    private val maxElevation = context.resources.getDimensionPixelSize(R.dimen.design_appbar_elevation).toFloat()

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {
            when (child) {
                is NestedScrollView -> {
                    setElevation(child, dependency)
                    addScrollListener(child, dependency)
                }
                is RecyclerView -> {
                    setElevation(child, dependency)
                    addScrollListener(child, dependency)
                }
            }
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }

    private fun addScrollListener(child: NestedScrollView, dependency: AppBarLayout) {
        child.setOnScrollChangeListener { _: NestedScrollView?, _: Int, _: Int, _: Int, _: Int ->
            setElevation(child, dependency)
        }
    }

    private fun addScrollListener(child: RecyclerView, dependency: AppBarLayout) {
        child.clearOnScrollListeners()
        child.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                setElevation(recyclerView, dependency)
            }
        })
    }

    private fun setElevation(view: View, appBarLayout: AppBarLayout) {
        val elevation = if (view.canScrollVertically(SCROLL_DIRECTION_UP)) maxElevation else 0f

        ViewCompat.setElevation(appBarLayout, elevation)
    }

    companion object {
        private const val SCROLL_DIRECTION_UP = -1
    }
}