package com.example.profileapp.domain.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@Parcelize
data class PeopleResult(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("favouriteColor")
    val favouriteColor: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("fromName")
    val fromName: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("jobtitle")
    val jobtitle: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("to")
    val to: String? = null
) : Parcelable {

    fun getDateTimeObj(): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        try {
            return format.parse(createdAt!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }

    }
}