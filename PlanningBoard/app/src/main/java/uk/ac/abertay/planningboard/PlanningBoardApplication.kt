package uk.ac.abertay.planningboard

import android.app.Application
import timber.log.Timber

class PlanningBoardApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


    }
}