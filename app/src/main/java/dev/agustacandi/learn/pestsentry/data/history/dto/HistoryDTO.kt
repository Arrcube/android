package dev.agustacandi.learn.pestsentry.data.history.dto

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("histories")
	val histories: List<HistoriesItem> = emptyList()
)

data class HistoriesItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("prediction")
	val prediction: String,

	@field:SerializedName("history_id")
	val historyId: String,

	@field:SerializedName("userId")
	val userId: String
)

data class DetailHistoryResponse(
	@field:SerializedName("history")
	val history: HistoriesItem
)

data class BaseHistoryResponse(
	@field:SerializedName("message")
	val message: String

)