package spammer

import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object SSLStripper {
    private val trustAllCerts = arrayOf<TrustManager>(
        object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return null
            }

            override fun checkClientTrusted(
                certs: Array<X509Certificate>, authType: String
            ) {
            }

            override fun checkServerTrusted(
                certs: Array<X509Certificate>, authType: String
            ) {
            }
        }
    )

    public fun createClient(): CloseableHttpClient {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, this.trustAllCerts, SecureRandom())

        val httpClient = HttpClientBuilder.create().setSSLContext(sslContext).build()
        return httpClient
    }
}