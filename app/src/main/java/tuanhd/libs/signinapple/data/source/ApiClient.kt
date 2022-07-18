package tuanhd.libs.signinapple.data.source

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tuanhd.libs.signinapple.data.util.Constants
import java.util.concurrent.TimeUnit

interface ApiClient {

    companion object {
        //interceptor
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        //okClient
        private val client: OkHttpClient = OkHttpClient.Builder().apply {
//           this.addInterceptor(TokenInterceptor())
//           this.addInterceptor(NetworkManager())
            this.addInterceptor(interceptor)
            this.connectTimeout(1, TimeUnit.MINUTES)
            this.writeTimeout(1, TimeUnit.MINUTES)
            this.readTimeout(1, TimeUnit.MINUTES)
        }.build()

        //retrofit
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.devBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}