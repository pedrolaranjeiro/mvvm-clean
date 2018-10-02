package flat14.uk.co.domain.usecase.features

import io.reactivex.Single

interface FeaturesRepository{

    fun getFeatureList(): Single<List<FeatureModel>>
}