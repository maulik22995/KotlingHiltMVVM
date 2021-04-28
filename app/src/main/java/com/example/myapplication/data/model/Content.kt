package com.example.myapplication.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val mediaDate: MediaDate?,
    val mediaId: Int,
    val mediaTitleCustom: String?,
    val mediaType: String?,
    val mediaUrl: String?,
    val mediaUrlBig: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(MediaDate::class.java.classLoader),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(mediaDate, flags)
        parcel.writeInt(mediaId)
        parcel.writeString(mediaTitleCustom)
        parcel.writeString(mediaType)
        parcel.writeString(mediaUrl)
        parcel.writeString(mediaUrlBig)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Content> {
        override fun createFromParcel(parcel: Parcel): Content {
            return Content(parcel)
        }

        override fun newArray(size: Int): Array<Content?> {
            return arrayOfNulls(size)
        }
    }

}