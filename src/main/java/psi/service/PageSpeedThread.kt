package psi.service

import org.json.JSONObject
import psi.api.PageSpeedClient
import psi.crawler.Crawler
import psi.dao.InsightsDAO
import psi.model.Insights
import psi.model.Website

class PageSpeedThread(private val crawler: Crawler?) : Thread() {

    private val pageSpeedClient: PageSpeedClient = PageSpeedClient()
    private val insightsDAO: InsightsDAO = InsightsDAO()

    override fun run() {
        var response: String?
        var insights: Insights

        crawler?.pages?.parallelStream()?.forEach({
            response = pageSpeedClient.getInsights(it, "mobile")
            insights = Insights(JSONObject(response), it, "mobile")
            insightsDAO.create(insights)
            response = pageSpeedClient.getInsights(it, "desktop")
            insights = Insights(JSONObject(response), it, "desktop")
            insightsDAO.create(insights)
        })

    }
}
