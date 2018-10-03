package uk.co.flat14.domain.usecase.features

import io.reactivex.Single
import uk.co.flat14.domain.usecase.features.FeatureModel

interface FeaturesRepository{

    fun getFeatureList(): Single<List<FeatureModel>>
}