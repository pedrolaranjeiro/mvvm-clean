package uk.co.flat14.domain.usecase.features

import io.reactivex.Single

interface GetFeaturesUseCase{

    fun get(): Single<List<FeatureModel>>

}