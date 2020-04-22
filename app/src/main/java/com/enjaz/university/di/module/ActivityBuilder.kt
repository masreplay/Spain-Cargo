package com.enjaz.university.di.module



import com.enjaz.university.MainActivity
import com.enjaz.university.ui.announcements.EventsFragment
import com.enjaz.university.ui.announcements.allEvents.AllEventsFragment
import com.enjaz.university.ui.finalGrades.FinalGradesFragment
import com.enjaz.university.ui.grades.GradesFragment

import com.enjaz.university.ui.home.HomeFragment
import com.enjaz.university.ui.login.LoginActivity
import com.enjaz.university.ui.schedual.ScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindLoginActivity(): LoginActivity


    @ContributesAndroidInjector
    internal abstract fun bindHomeFragmnet(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun binScheduleFramgnet(): ScheduleFragment

    @ContributesAndroidInjector
    internal abstract fun binFinalGradesFramgnet(): FinalGradesFragment


    @ContributesAndroidInjector
    internal abstract fun binGradesFramgnet(): GradesFragment


    @ContributesAndroidInjector
    internal abstract fun binEventsFragment(): EventsFragment

    @ContributesAndroidInjector
    internal abstract fun binAllEventsFragment(): AllEventsFragment





}
