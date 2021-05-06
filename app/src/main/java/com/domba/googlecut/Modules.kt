package com.domba.googlecut

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.domba.ErrorHandler
import com.domba.api.services.createRestService
import com.domba.api_common.RestInterface
import com.domba.api_common.interactors.match.MatchInteractor
import com.domba.api_common.interactors.match.MatchInteractorImpl
import com.domba.dummy.dummy_fragment.DummyVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { androidContext().resources }
    single { androidContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    single {
        createRestService<RestInterface>(BuildConfig.BASE_URL)
    }
    single { ErrorHandler() }
}

val interactors = module {
    factory { MatchInteractorImpl(get()) as MatchInteractor }
}

val dummyModule = module {
    viewModel { DummyVM(get()) }
}

