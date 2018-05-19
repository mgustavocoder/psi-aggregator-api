package psi.crawler

import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

class CrawlerTest {

    @Test
    @Ignore
    fun getPages() {
        val pages: List<String>? = Crawler("https://www.havaianas.com.br").pages
        assertNotNull(pages)
    }
}