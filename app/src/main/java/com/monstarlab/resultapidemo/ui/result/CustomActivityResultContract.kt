package com.monstarlab.resultapidemo.ui.result

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class CustomActivityResultContract : ActivityResultContract<Int, String?>() {

  override fun createIntent(context: Context, input: Int) =
    Intent(context, NewResultActivity::class.java).apply {
      putExtra(NewResultActivity.ACTIVITY_LAUNCHER_KEY, input)
    }

  override fun parseResult(resultCode: Int, intent: Intent?): String? {
    val title = intent?.getStringExtra(NewResultActivity.TITLE)
    return if (resultCode == Activity.RESULT_OK && title != null) title else null
  }
}