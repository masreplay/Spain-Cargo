package com.enjaz.hr.ui.team

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetTeamBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment :
    BaseFragment<FramgnetTeamBinding, ITeamInteractionListener, TeamViewModel>(),
    ITeamInteractionListener {

    private val teamViewModel: TeamViewModel by viewModels()

    lateinit var teamAdapter: TeamAdapter


    override fun getLayoutId(): Int {
        return R.layout.framgnet_team
    }

    override fun getViewModel(): TeamViewModel {
        return teamViewModel
    }


    override fun getNavigator(): ITeamInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getMyTeammates()
        getViewDataBinding().rv.apply {
            adapter = teamAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamAdapter = TeamAdapter(requireContext(), mutableListOf())
    }


}

interface ITeamInteractionListener : BaseNavigator {

}


