package tecx.z3.sub2kotlins.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Z3_Brothers on 11/23/18.
 */
data class DataLastNext(
        @field:SerializedName("idEvent")
        val idEvent: String? = null,

        @field:SerializedName("idHomeTeam")
        val idHomeTeam: String? = null,

        @field:SerializedName("idAwayTeam")
        val idAwayTeam: String? = null,

        @field:SerializedName("intHomeShots")
        val intHomeShots: String? = null,

        @field:SerializedName("intAwayShots")
        val intAwayShots: String? = null,

        @field:SerializedName("intHomeScore")
        val intHomeScore: String? = null,

        @field:SerializedName("intAwayScore")
        val intAwayScore: String? = null,

        @field:SerializedName("strHomeTeam")
        val strHomeTeam: String? = null,

        @field:SerializedName("strAwayTeam")
        val strAwayTeam: String? = null,

        @field:SerializedName("strHomeYellowCards")
        val strHomeYellowCards: String? = null,

        @field:SerializedName("strAwayYellowCards")
        val strAwayYellowCards: String? = null,

        @field:SerializedName("strHomeRedCards")
        val strHomeRedCards: String? = null,

        @field:SerializedName("strAwayRedCards")
        val strAwayRedCards: String? = null,

        @field:SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: String? = null,

        @field:SerializedName("strAwayGoalDetails")
        val strAwayGoalDetails: String? = null,

        @field:SerializedName("strTime")
        val strTime: String? = null,

        @field:SerializedName("strFilename")
        val strFilename: String? = null,

        @field:SerializedName("dateEvent")
        val dateEvent: String? = null,

        @field:SerializedName("strHomeLineupGoalkeeper")
        val strHomeLineupGoalkeeper: String? = null,

        @field:SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: String? = null,

        @field:SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: String? = null,

        @field:SerializedName("strHomeLineupForward")
        val strHomeLineupForward: String? = null,

        @field:SerializedName("strAwayLineupGoalkeeper")
        val strAwayLineupGoalkeeper: String? = null,

        @field:SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: String? = null,

        @field:SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: String? = null,

        @field:SerializedName("strAwayLineupForward")
        val strAwayLineupForward: String? = null,

        @field:SerializedName("strHomeLineupSubstitutes")
        val strHomeLineupSubstitutes: String? = null,

        @field:SerializedName("strAwayLineupSubstitutes")
        val strAwayLineupSubstitutes: String? = null


)