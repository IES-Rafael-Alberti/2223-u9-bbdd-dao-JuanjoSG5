package dao.grupos

import Grupo
import log.LogHandler
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class DaoGruposImpl(private val dataSource: DataSource) :DaoGrupos {

    override fun createGrupo(grupo: Grupo): Int? {
        val sql = "INSERT INTO GRUPOS (grupoid,GRUPODESC, mejorposCTFid) " +
                "VALUES (?, ?,?)"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, grupo.grupoid)
                    stmt.setInt(2, grupo.mejorCtfId)

                    return stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun updateGrupo(grupo: Grupo): Int? {
        val sql = "UPDATE GRUPOS SET  mejorposCTFid = ?, GRUPODESC = ? WHERE grupoid = ?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, grupo.grupoid)
                    stmt.setInt(2, grupo.mejorCtfId)
                    return stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun deleteGrupoByGroupID(grupo: Grupo): Int? {
        val sql = "DELETE FROM GRUPOS WHERE GRUPOID = ?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, grupo.grupoid)

                    return stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun createTable() {
        val tableName = "Grupos"
        val sql =
            "CREATE TABLE IF NOT EXISTS $tableName (\n" +
                    "    grupoid INT NOT NULL AUTO_INCREMENT,\n" +
                    "    grupodesc VARCHAR(100) NOT NULL,\n" +
                    "    mejorposCTFid INT,\n" +
                    "    PRIMARY KEY (grupoid)\n" +
                    ");"
        try {
            LogHandler.info("iniciando")
            dataSource.connection.use { conn ->
                LogHandler.info("iniciando conexión")
                val metaData = conn.metaData
                val resultSet = metaData.getTables(null, null, tableName.uppercase(Locale.getDefault()), null)
                if (!resultSet.next()) {
                    conn.prepareStatement(sql).use { stmt ->
                        LogHandler.info("iniciando ejecución")
                        stmt.executeUpdate()
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }

    override fun deleteTable() {
        val sql = "DROP TABLE Grupos;"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.executeUpdate()
                }
            }
        } catch (ex: SQLException) {
            with(ex) { printStackTrace() }
        }
    }
    override fun getAllGrupos(): List<Grupo> {
        val sql = "SELECT grupoid, grupodesc, mejorposCTFid FROM GRUPOS"

        val grupos = mutableListOf<Grupo>()

        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()

                    while (rs.next()) {
                        val grupoId = rs.getInt("grupoid")
                        val mejorPosCtfId = rs.getInt("mejorposCTFid")

                        grupos.add(Grupo(grupoId, mejorPosCtfId))
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }

        return grupos
    }
    override fun getGrupoById(grupoId: Int): Grupo? {
        val sql = "SELECT grupoid, grupodesc, mejorposCTFid FROM GRUPOS WHERE grupoid = ?"
        var grupo: Grupo? = null

        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, grupoId)

                    val rs = stmt.executeQuery()

                    if (rs.next()) {
                        val grupoDesc = rs.getString("grupodesc")
                        val mejorPosCtfId = rs.getInt("mejorposCTFid")

                        grupo = Grupo(grupoId, mejorPosCtfId)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }

        return grupo
    }
}
