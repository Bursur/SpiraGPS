package com.bursur.spiragps.utils

actual suspend fun loadJsonFile(url: String): String {
    val result = ApiClient.apiService.getJson(url).execute()

    return if(result.isSuccessful)
        result.body().orEmpty()
    else
        ""
}