package dev.rhcehd123.core.network.retrofit

import dev.rhcehd123.core.model.GachaItem
import dev.rhcehd123.core.network.GachaSaverNetworkDataSource
import dev.rhcehd123.core.network.model.RequestGachaBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitGachaSaverNetworkApi {
    @POST("/request")
    suspend fun requestGacha(@Body requestGacha: RequestGachaBody): NetworkResponse<List<GachaItem>>
}

private const val GACHA_SAVER_BASE_URL = "http://192.168.0.16:3000"
private const val API_KEY = "RK02ZNvbjtdkJLlhdwqvh7o0Laq14LRrJWPwFjNpALU="
private const val GACHA_ID = 1

private data class NetworkResponse<T>(
    val data: T,
    val msg: String
)

@Singleton
internal class RetrofitGachaSaverNetwork @Inject constructor(): GachaSaverNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(GACHA_SAVER_BASE_URL)

        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitGachaSaverNetworkApi::class.java)

    override suspend fun requestGacha(id: String, count: Int): List<GachaItem>
        = networkApi.requestGacha(RequestGachaBody(API_KEY, GACHA_ID, id, count)).data
}