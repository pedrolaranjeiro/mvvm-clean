package mock
import uk.co.flat14.data.features.FeatureDataModel

class MockFirebase{

    fun getFeatures(): List<FeatureDataModel>{
        val featureList = ArrayList<FeatureDataModel>()
        featureList.add(FeatureDataModel("1", "FUTEBOL_MATCHES", false ))
        featureList.add(FeatureDataModel("2", "RUGBY", true ))
        featureList.add(FeatureDataModel("3", "WRC_ITALY", true ))
        Thread.sleep(5000)
        return featureList
    }

}