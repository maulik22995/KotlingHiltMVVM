package com.example.myapplication.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class MediaDate(
    val dateString: String?,
    val year: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateString)
        parcel.writeString(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaDate> {
        override fun createFromParcel(parcel: Parcel): MediaDate {
            return MediaDate(parcel)
        }

        override fun newArray(size: Int): Array<MediaDate?> {
            return arrayOfNulls(size)
        }
    }
}