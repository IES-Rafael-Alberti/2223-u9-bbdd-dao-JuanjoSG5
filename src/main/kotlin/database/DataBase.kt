package database

import java.io.File
import java.sql.DriverManager
import java.sql.*
import log.LogHandler
/*
class DataBase {
    init {
        createDatabase()
    }

    private fun createDatabase() {
        // JDBC driver name and database URL
        val JDBC_DRIVER = "org.h2.Driver"
        val DB_URL = "jdbc:h2:./mydatabase"

        // Database credentials
        val USER = "sa"
        val PASS = ""

        // Open a connection to the database
        val conn = DriverManager.getConnection(DB_URL, USER, PASS)

        // Execute SQL commands to create the database and tables
        conn.createStatement().apply {
            execute("CREATE SCHEMA IF NOT EXISTS mydatabase")
            execute("CREATE TABLE IF NOT EXISTS mydatabase.users (id INTEGER PRIMARY KEY, name VARCHAR(255))")
            close()
        }
        println("Database created")
        conn.close()
    }
}
*/


class Database {

    private val url = "jdbc:h2:./mydatabase"
    private val driver = "org.h2.Driver"

    private fun getConnection(): Connection {
        return DriverManager.getConnection(url)
    }

    init {
        // Check if the database exists
        val test = File("./mydatabase.mv.db")
        val databaseExists = test.exists()
        LogHandler.info("Database exists: $databaseExists")
        // If the database doesn't exist it creates it
        if (!databaseExists) {
            LogHandler.info("Database not found")
            Class.forName(driver)
            val conn = getConnection()
            LogHandler.info("Database created ")
            conn.close()
        }
    }
}

fun main(){
    LogHandler.info("Inicializing Database")
    Database()
    LogHandler.info("Database Initialised")
}
