package tecx.z3.sub2kotlins.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Z3_Brothers on 11/22/18.
 */
data class ResponseLastNext (
        @field:SerializedName("events")
        val events: List<DataLastNext>? = null
)
