package com.roulette.roulettegame.mvp.presenter

import com.roulette.roulettegame.mvp.contract.MainContract
import com.roulette.roulettegame.util.RANDOM
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(private val router: MainContract.Router) :
    MvpPresenter<MainContract.View>(),
    MainContract.Presenter {

    companion object {

        private val sectors = arrayOf(15, 10, 0, 3, 1, 5, 7, 0)

        private const val HALF_SECTOR = 360f / 8f/ 2f
    }

    private var degree = 0

    private var degreeOld = 0

    private var score = 0

    override fun showPrivacyPolicyScreen() {
        router.showPrivacyPolicyScreen()
    }

    override fun spinClicked() {
        degreeOld = (degree % 360)

        val randDegree = RANDOM.nextInt(360)

        if(randDegree !=0) {
            degree = (randDegree - (randDegree % (2 * HALF_SECTOR))).toInt()
        }

        degree+=1440

        viewState.showWheelAnimation(degreeOld, degree)
    }

    override fun wheelAnimationEnded() {
        score += calculateSectorScore()
        viewState.showScore(score)
    }

    private fun calculateSectorScore() : Int {
        val degrees = 360 - (degree % 360)

        if(degrees >= (360 - HALF_SECTOR) || degrees <= HALF_SECTOR) {
            return sectors[0]
        }

        var from = HALF_SECTOR
        var to = 3 * HALF_SECTOR

        for(i in 1 until sectors.size) {
            if(from < degrees && to > degrees) {

                return sectors[i]
            }

            from = (from + 2 * HALF_SECTOR) % 360
            to = (to + 2 * HALF_SECTOR) % 360
        }

        return -1
    }
}