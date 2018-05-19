package psi.controller;

import com.google.gson.Gson;
import psi.dto.Response;
import psi.service.PageSpeedService;
import static spark.Spark.post;

public class PageSpeedCtrl {

    private PageSpeedService pageSpeedService = new PageSpeedService();

    public PageSpeedCtrl() {
        Gson gson = new Gson();

        //curl -X POST http://localhost:8080/api/v1/psi/batch/https%3A%2F%2Fwww.drinksmartwater.com
        post("/api/v1/psi/batch/:url", (req, res) -> {
            String url = req.params("url");
            return new Response(200, pageSpeedService.updateBatchPSIResult(url));
        }, gson::toJson);

    }

}
