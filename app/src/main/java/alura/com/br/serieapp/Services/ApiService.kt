package alura.com.br.serieapp.Services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "4cc2822d765baa01bddd3620618e1be1"

class ApiService {

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(Intercept)
        .build()

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val seriesService: API by lazy {
        retrofit.create(API::class.java)
    }

    object Intercept : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val url = originalRequest
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val request = originalRequest
                .newBuilder()
                .url(url)
                .build()
            return chain.proceed(request)
        }
    }
}