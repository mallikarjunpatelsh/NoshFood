package com.nosh.food.ui.cook

import AppConstants.AdapterConstants.Companion.COOK_DISHES
import AppConstants.AdapterConstants.Companion.COOK_FOOTER
import AppConstants.AdapterConstants.Companion.COOK_HEADER
import AppConstants.AdapterConstants.Companion.COOK_RECOMMENDATIONS
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nosh.food.data.base.Resource
import com.nosh.food.data.service.data.Dishes
import com.nosh.food.di.qualifier.Repository
import com.nosh.food.domain.IRepository
import com.nosh.food.domain.model.CookUI
import com.nosh.food.domain.model.DataItem
import com.nosh.food.domain.model.RecommendationUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CookViewmodel @Inject constructor(@Repository private val repository: IRepository) :
    ViewModel() {
    private val _dishes = MutableStateFlow<Resource<CookUI>>(Resource.loading(true))
    val dishes = _dishes.asStateFlow()

    init {
        fetchDishes()
    }

    private fun fetchDishes() {
        viewModelScope.launch(Dispatchers.IO) {
            _dishes.emit(Resource.loading(true))
            try {
                repository.getDishes().collect {
                    _dishes.emit(
                        Resource(
                            it.status,
                            CookUI(
                                listOf(
                                    DataItem.DishesUI(
                                        COOK_HEADER,
                                        null
                                    ),
                                    DataItem.DishesUI(
                                        COOK_RECOMMENDATIONS,
                                        getRecommendatationList()
                                    ),
                                    DataItem.DishesUI(
                                        COOK_DISHES,
                                        it.data?: ArrayList()
                                    ),
                                    DataItem.DishesUI(
                                        COOK_FOOTER,
                                    null
                                )
                                )
                            ),
                            it.message,
                            it.code,
                            it.state
                        )
                    )
                }
            } catch (e: Exception) {
                _dishes.emit(Resource.error(null, "Something went wrong!", 400, false))
            }

        }
    }

    private fun getRecommendatationList() = ArrayList<RecommendationUI>().apply {
        add(
            RecommendationUI(
                "Rice items",
                "https://media.istockphoto.com/id/1345624336/photo/chicken-biriyani.jpg?s=1024x1024&w=is&k=20&c=bvTAMlq5A8Z5EhVjBn6D8eYOQS-rsuKmT9ToLkCc2Y4="
            )
        )
        add(
            RecommendationUI(
                "Indian",
                "https://media.istockphoto.com/id/1292563627/photo/assorted-south-indian-breakfast-foods-on-wooden-background-ghee-dosa-uttappam-medhu-vada.jpg?s=612x612&w=0&k=20&c=HvuYT3RiWj5YsvP2_pJrSWIcZUXhnTKqjKhdN3j_SgY="
            )
        )
        add(
            RecommendationUI(
                "Curries",
                "https://www.foodandwine.com/thmb/f4uf4WXHz-waXLB_oqG-U1p4Y7A=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/spicy-chicken-curry-FT-RECIPE0321-58f84fdf7b484e7f86894203eb7834e7.jpg"
            )
        )
        add(
            RecommendationUI(
                "Soups",
                "https://www.seriouseats.com/thmb/OA9APJ1aPo98jYVPfIzHPILNPJU=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2017__12__20171115-chicken-soup-vicky-wasik-11-80db1a04d84a43a089e0559efdddd517.jpg"
            )
        )
        add(
            RecommendationUI(
                "Desserts",
                "https://img.taste.com.au/_-tfcapo/w720-h480-cfill-q80/taste/2016/11/lemon-and-yoghurt-syrup-cakes-100035-1.jpeg"
            )
        )
        add(
            RecommendationUI(
                "Snacks",
                "https://www.checkyourfood.com/content/blob/Ingredients/French-fries-takeaway-nutritional-information-calories.jpg"
            )
        )
        add(
            RecommendationUI(
                "Burger",
                "https://thumbs.dreamstime.com/b/perfect-hamburger-classic-burger-american-cheeseburger-isolated-white-reflection-giant-large-massive-thick-extra-toppings-59054909.jpg"
            )
        )
    }
}