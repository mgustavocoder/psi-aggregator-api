package psi.api

import java.net.URLEncoder

class PageSpeedClient {

    private val client = Client()

    fun getInsights(url: String?, strategy: String?): String? {
        return client.request("$PSI_ENDPOINT?url=${URLEncoder.encode(url, "UTF-8")}&strategy=$strategy&key=$API_KEY")
    }

    companion object {
        const val PSI_ENDPOINT = "https://www.googleapis.com/pagespeedonline/v4/runPagespeed"
        const val API_KEY = ""
    }
}
