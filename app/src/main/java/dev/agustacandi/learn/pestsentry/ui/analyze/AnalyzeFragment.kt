package dev.agustacandi.learn.pestsentry.ui.analyze

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.yalantis.ucrop.UCrop
import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.base.BaseFragment
import dev.agustacandi.learn.pestsentry.data.lib.ApiResponse
import dev.agustacandi.learn.pestsentry.databinding.FragmentAnalyzeBinding
import dev.agustacandi.learn.pestsentry.utils.Helper
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import dev.agustacandi.learn.pestsentry.utils.ext.gone
import dev.agustacandi.learn.pestsentry.utils.ext.setDisable
import dev.agustacandi.learn.pestsentry.utils.ext.setEnable
import dev.agustacandi.learn.pestsentry.utils.ext.showSessionDialog
import org.koin.android.ext.android.inject
import java.io.File
import java.util.Date

class AnalyzeFragment : BaseFragment<FragmentAnalyzeBinding>() {

    private var currentImageUri: Uri? = null

    private val analyzeViewModel: AnalyzeViewModel by inject()
    private val preferenceManager: PreferenceManager by inject()

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            launchUCrop(uri)
        }
    }

    private val launcherCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            launchUCrop(currentImageUri!!)
        }
    }

    private val launcherUCrop =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultUri = UCrop.getOutput(result.data!!)
                if (resultUri != null) {
                    currentImageUri = resultUri
                    setImagePreview()
                }
            }
        }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAnalyzeBinding = FragmentAnalyzeBinding.inflate(inflater, container, false)

    override fun initIntent() {
    }

    override fun initUI() {
        binding.plantImage.load(R.drawable.img_placeholder) {
            transformations(RoundedCornersTransformation(16f))
        }
    }

    override fun initAction() {
        binding.apply {
            appbar.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_analyzeFragment_to_historyFragment)
            }

            cameraButton.setOnClickListener {
                currentImageUri = Helper.getImageUri(requireActivity())
                launcherCamera.launch(currentImageUri)
            }

            galleryButton.setOnClickListener {
                launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            analyzePlantPestButton.setOnClickListener {
                if (currentImageUri != null) {
                    if (analyzeViewModel.analyzeImageResult.value == null) {
                        analyzeViewModel.predictPest(currentImageUri!!)
                    }
                } else {
                    Helper.showErrorToast(
                        requireActivity(),
                        getString(R.string.you_have_not_selected_an_image)
                    )
                }
            }

            analyzePlantDiseaseButton.setOnClickListener {

            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
        if (analyzeViewModel.analyzeImageResult.value == null) {
            analyzeViewModel.analyzeImageResult.observe(viewLifecycleOwner) { result ->
                binding.apply {
                    when (result) {
                        is ApiResponse.Loading -> {
                            disableButton()
                            progressIndicator.show()
                        }

                        is ApiResponse.Success -> {
                            val capitalizedString =
                                result.data.prediction.replaceFirstChar { it.uppercase() }

                            Helper.showSuccessToast(requireActivity(), capitalizedString)

                            val navigateToResultFragment =
                                AnalyzeFragmentDirections.actionAnalyzeFragmentToResultFragment(
                                    capitalizedString, currentImageUri.toString(), getString(R.string.analyze)
                                )

                            findNavController().navigate(navigateToResultFragment)
                            analyzeViewModel.setToNull()
                        }

                        is ApiResponse.Error -> {
                            if (result.errorMessage.contains("401")) {
                                showSessionDialog(
                                    onClick = {
                                        try {
                                            preferenceManager.clearAllPreferences()
                                            Helper.reloadKoinModules()
                                            findNavController().navigate(R.id.action_analyzeFragment_to_loginFragment)
                                        } catch (e: Exception) {
                                            Helper.showErrorToast(requireActivity(), e.message.toString())
                                        }
                                    }
                                )
                            }
                            Helper.showErrorToast(requireActivity(), result.errorMessage)
                            enableButton()
                            progressIndicator.gone()
                            analyzeViewModel.setToNull()
                        }

                        else -> progressIndicator.gone()
                    }
                }
            }
        }
    }

    private fun launchUCrop(uri: Uri) {
        val timestamp = Date().time
        val cachedImage = File(requireActivity().cacheDir, "cropped_image_${timestamp}.jpg")

        val destinationUri = Uri.fromFile(cachedImage)

        val uCrop = UCrop.of(uri, destinationUri).withAspectRatio(1f, 1f)

        uCrop.getIntent(requireContext()).apply {
            launcherUCrop.launch(this)
        }
    }

    private fun setImagePreview() {
        currentImageUri?.let {
            binding.plantImage.load(it) {
                transformations(RoundedCornersTransformation(16f, 16f, 16f, 16f))
            }
        }
    }

    private fun enableButton() {
        binding.apply {
            cameraButton.setEnable()
            galleryButton.setEnable()
            analyzePlantPestButton.setEnable()
            analyzePlantDiseaseButton.setEnable()
        }
    }

    private fun disableButton() {
        binding.apply {
            cameraButton.setDisable()
            galleryButton.setDisable()
            analyzePlantPestButton.setDisable()
            analyzePlantDiseaseButton.setDisable()
        }
    }

}