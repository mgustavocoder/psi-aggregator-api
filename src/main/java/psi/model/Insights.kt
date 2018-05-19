package psi.model

import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import org.json.JSONObject
import java.util.regex.Pattern
import com.mongodb.util.JSON



class Insights {

    /**
     * If the original URL redirects to other page, Page Speed will analyze the final page.
     * If a redirect occurs, the url and evaluatedUrl will be different.
     */
    var evaluatedUrl: String? = null
    var url: String? = null
    var strategy: String? = null
    var responseCode: String? = null
    var title: String? = null
    var score: Int? = null
    var loadingExperience: String? = null
    var pageStats: PageStats? = null
    var ruleResults: HashMap<String, Rule> = HashMap()

    constructor(jsonObject: JSONObject?, url: String?, strategy: String?) {
        this.url = url
        this.strategy = strategy
        evaluatedUrl = jsonObject?.optString("id")
        responseCode = jsonObject?.optString("responseCode")
        title = jsonObject?.optString("title")
        score = jsonObject?.optJSONObject("ruleGroups")?.optJSONObject("SPEED")?.optInt("score")
        loadingExperience = jsonObject?.optJSONObject("loadingExperience")?.optString("overall_category")
        pageStats = PageStats(jsonObject?.optJSONObject("pageStats"))
        val ruleResultsJson = jsonObject?.optJSONObject("formattedResults")?.optJSONObject("ruleResults")
        ruleResults.put("AvoidLandingPageRedirects", Rule(ruleResultsJson?.optJSONObject("AvoidLandingPageRedirects")))
        ruleResults.put("EnableGzipCompression", Rule(ruleResultsJson?.optJSONObject("EnableGzipCompression")))
        ruleResults.put("LeverageBrowserCaching", Rule(ruleResultsJson?.optJSONObject("LeverageBrowserCaching")))
        ruleResults.put("MinifyHTML", Rule(ruleResultsJson?.optJSONObject("MinifyHTML")))
        ruleResults.put("MinifyJavaScript", Rule(ruleResultsJson?.optJSONObject("MinifyJavaScript")))
        ruleResults.put("MinifyCss", Rule(ruleResultsJson?.optJSONObject("MinifyCss")))
        ruleResults.put("PrioritizeVisibleContent", Rule(ruleResultsJson?.optJSONObject("PrioritizeVisibleContent")))
        ruleResults.put("MainResourceServerResponseTime", Rule(ruleResultsJson?.optJSONObject("MainResourceServerResponseTime")))
        ruleResults.put("OptimizeImages", Rule(ruleResultsJson?.optJSONObject("OptimizeImages")))
        ruleResults.put("MinimizeRenderBlockingResources", Rule(ruleResultsJson?.optJSONObject("MinimizeRenderBlockingResources")))
    }

    constructor(dbObject: DBObject?) {
        url = dbObject?.get("url") as String?
        evaluatedUrl = dbObject?.get("evaluatedUrl") as String?
        responseCode = dbObject?.get("responseCode") as String?
        strategy = dbObject?.get("strategy") as String?
        title = dbObject?.get("title") as String?
        score = dbObject?.get("score") as Int?
        loadingExperience = dbObject?.get("loadingExperience") as String?
        pageStats = PageStats(JSONObject(dbObject?.get("pageStats").toString()))
        ruleResults = dbObject?.get("ruleResults") as HashMap<String, Rule>
    }

    companion object {
        fun adaptToDbObject(insights: Insights): DBObject {
            return BasicDBObject("url", insights.url)
                    .append("loadingExperience", insights.loadingExperience)
                    .append("evaluatedUrl", insights.evaluatedUrl)
                    .append("score", insights.score)
                    .append("title", insights.title)
                    .append("strategy", insights.strategy)
                    .append("responseCode", insights.responseCode)
                    .append("pageStats", PageStats.adaptToDbObject(insights.pageStats!!))
                    .append("ruleResults", JSON.parse(JSONObject(insights.ruleResults).toString()) as DBObject)
        }

        fun adaptToDbObject(url: String, strategy: String): DBObject? {
            return when(strategy) {
                "mobile" -> BasicDBObject("url", Pattern.compile(url)).append("strategy", "mobile")
                "desktop" -> BasicDBObject("url", Pattern.compile(url)).append("strategy", "desktop")
                "both" -> BasicDBObject("url", Pattern.compile(url))
                else -> null
            }
        }
    }
}