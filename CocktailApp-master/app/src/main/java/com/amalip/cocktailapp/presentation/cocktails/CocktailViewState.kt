package com.amalip.cocktailapp.presentation.cocktails

import com.amalip.cocktailapp.core.presentation.BaseViewState
import com.amalip.cocktailapp.domain.model.Cocktail

sealed class CocktailViewState: BaseViewState() {

    data class CocktailsRecived(val cocktails: List<Cocktail>): BaseViewState()

}