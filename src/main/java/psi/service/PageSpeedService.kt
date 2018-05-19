package psi.service

import psi.crawler.Crawler

class PageSpeedService {

    @Throws
    fun updateBatchPSIResult(url: String): String {
        val crawler = Crawler(url)
        if(crawler.pages!!.isNotEmpty()){
            PageSpeedThread(crawler).start()
            return "Analysis started: " + crawler.pages?.size + " pages was found on $url/sitemap.xml"
        }
        return "$url/sitemap.xml not found. Analysis not started."
    }

}
