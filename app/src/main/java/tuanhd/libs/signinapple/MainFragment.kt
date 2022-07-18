package tuanhd.libs.signinapple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import tuanhd.libs.signinwithapple.SignInWithAppleService

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btSignIn.setOnClickListener {
            loadFragmentSignIn()
        }
    }

    private fun loadFragmentSignIn() {
        val f = SignInWithAppleFragment.newInstance(getUrl())
        (activity as MainActivity).replaceFragment(f)
    }

    private fun getUrl(): String {
        return SignInWithAppleService.makeUrl(
            "me.moneylover",
            "https://oauth-sandbox.moneylover.me/callback/appleid",
            scope = "name email"
        )
    }
}
//com.aegisrider.communityappTest
//https://frontend.aegisrider.com/dashboard
//https://backend-dev.aegisrider.com/auth/web-apple
//testaegisapple@gmail.com

//"me.moneylover",
//"https://oauth-sandbox.moneylover.me/callback/appleid",
// callbackScheme: apple.signin.moneylover://callback/