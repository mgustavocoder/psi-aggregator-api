package psi.model

import org.json.JSONObject

class Rule(jsonObject: JSONObject?) {

    val ruleImpact: Int? = jsonObject?.optInt("ruleImpact")
    val localizedRuleName: String? = jsonObject?.optString("localizedRuleName")

}
