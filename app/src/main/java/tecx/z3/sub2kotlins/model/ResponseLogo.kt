package tecx.z3.sub2kotlins.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Z3_Brothers on 11/23/18.
 */
data class ResponseLogo (
        @field:SerializedName("teams")
        val teams: List<TeamsItem?>? = null
)