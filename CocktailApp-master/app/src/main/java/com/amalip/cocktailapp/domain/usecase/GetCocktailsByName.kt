package com.amalip.cocktailapp.domain.usecase

import com.amalip.cocktailapp.core.exception.Failure
import com.amalip.cocktailapp.core.functional.Either
import com.amalip.cocktailapp.core.interactor.UseCase
import com.amalip.cocktailapp.data.dto.CocktailsResponse
import com.amalip.cocktailapp.domain.repository.CocktailRepository
import javax.inject.Inject

class GetCocktailsByName @Inject constructor(private val cocktailRepository: CocktailRepository) : UseCase<CocktailsResponse, String>() {

    override suspend fun run(params: String) = cocktailRepository.getCocktailsByName(params)


}