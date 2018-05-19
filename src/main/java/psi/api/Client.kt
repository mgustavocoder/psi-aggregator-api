package psi.api

import org.apache.http.HttpStatus
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class Client {

    @Throws
    fun isStatus200(url: String?): Boolean {
        val httpResponse = closeableHttpResponse(url)
        return httpResponse?.statusLine?.statusCode == HttpStatus.SC_OK
    }

    @Throws
    fun request(url: String?): String? {
        val httpResponse = closeableHttpResponse(url)
        return EntityUtils.toString(httpResponse?.entity, "UTF-8")
    }

    @Throws
    private fun closeableHttpResponse(requestUrl: String?): CloseableHttpResponse? {
        val httpClient = HttpClientBuilder.create().build()
        val request = HttpGet(requestUrl)
        return httpClient.execute(request)
    }
}
