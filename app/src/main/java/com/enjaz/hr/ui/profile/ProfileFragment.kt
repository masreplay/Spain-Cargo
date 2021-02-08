package com.enjaz.hr.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.net.toFile
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentProfileBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.ui.login.LoginActivity
import com.enjaz.hr.util.PrefsManager
import com.enjaz.hr.util.UploadRequestBody
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getProfileInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

    private fun showSheet() {
        val btnsheet = layoutInflater.inflate(R.layout.sheet_profile, null)
        val dialog = BottomSheetDialog(this.requireContext())
        dialog.setContentView(btnsheet)
        dialog.findViewById<LinearLayout>(R.id.lyt_remove)?.setOnClickListener {
            getViewModel().deleteProfilePicture()
            dialog.dismiss()
        }
        dialog.findViewById<LinearLayout>(R.id.lyt_edit)?.setOnClickListener {
            val intent = CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .getIntent(requireActivity())
            startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDeleteProfilePhotoClick() {

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

    override fun logout() {
        MaterialAlertDialogBuilder(
            requireActivity()
        )
            .setTitle(getString(R.string.confirm_logout))
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.logout)) { _, _ ->
                PrefsManager.instance?.clearPreferences()
                val intent=Intent(requireActivity(),LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            .show()
    }


    override fun onEditProfilePhotoClick() {
        showSheet()
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
                        UploadRequestBody(
                            resultUri.toFile(),
                            "image",
                            "jpg"
                        )
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
    fun onDeleteProfilePhotoClick()
    fun onBalanceClick()
    fun onSalaryDetailsClick()
    fun onSettingsClick()
    fun logout()
    fun onEditProfilePhotoClick()
    fun detailsAvailable()
    fun noDetails()
    fun hideLeaveCreditView()
    fun showLeaveCreditView()
}
