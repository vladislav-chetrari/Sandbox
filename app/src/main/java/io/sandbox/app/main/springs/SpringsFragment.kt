package io.sandbox.app.main.springs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.FloatRange
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import io.sandbox.R
import io.sandbox.app.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_springs.*

class SpringsFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_springs

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.springPosition(SpringForce.STIFFNESS_MEDIUM, SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
    }

    private fun View.springPosition(
        @FloatRange(from = .0) stiffness: Float,
        @FloatRange(from = .0) dampingRatio: Float
    ) = viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

        @SuppressLint("Range")
        private fun springForceInstance(finalPosition: Float) = SpringForce(finalPosition).apply {
            this.stiffness = stiffness
            this.dampingRatio = dampingRatio
        }

        override fun onGlobalLayout() {
            val xAnimation = SpringAnimation(this@springPosition, SpringAnimation.X)
                .apply { spring = springForceInstance(x) }
            val yAnimation = SpringAnimation(this@springPosition, SpringAnimation.Y)
                .apply { spring = springForceInstance(y) }
            var dX = 0f
            var dY = 0f
            setOnTouchListener { view, event ->
                when (event.actionMasked) {
                    ACTION_DOWN -> {
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY
                        xAnimation.cancel()
                        yAnimation.cancel()
                    }
                    ACTION_MOVE -> view.animate().x(event.rawX + dX).y(event.rawY + dY).setDuration(0).start()
                    ACTION_UP -> {
                        xAnimation.start()
                        yAnimation.start()
                    }
                }
                true
            }
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}