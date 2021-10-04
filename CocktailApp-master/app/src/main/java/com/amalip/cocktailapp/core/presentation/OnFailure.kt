package com.amalip.cocktailapp.core.presentation

import com.amalip.cocktailapp.core.exception.Failure

interface OnFailure {

    fun handleFailure(failure: Failure?)

}