package com.amalip.cocktailapp.data.source

import com.amalip.cocktailapp.core.exception.Failure
import com.amalip.cocktailapp.core.functional.Either
import com.amalip.cocktailapp.core.plataform.NetworkHandler
import com.amalip.cocktailapp.data.api.CocktailApi
import com.amalip.cocktailapp.data.dto.CocktailsResponse
import com.amalip.cocktailapp.domain.repository.CocktailRepository
import com.amalip.cocktailapp.framework.ApiRequest
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(private val cocktailApi: CocktailApi, private val networkHandler: NetworkHandler): CocktailRepository, ApiRequest {


    override fun getCocktailsByName(name: String) = makeRequest(networkHandler, cocktailApi.getCocktailsByName("margarita"), { it }, CocktailsResponse(
            emptyList()))



}