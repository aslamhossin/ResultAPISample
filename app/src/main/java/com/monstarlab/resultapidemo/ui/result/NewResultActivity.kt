package com.monstarlab.resultapidemo.ui.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.monstarlab.resultapidemo.databinding.ActivityNewResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewResultActivity : AppCompatActivity() {

  private lateinit var binding: ActivityNewResultBinding

  companion object {

    const val TITLE = "title"
    const val ACTIVITY_LAUNCHER_KEY = "ID"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityNewResultBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.buttonResultBack.setOnClickListener {
      provideRequestedResult()
    }

  }

  private fun provideRequestedResult() {
    val launcherKey = intent.extras?.get(ACTIVITY_LAUNCHER_KEY)
    val intent = Intent().apply {
      putExtra(TITLE, "Result from New API and Key: $launcherKey")
    }
    setResult(RESULT_OK, intent)
    finish()
  }

}