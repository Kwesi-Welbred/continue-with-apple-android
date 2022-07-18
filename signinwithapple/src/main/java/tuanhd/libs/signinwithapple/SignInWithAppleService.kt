package tuanhd.libs.signinwithapple

import android.net.Uri
class SignInWithAppleService {
    companion object{
        private const val BASE_URL = "https://appleid.apple.com/auth/authorize"
        private const val KEY_CLIENT_ID = "client_id"
        private const val KEY_REDIRECT_URI = "redirect_uri"
        private const val KEY_RESPONSE_TYPE = "response_type"
        private const val KEY_SCOPE = "scope"
        private const val KEY_RESPONSE_MODE = "response_mode"
        private const val RESPONSE_TYPE_DEFAULT = "code id_token"
        private const val RESPONSE_MODE_FORM_POST = "form_post"
        private const val SCOPE_EMAIL = "email"
        private const val SCOPE_NAME = "name"
        private const val SCOPE = "name%20email"

        fun makeUrl(
            client_id: String,
            redirect_id: String,
            response_type: String = RESPONSE_TYPE_DEFAULT,
            scope: String = ""
        ): String {

            val uri = Uri.parse(BASE_URL)
                .buildUpon().apply {
                    appendQueryParameter(KEY_CLIENT_ID, client_id)
                    appendQueryParameter(KEY_REDIRECT_URI, redirect_id)
                    appendQueryParameter(KEY_RESPONSE_TYPE, response_type)
                    appendQueryParameter(KEY_SCOPE, scope)
                    if (scope.contains(SCOPE_EMAIL)
                        || scope.contains(SCOPE_NAME)
                    ) {
                        appendQueryParameter(KEY_RESPONSE_MODE, RESPONSE_MODE_FORM_POST)
                    }
                }

            return uri.build().toString()
        }
    }
}