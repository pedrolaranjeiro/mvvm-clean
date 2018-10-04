package uk.co.flat14.domain.usecase.exception

import java.lang.Exception
import java.lang.RuntimeException

class DataNotAvailableException(message:String = "Data not available at the moment"): RuntimeException(message)