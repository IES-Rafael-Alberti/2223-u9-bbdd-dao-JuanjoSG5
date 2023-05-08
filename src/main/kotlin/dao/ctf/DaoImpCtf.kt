package dao.ctf

import Ctf
import factoriaDatos.DataFactory
import log.LogHandler
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class DaoImpCtf(private val dataSource: DataSource) :DaoCtf {
    override fun createCtf(ctf: Ctf): Int? {
        val sql = "INSERT INTO CTFS (CTFid,grupoid,puntuacion) " +
                "VALUES (?, ?, ?)"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, ctf.id)
                    stmt.setInt(2, ctf.grupoId)
                    stmt.setInt(3, ctf.puntuacion)

                    return stmt.executeUpdate()
                }
            }
        }catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun createTable() {
        var tableName = "CTFS"
        val sql =
            "CREATE TABLE IF NOT EXISTS CTFS (\n" +
                    "    CTFid INT NOT NULL,\n" +
                    "    grupoid INT NOT NULL,\n" +
                    "    puntuacion INT NOT NULL,\n" +
                    "    PRIMARY KEY (CTFid,grupoid)\n" +
                    ");"
        try {
            LogHandler.info("inicindo")
            dataSource.connection.use { conn ->
                LogHandler.info("inicindo conneion")
                val metaData = conn.metaData
                val resultSet = metaData.getTables(null, null, tableName.uppercase(Locale.getDefault()), null)
                if (!resultSet.next()) {
                    conn.prepareStatement(sql).use { stmt ->
                        LogHandler.info("inicindo ejecucion")
                        stmt.executeUpdate()
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }





    override fun deleteTable() {
        val sql ="DROP TABLE CTFS;"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
}