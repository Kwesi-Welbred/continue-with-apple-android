package tuanhd.libs.signinapple.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import tuanhd.libs.signinapple.data.util.Constants
import tuanhd.libs.signinapple.model.User

interface HttpRequest {

    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST(Constants.appleSignIn)
    fun appleCode(@Body user_key: Any): Call<User>
}