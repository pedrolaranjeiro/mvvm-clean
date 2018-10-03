package uk.co.flat14.data.features

import uk.co.flat14.domain.usecase.features.FeaturesRepository
import io.reactivex.Single
import mock.MockFirebase
import uk.co.flat14.domain.usecase.features.FeatureModel

class FeatureApi: FeaturesRepository {

    override fun getFeatureList(): Single<List<FeatureModel>> {
        return Single.just(MockFirebase().getFeatures()).map {
            features -> features.map(featureMode)
        }
    }

    private val featureMode: (FeatureDataModel) -> FeatureModel = {
        feature -> FeatureModel(feature.id, feature.name, feature.enable)
    }


}