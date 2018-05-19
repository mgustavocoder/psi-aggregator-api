package psi.crawler

import crawlercommons.sitemaps.AbstractSiteMap
import crawlercommons.sitemaps.SiteMap
import crawlercommons.sitemaps.SiteMapIndex
import crawlercommons.sitemaps.SiteMapParser
import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import psi.api.Client
import java.net.URL


class Crawler(private val url: String?) {

    private val saxParser = SiteMapParser(false, true)
    private val doc: Document
    private val client: Client = Client()

    init {
        val response = client.request(url)
        doc = Jsoup.parse(response)
    }

    val title: String?
        get() = doc.title()

    val pages: List<String>? get() {
        return parse(URL("$url/sitemap.xml"),null)
                .filter { it -> it.contains(url!!)}
                .distinct()
    }

    /**
     * Parses a Sitemap recursively meaning that if the sitemap is a
     * sitemapIndex then it parses all of the internal sitemaps
     */
    @Throws()
    private fun parse(url: URL, mt: String?): List<String> {
        val content = IOUtils.toByteArray(url)

        val resultList: ArrayList<String> = ArrayList()

        saxParser.isStrictNamespace = false

        val sm: AbstractSiteMap?
        // guesses the mimetype
        if (mt == null || mt == "") {
            sm = saxParser.parseSiteMap(content, url)
        } else {
            sm = saxParser.parseSiteMap(mt, content, url)
        }

        if (sm!!.isIndex) {
            val links = (sm as SiteMapIndex).sitemaps
            for (asm in links) {
                resultList.addAll(parse(asm.url, mt)) // Recursive call
            }
        } else {
            val links = (sm as SiteMap).siteMapUrls
            for (smu in links) {
                resultList.add(smu.url.toString())
            }
        }
        return resultList
    }
}
