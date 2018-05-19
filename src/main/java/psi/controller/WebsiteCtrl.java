package psi.controller;

import com.google.gson.Gson;
import psi.dto.Response;
import psi.service.WebsiteService;
import static spark.Spark.get;

public class WebsiteCtrl {

    private WebsiteService websiteService = new WebsiteService();

    public WebsiteCtrl(){
        Gson gson = new Gson();

        get("/api/v1/website/:strategy/:url", (req, res) -> {
            String url = req.params("url");
            String strategy = req.params("strategy");
            return new Response(200, websiteService.getWebsite(url, strategy));
        }, gson::toJson);
    }

}
