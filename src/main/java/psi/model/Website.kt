package psi.model

import psi.crawler.Crawler

class Website() {
    var title: String? = null
    var pages: List<Insights>? = null

    constructor(crawler: Crawler?, pages: List<Insights>?) : this() {
        title = crawler?.title
        this.pages = pages
    }

    constructor(crawler: Crawler?) : this() {
        title = crawler?.title
    }
}
