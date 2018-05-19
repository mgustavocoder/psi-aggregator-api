package psi.dao

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

class DBConnector private constructor() {
    val mongoClient: MongoClient? = MongoClient(MongoClientURI(clientUri))

    companion object {
        private var dbConnector: DBConnector? = null
        private const val clientUri: String = ""

        val instance: DBConnector
            get() {
                if (dbConnector == null)
                    dbConnector = DBConnector()
                return dbConnector as DBConnector
            }
    }
}
