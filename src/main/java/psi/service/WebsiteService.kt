package psi.service

import psi.crawler.Crawler
import psi.dao.InsightsDAO
import psi.model.Insights
import psi.model.Website

class WebsiteService {

    fun getWebsite(url: String, strategy: String): Website {
        val crawler = Crawler(url)

        return Website(crawler, getWebsiteInsights(url, strategy))
    }

    private fun getWebsiteInsights(url: String, strategy: String): List<Insights> {
        return InsightsDAO().read(url, strategy)
    }

}
