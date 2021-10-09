package com.monstarlab.resultapidemo.common.contracts.handler

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.lifecycle.*

class ContractsHandler<I, O>(
    private val contract: ActivityResultContract<I, O>,
    private val registry: ActivityResultRegistry,
    lifecycleOwner: LifecycleOwner,
    private val key: String = "activity_rq",
) : LifecycleObserver {

    private lateinit var resultLauncher: ActivityResultLauncher<I>

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    val result: MutableLiveData<O> = MutableLiveData()

    fun launch(input: I) {
        resultLauncher.launch(input)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        resultLauncher = registry.register(
            key,
            contract
        ) { output: O ->
            result.value = output
        }
    }
}
