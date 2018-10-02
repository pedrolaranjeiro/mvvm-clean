package mock
import uk.co.flat14.data.features.FeatureJSON

class MockFirebase{

    fun getFeatures(): List<FeatureJSON>{
        val featureList = ArrayList<FeatureJSON>()
        featureList.add(FeatureJSON("1", "FUTEBOL_MATCHES", false ))
        featureList.add(FeatureJSON("2", "RUGBY", true ))
        featureList.add(FeatureJSON("3", "WRC_ITALY", true ))
        Thread.sleep(5000)
        return featureList
    }

}