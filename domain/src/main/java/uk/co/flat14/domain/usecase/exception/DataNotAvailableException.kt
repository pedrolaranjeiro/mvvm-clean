package uk.co.flat14.domain.usecase.exception

import java.lang.Exception

class DataNotAvailableException(message:String = "Data not available at the moment"): Exception(message)