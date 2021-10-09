package com.monstarlab.resultapidemo.ui.base

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.monstarlab.resultapidemo.common.contracts.handler.ContractsHandler

abstract class BaseActivity : AppCompatActivity() {

    open fun getBaseViewModel(): BaseViewModel? = null


}
