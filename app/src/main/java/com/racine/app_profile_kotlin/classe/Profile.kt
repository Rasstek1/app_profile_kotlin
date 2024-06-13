package com.racine.app_profile_kotlin

import android.os.Parcel
import android.os.Parcelable

data class Profile(
    // Déclaration des propriétés de la classe Profile
    val name: String,
    val birthYear: Int,
    val city: String,
    val photoUri: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(birthYear)
        parcel.writeString(city)
        parcel.writeString(photoUri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Profile> {
        override fun createFromParcel(parcel: Parcel): Profile {
            return Profile(parcel)
        }

        override fun newArray(size: Int): Array<Profile?> {
            return arrayOfNulls(size)
        }
    }
}
