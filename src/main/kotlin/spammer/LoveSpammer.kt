package spammer

import com.google.gson.Gson
import models.KelasModel
import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


/*
https://api-edunex.cognisia.id/course/presences?filter[course_id][is]=39702&filter[class_id][is]=35016&sort=start_at&page[limit]=35&page[offset]=0
https://api-edunex.cognisia.id/course/presences/list
 */
class LoveSpammer {
    private val gson = Gson()
    fun getClassesList(bearer: String): Array<KelasModel> {
        val client = SSLStripper.createClient()
        val request = HttpGet("https://api-edunex.cognisia.id/course/presences?filter[course_id][is]=39702&filter[class_id][is]=35016&sort=start_at&page[limit]=35&page[offset]=0")
        request.addHeader("Authorization", "Bearer $bearer")

        val config = RequestConfig.custom()
            .setProxy(HttpHost("49.0.2.242", 8090))
            .build()
        request.setConfig(config)
        val response = client.execute(request)
        val entity = response.entity
        val responseBody = EntityUtils.toString(entity)
        println(responseBody)
        val classes = gson.fromJson(responseBody, Array<KelasModel>::class.java)
        return classes
    }

    fun getLectureList() {

    }

    fun openCourse() {

    }
}