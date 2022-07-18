package tuanhd.libs.signinapple

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tuanhd.libs.signinapple.data.network.HttpRequest
import tuanhd.libs.signinapple.data.source.ApiClient
import tuanhd.libs.signinapple.model.CallbackResponse
import tuanhd.libs.signinapple.model.User
import tuanhd.libs.signinwithapple.SignInAppleWebView

class SignInWithAppleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val callback = CallbackResponse()
        val callbackResponse: MutableLiveData<User?> = MutableLiveData()

        val webView = SignInAppleWebView(context, "apple.signin.moneylover://callback/") {
            Log.d("RESPONSE",":::::::::::::::::::::::::::::::::::::::::::::::::$it")
            val data = JSONObject(it)
            callback.user_key = data.get("code").toString()
            callback.id_token = data.get("id_token").toString()

            //  callback.user_key = data.keys().toString()

            Log.e(
                "SignInWithAppleFragment",
                ":::::::::::::::::::::::::::::::::::::::::callback: $it"
            )
            Log.e(
                "SignInWithAppleFragment",
                ":::::::::::::::::::::::::::::::::::::::::id token: ${callback.id_token}"
            )
            Log.e(
                "SignInWithAppleFragment",
                ":::::::::::::::::::::::::::::::::::::::::user_key: ${callback.user_key}"
            )


            val retrofit = ApiClient.getRetrofitInstance().create(HttpRequest::class.java)
            val body = User(code = callback.user_key.toString())
            val call = retrofit.appleCode(body)
            call.enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        callbackResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.i("ERR", ":::::::::::::ERROR")
                }
            })
        }
        //webView.loadUrl()
        webView.loadUrl(arguments?.getString(KEY_URL) ?: "nothing")

        return webView
    }

    companion object {
        private const val KEY_URL = "KEY_URL"
        fun newInstance(url: String): Fragment {
            val uri = Uri.parse(url)
            val f = SignInWithAppleFragment()
            val args = Bundle().apply {
                putString(KEY_URL, url)
            }
            f.arguments = args
            return f
        }
    }

    //https://oauth-sandbox.moneylover.me/callback/appleid?

}