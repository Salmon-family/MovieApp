package com.devfalah.usecases.allMedia

import com.devfalah.types.AllMediaType
import javax.inject.Inject

class CheckIfMediaIsSeriesUseCase @Inject constructor() {
    operator fun invoke(type: String): Boolean {
        return (type == AllMediaType.ON_THE_AIR.name
                || type == AllMediaType.POPULAR.name
                || type == AllMediaType.AIRING_TODAY.name
                || type == AllMediaType.TOP_RATED.name
                || type == AllMediaType.LATEST.name
                )
    }
}