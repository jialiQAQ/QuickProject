package com.alex.quickapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import com.alex.quickapp.base.BaseViewModel
import com.alex.quickapp.constant.SnStateConstant
import com.alex.quickapp.data.ScannedSnRepository
import com.alex.quickapp.model.ScannedSn

class ScannedSnViewModel internal constructor(
    private val mRepository: ScannedSnRepository,
    private val mStateHandle: SavedStateHandle
) : BaseViewModel() {
    companion object {
        const val TICKET_KEY = "TICKET_KEY"
        const val SCANNED_SN = "SCANNED_SN"
    }

    private val mSnList = MutableLiveData<MutableList<ScannedSn>>()

    fun queryScannedSnList(): LiveData<MutableList<ScannedSn>> {
        mStateHandle.get<String>(TICKET_KEY)?.apply {
            Transformations.map(mRepository.querySns(this)) {
                mSnList.value = it
            }
        }
        return mSnList
    }

    fun setScannedSn(sn: String) {
        mStateHandle[SCANNED_SN] = sn
    }

    fun setTicketKey(key: String) {
        mStateHandle[TICKET_KEY] = key
    }

    suspend fun insertScannedSn() {
        mStateHandle.get<String>(TICKET_KEY)?.let {
            mStateHandle.get<String>(SCANNED_SN)?.apply {
                mRepository.insertSn(it, this)
            }
        }
    }

    suspend fun deleteSnByKey() {
        mStateHandle.get<String>(TICKET_KEY)?.apply {
            mRepository.deleteSnByKey(this)
        }
    }

    fun changeSnState(errorSns: List<String>) {
        Transformations.map(mSnList) {
            for (scannedSn in it) {
                if (errorSns.contains(scannedSn.sn)) {
                    scannedSn.state = SnStateConstant.ERROR
                }
            }
        }
    }

}