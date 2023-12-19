package com.example.disadaapp.data.respone

import com.google.gson.annotations.SerializedName

data class PredictsResponse(

	@field:SerializedName("kemungkinan")
	val kemungkinan: Kemungkinan? = null,

	@field:SerializedName("rekomendasi_panganan")
	val rekomendasiPanganan: RekomendasiPanganan? = null,

	@field:SerializedName("Hasil")
	val hasil: String? = null
)

data class Kemungkinan(

	@field:SerializedName("merasa kesakitan")
	val merasaKesakitan: Any? = null,

	@field:SerializedName("sedang lapar")
	val sedangLapar: Any? = null,

	@field:SerializedName("sedang lelah")
	val sedangLelah: Any? = null,

	@field:SerializedName("sedang merasa kembung")
	val sedangMerasaKembung: Any? = null,

	@field:SerializedName("merasa kurang nyaman")
	val merasaKurangNyaman: Any? = null
)

data class RekomendasiPanganan(

	@field:SerializedName("penjelasan")
	val penjelasan: String? = null,

	@field:SerializedName("rekomendasi")
	val rekomendasi: List<String?>? = null
)
