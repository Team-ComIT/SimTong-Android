package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ChangeSpotRequest(
    @SerializedName("spot_id")
    val spotId: UUID,
)
