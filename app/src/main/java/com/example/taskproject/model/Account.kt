package com.example.taskproject.model

import com.google.gson.annotations.SerializedName

data class Account( val data:AccountData = AccountData())

data class AccountData(val data :ArrayList<Data> = ArrayList())

data class Data(val url: String, val photographer: Photographer = Photographer(), @SerializedName("business_purchases")val businessPurchase: ArrayList<Business> = ArrayList(), @SerializedName("light_box") val lightBox:ArrayList<LightBox> = ArrayList())

data class Photographer(@SerializedName ("user_name") val username: String = "",
                        @SerializedName ("full_name") val fullName: String = "",
                        @SerializedName ("image_profile")  val imageProfile: String = "")

data class Business(@SerializedName("followers_count")  val followersCounts:Int = 0,
                            @SerializedName("followings_count")  val followingsCounts: Int = 0)

data class LightBox(@SerializedName("photos_count")  val photosCount:Int = 0)
