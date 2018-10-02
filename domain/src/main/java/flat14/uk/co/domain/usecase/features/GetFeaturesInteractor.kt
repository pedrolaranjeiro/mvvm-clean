package flat14.uk.co.domain.usecase.features

import io.reactivex.Single

class GetFeaturesInteractor(val repository: FeaturesRepository) : GetFeaturesUseCase {

    override fun get(): Single<List<FeatureModel>> {
        return repository.getFeatureList().map { features ->
            features.map(enableAllFeatures)
        }
    }

    // add some logic
    private val enableAllFeatures: (FeatureModel) -> FeatureModel = { feature ->
            var f = FeatureModel(feature.id, feature.name, true)
            if (f.id.toInt() < 2) {
                f.id = "AppId-${feature.id}"
            }
             f
    }

}