package com.roulette.roulettegame.mvp.contract

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface MainContract {

    interface View : MvpView {

        @AddToEndSingle
        fun showScore(score : Int)

        @Skip
        fun showWheelAnimation(degreeOld : Int, degree : Int)
    }

    interface Presenter {

        fun showPrivacyPolicyScreen()

        fun spinClicked()

        fun wheelAnimationEnded()
    }

    interface Router {

        fun showPrivacyPolicyScreen()
    }
}