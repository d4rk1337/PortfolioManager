package net.d4rk.portfoliomanager.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.d4rk.portfoliomanager.util.security.SignatureGenerator

class HomeViewModel : ViewModel() {

    private val mTextLiveData = MutableLiveData<String>().apply {
        value = SignatureGenerator.createSignature("test", "askdjakjsd")
    }

    val mText: LiveData<String> = mTextLiveData
}