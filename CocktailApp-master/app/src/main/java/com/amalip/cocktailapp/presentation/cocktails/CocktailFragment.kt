package com.amalip.cocktailapp.presentation.cocktails

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amalip.cocktailapp.R
import com.amalip.cocktailapp.core.extension.failure
import com.amalip.cocktailapp.core.extension.observe
import com.amalip.cocktailapp.core.presentation.BaseFragment
import com.amalip.cocktailapp.core.presentation.BaseViewState
import com.amalip.cocktailapp.databinding.CocktailFragmentBinding
import com.amalip.cocktailapp.domain.model.Cocktail
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CocktailFragment : BaseFragment(R.layout.cocktail_fragment) {

    private lateinit var binding: CocktailFragmentBinding

    private lateinit var adapter: CocktailAdapter
    private lateinit var adapterGrid: CocktailGridAdapter

    private lateinit var btnGrid: Button
    private lateinit var swpRefreshRow: SwipeRefreshLayout
    private lateinit var swpRefreshGrid: SwipeRefreshLayout
    var isRow:Boolean=true //COMPROBAR SI ESTA EN MODO ROW

    private val cocktailViewModel by viewModels<CocktailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)

            doGetCocktailsByName("margarita")
        }


    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when(state){
            is CocktailViewState.CocktailsRecived -> setUpAdapter(state.cocktails)
        }
    }


    private fun setUpAdapter(cocktails: List<Cocktail>){
        adapter= CocktailAdapter()
        adapterGrid= CocktailGridAdapter()


        adapter.addData(cocktails)
        adapterGrid.addData(cocktails)


            binding.rcCocktails.apply{
                adapter=this@CocktailFragment.adapter
            }



    }

    override fun setBinding(view: View) {
        binding= CocktailFragmentBinding.bind(view)



        binding.svCocktail.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(sv: String): Boolean{
                cocktailViewModel.apply {
                    doGetCocktailsByName(sv)
                }
                return true
            }

            override fun onQueryTextChange(sv: String): Boolean{
                cocktailViewModel.apply {
                    doGetCocktailsByName(sv)
                }
                return true
            }
        })


        binding.btnGrid.setOnClickListener{

            //SI ES ROW
            if(isRow){
                //SE APLICAN LOS CAMBIOS AL ROW COCKTAIL
                binding.rcCocktails.apply{
                    adapter=this@CocktailFragment.adapter
                }
                //Y SU LAYOUT MANAYER ES LINEAR
                binding.rcCocktails.layoutManager=LinearLayoutManager(requireContext())
                isRow=false
            }
            else{
                //SE APLICAN LOS CAMBIOS AL ROW COCKTAIL
                binding.rcCocktails.apply{
                    adapter=this@CocktailFragment.adapterGrid
                }
                //Y SU LAYOUT MANAYER ES TIPO GRID
                binding.rcCocktails.layoutManager=GridLayoutManager(requireContext(), 3)
                isRow=true
            }
        }

        binding.lifecycleOwner=this
    }


}