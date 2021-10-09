package com.monstarlab.resultapidemo.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.monstarlab.resultapidemo.common.contracts.handler.ContractsHandler
import com.monstarlab.resultapidemo.databinding.ActivityMainBinding
import com.monstarlab.resultapidemo.ui.base.BaseActivity
import com.monstarlab.resultapidemo.ui.base.BaseViewModel
import com.monstarlab.resultapidemo.ui.result.CustomActivityResultContract
import com.monstarlab.resultapidemo.ui.result.NewResultActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor() : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun getBaseViewModel(): BaseViewModel = viewModel

    private val customResultContracts = ContractsHandler(
        CustomActivityResultContract(),
        this.activityResultRegistry, this,
        "custom"
    )

    private val singlePermissionContracts =
        ContractsHandler(
            ActivityResultContracts
                .RequestPermission(),
            this.activityResultRegistry, this,
            "single"

        )

    private val multiplePermissionsContracts =
        ContractsHandler(
            ActivityResultContracts
                .RequestMultiplePermissions(),
            this.activityResultRegistry, this,
            "multiple"
        )

    private val galleryPickerContracts =
        ContractsHandler(
            ActivityResultContracts
                .GetContent(),
            this.activityResultRegistry, this,
            "gallery"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        bindUIEvents()
        observeResultContracts()
    }


    private fun bindUIEvents() = binding.apply {

        buttonSinglePermission.setOnClickListener {
            singlePermissionContracts.launch(Manifest.permission.CAMERA)
        }

        buttonMultiplePermission.setOnClickListener {
            multiplePermissionsContracts.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
        buttonImage.setOnClickListener {
            galleryPickerContracts.launch("image/*")
        }

        buttonResult.setOnClickListener {
            customResultContracts.launch(1)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun observeResultContracts() {
        singlePermissionContracts.result.observe(this@MainActivity) {
            binding.textPermissionStatus.text = "Single Permission: $it"
        }

        multiplePermissionsContracts.result.observe(this@MainActivity) {
            binding.textPermissionStatus.text = "Multiple Permission: $it"
        }

        galleryPickerContracts.result.observe(this@MainActivity) {
            binding.image.setImageURI(it)
        }
        customResultContracts.result.observe(this@MainActivity) {
            binding.textPermissionStatus.text = "Custom Activity Result: $it"
        }
    }

}
