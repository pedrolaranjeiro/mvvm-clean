package flat14.uk.co.domain.usecase.features

import io.reactivex.Single

class GetFeaturesInteractor(val repository: FeaturesRepository) : GetFeaturesUseCase {

    override fun get(): Single<List<FeatureModel>> {
        return repository.getFeatureList()
    }

}