package com.roulette.roulettegame.ui.activity

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import com.roulette.roulettegame.R
import com.roulette.roulettegame.mvp.contract.MainContract
import com.roulette.roulettegame.mvp.presenter.MainPresenter
import com.roulette.roulettegame.ui.router.MainRouter
import com.roulette.roulettegame.util.getRandomNumberInRange
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MainActivity : MyMvpAppCompatActivity(R.layout.activity_main), MainContract.View {

    private val MIN_ANIMATION_DURATION : Int = 5000
    private val MAX_ANIMATION_DURATION : Int = 15000

    @InjectPresenter
    lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun showScore(score: Int) {
        tvScore.text = score.toString()
    }

    override fun showWheelAnimation(degreeOld: Int, degree: Int) {
        val rotateAnimation = RotateAnimation(degreeOld.toFloat(), degree.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        rotateAnimation.duration =
            getRandomNumberInRange(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION).toLong()
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationEnd(animation: Animation?) {
                presenter.wheelAnimationEnded()
                ivSpin.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationRepeat(animation: Animation?) {}
        })

       ivWheel.startAnimation(rotateAnimation)
    }

    private fun init() {
        ivSpin.setOnClickListener {
            presenter.spinClicked()
            ivSpin.visibility = View.INVISIBLE
        }

        tvPrivacyPolicy.setOnClickListener { presenter.showPrivacyPolicyScreen() }
    }

    @ProvidePresenter
    fun provideMainPresenter() : MainPresenter {
        return MainPresenter(MainRouter(this))
    }
}