package com.example.usecase.usecase.allMedia

import com.example.usecase.enums.AllMediaType
import javax.inject.Inject

class CheckIfMediaIsSeriesUseCase @Inject constructor() {
    operator fun invoke(type: AllMediaType): Boolean {
        return (
            type == AllMediaType.ON_THE_AIR ||
                type == AllMediaType.POPULAR ||
                type == AllMediaType.AIRING_TODAY ||
                type == AllMediaType.TOP_RATED ||
                type == AllMediaType.LATEST
            )
    }
}
