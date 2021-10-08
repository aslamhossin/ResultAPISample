package com.monstarlab.resultapidemo.ui.base

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

  val singlePermissionResult: MutableLiveData<Boolean> = MutableLiveData()
  val multiplePermissionsResult: MutableLiveData<Boolean> = MutableLiveData()
  var picUriResult: MutableLiveData<Uri> = MutableLiveData()
  val activityResult: MutableLiveData<String> = MutableLiveData()
}