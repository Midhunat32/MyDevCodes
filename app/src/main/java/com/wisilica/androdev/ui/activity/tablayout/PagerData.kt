package com.wisilica.androdev.ui.activity.tablayout

import android.os.Parcel
import android.os.Parcelable

data class PagerData(val title:String,val image:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PagerData> {
        override fun createFromParcel(parcel: Parcel): PagerData {
            return PagerData(parcel)
        }

        override fun newArray(size: Int): Array<PagerData?> {
            return arrayOfNulls(size)
        }
    }
}