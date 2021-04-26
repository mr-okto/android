package com.halfkon.recipe.ingredient.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.halfkon.recipe.ingredient.network.IngredientApiRepo;
import com.halfkon.recipe.ingredient.network.IngredientApiRepoImpl;
import com.halfkon.recipe.ingredient.network.IngredientApiResponse;

public class IngredientViewModel extends ViewModel {
    private MediatorLiveData<IngredientApiResponse> mIngredientApiResponse;
    private IngredientApiRepo mIngredientApiRepo;

    public IngredientViewModel() {
        mIngredientApiResponse = new MediatorLiveData<>();
        mIngredientApiRepo = new IngredientApiRepoImpl();
    }

    @NonNull
    public LiveData<IngredientApiResponse> getApiResponse() {
        return mIngredientApiResponse;
    }

    public LiveData<IngredientApiResponse> getIngredients(@NonNull String query) {
        mIngredientApiResponse.addSource(
                mIngredientApiRepo.getIngredients(query),
                apiResponse -> mIngredientApiResponse.setValue(apiResponse)
        );
        return mIngredientApiResponse;
    }
}
