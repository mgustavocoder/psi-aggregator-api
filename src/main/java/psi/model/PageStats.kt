package psi.model

import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import org.json.JSONObject

class PageStats(jsonObject: JSONObject?,
                val numberResources: Int? = jsonObject?.optInt("numberResources"),
                val numberHosts: Int? = jsonObject?.optInt("numberHosts"),
                val totalRequestBytes: String? = jsonObject?.optString("totalRequestBytes"),
                val numberStaticResources: Int? = jsonObject?.optInt("numberStaticResources"),
                val htmlResponseBytes: String? = jsonObject?.optString("htmlResponseBytes"),
                val textResponseBytes: String? = jsonObject?.optString("textResponseBytes"),
                val overTheWireResponseBytes: String? = jsonObject?.optString("overTheWireResponseBytes"),
                val cssResponseBytes: String? = jsonObject?.optString("cssResponseBytes"),
                val imageResponseBytes: String? = jsonObject?.optString("imageResponseBytes"),
                val javascriptResponseBytes: String? = jsonObject?.optString("javascriptResponseBytes"),
                val otherResponseBytes: String? = jsonObject?.optString("otherResponseBytes"),
                val numberJsResources: Int? = jsonObject?.optInt("numberJsResources"),
                val numberCssResources: Int? = jsonObject?.optInt("numberCssResources"),
                val numTotalRoundTrips: Int? = jsonObject?.optInt("numTotalRoundTrips"),
                val numRenderBlockingRoundTrips: Int? = jsonObject?.optInt("numRenderBlockingRoundTrips")) {

    companion object {
        fun adaptToDbObject(pageStats: PageStats): DBObject {
            return BasicDBObject("numberResources", pageStats.numberResources)
                    .append("numberHosts", pageStats.numberHosts)
                    .append("totalRequestBytes", pageStats.totalRequestBytes)
                    .append("numberStaticResources", pageStats.numberStaticResources)
                    .append("htmlResponseBytes", pageStats.htmlResponseBytes)
                    .append("textResponseBytes", pageStats.textResponseBytes)
                    .append("overTheWireResponseBytes", pageStats.overTheWireResponseBytes)
                    .append("cssResponseBytes", pageStats.cssResponseBytes)
                    .append("imageResponseBytes", pageStats.imageResponseBytes)
                    .append("textResponseBytes", pageStats.textResponseBytes)
                    .append("javascriptResponseBytes", pageStats.javascriptResponseBytes)
                    .append("otherResponseBytes", pageStats.otherResponseBytes)
                    .append("numberJsResources", pageStats.numberJsResources)
                    .append("numberCssResources", pageStats.numberCssResources)
                    .append("numTotalRoundTrips", pageStats.numTotalRoundTrips)
                    .append("numRenderBlockingRoundTrips", pageStats.numRenderBlockingRoundTrips)
        }
    }
}
