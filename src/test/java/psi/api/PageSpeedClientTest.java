package psi.api;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import psi.model.Insights;

import static org.junit.Assert.*;

public class PageSpeedClientTest {

    @Test
    @Ignore
    public void getInsights() {
        String response = new PageSpeedClient().getInsights("https://www.havaianas.com","mobile");
        assertNotNull(response);

        Insights insights = new Insights(new JSONObject(response),"https://www.havaianas.com", "mobile");
        assertNotNull(insights);
    }
}