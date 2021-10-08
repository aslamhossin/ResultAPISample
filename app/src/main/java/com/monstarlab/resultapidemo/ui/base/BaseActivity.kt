package com.monstarlab.resultapidemo.ui.base

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

  open fun getBaseViewModel(): BaseViewModel? = null

  val singlePermissionContracts =
    registerForActivityResult(
      ActivityResultContracts
        .RequestPermission()
    ) {
      getBaseViewModel()?.singlePermissionResult?.value = it
    }

  val multiplePermissionsContracts =
    registerForActivityResult(
      ActivityResultContracts
        .RequestMultiplePermissions()
    ) { permissions: Map<String, Boolean> ->
      val values: Set<Boolean> = HashSet<Boolean>(permissions.values)
      val isUnique = values.size == 1
      getBaseViewModel()?.multiplePermissionsResult?.value = isUnique
    }

  val galleryPickerContracts =
    registerForActivityResult(
      ActivityResultContracts
        .GetContent()
    ) {
      getBaseViewModel()?.picUriResult?.value = it
    }


}