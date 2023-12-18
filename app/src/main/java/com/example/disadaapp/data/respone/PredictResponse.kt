package com.example.disadaapp.data.respone

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("prediction_probabilities")
	val predictionProbabilities: PredictionProbabilities? = null,

	@field:SerializedName("predicted_label")
	val predictedLabel: String? = null
)

data class PredictionProbabilities(

	@field:SerializedName("bayi merasa kurang nyaman")
	val bayiMerasaKurangNyaman: Any? = null,

	@field:SerializedName("bayi sedang lapar")
	val bayiSedangLapar: Any? = null,

	@field:SerializedName("bayi sedang merasa kembung")
	val bayiSedangMerasaKembung: Any? = null,

	@field:SerializedName("bayi sedang lelah")
	val bayiSedangLelah: Any? = null,

	@field:SerializedName("bayi sedang kesakitan")
	val bayiSedangKesakitan: Any? = null
)
