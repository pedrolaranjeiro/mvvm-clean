package uk.co.flat14.data.features

import flat14.uk.co.domain.usecase.features.FeatureModel
import flat14.uk.co.domain.usecase.features.FeaturesRepository
import io.reactivex.Single
import mock.MockFirebase

class FeatureApi: FeaturesRepository{

    override fun getFeatureList(): Single<List<FeatureModel>> {
        return Single.just(MockFirebase().getFeatures()).map {
            features -> features.map(featureMode)
        }
    }

    private val featureMode: (FeatureDataModel) -> FeatureModel = {
        feature -> FeatureModel(feature.id, feature.name, feature.enable)
    }


}