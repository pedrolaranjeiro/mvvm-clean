package flat14.uk.co.domain.usecase.features

import io.reactivex.Single

interface GetFeaturesUseCase{

    fun get(): Single<List<FeatureModel>>

}