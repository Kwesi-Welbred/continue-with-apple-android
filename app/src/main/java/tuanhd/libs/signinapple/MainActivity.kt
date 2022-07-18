package tuanhd.libs.signinapple

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MainFragment())
    }

    fun replaceFragment(f: Fragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, f)
        ft.commitNowAllowingStateLoss()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("Email","Query perimeter : ${intent?.getStringExtra("Apple Code")}")
        Log.e("MAIN", "uri ::::::::::::::::::::::::::::::::::::::::::::::::::::: ${intent?.data}")
    }
}
