package com.enjaz.hr.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toFile
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.enjaz.hr.R
import com.enjaz.hr.UploadRequestBody
import com.enjaz.hr.databinding.FragmentProfileBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody


@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, IProfileInteractionListener, ProfileViewModel>(),
    IProfileInteractionListener {

    private val profileViewModel: ProfileViewModel by viewModels()
    lateinit var newImageUri: Uri


    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun getViewModel(): ProfileViewModel {
        return profileViewModel
    }

    override fun getNavigator(): IProfileInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getProfileInfo()

        getViewModel().updateUserProfileResponse.observe(requireActivity(), Observer { resource ->
            resource?.data?.let {
                getViewDataBinding().image.setImageURI(newImageUri)
                getViewModel().getProfileInfo()
            }
        })

    }

    override fun onPersonalDetailsClick() {
        findNavController().navigate(R.id.userInfoFragment)
    }

    override fun onBalanceClick() {
        findNavController().navigate(R.id.balanceFragment)
    }


    override fun onSalaryDetailsClick() {
        findNavController().navigate(R.id.salaryDetailsFragment)
    }

    override fun onSettingsClick() {
        findNavController().navigate(R.id.settingsFragment)
    }

    override fun onEditProfilePhotoClick() {
        val intent = CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .setAspectRatio(1, 1)
            .getIntent(requireActivity())


        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
    }

    override fun detailsAvailable() {
        TODO("Not yet implemented")
    }

    override fun noDetails() {
        TODO("Not yet implemented")
    }

    override fun hideLeaveCreditView() {
        TODO("Not yet implemented")
    }

    override fun showLeaveCreditView() {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val resultUri: Uri = result.uri
                newImageUri = resultUri
                getViewModel().updateUserProfile(
                    MultipartBody.Part.createFormData(
                        "File",
                        resultUri.toFile().name,
                        UploadRequestBody(resultUri.toFile(), "image", "jpg")
                    )
                )
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

}

interface IProfileInteractionListener : BaseNavigator {
    fun onPersonalDetailsClick()

    fun onBalanceClick()
    fun onSalaryDetailsClick()
    fun onSettingsClick()
    fun onEditProfilePhotoClick()
    fun detailsAvailable()
    fun noDetails()
    fun hideLeaveCreditView()
    fun showLeaveCreditView()
}
