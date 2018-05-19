package psi.dto

import com.google.gson.Gson

class Response(private val status: Int?, private val data: Any) {

    fun toJson(): String {
        return Gson().toJson(Response(status, data), Response::class.java)
    }
}