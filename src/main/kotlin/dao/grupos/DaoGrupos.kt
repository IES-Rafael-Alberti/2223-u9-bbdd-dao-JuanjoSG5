package dao.grupos

import Grupo
import dao.Dao


interface DaoGrupos: Dao {
    fun getGrupoById(grupoId: Int): Grupo?
    fun getAllGrupos(): List<Grupo>
    fun createGrupo(grupo: Grupo): Int?
    fun updateGrupo(grupo: Grupo): Int?
    fun deleteGrupoByGroupID(grupo: Grupo): Int?
}/*
override fun recalculateBestPositions(): Int? {
    val sql = "SELECT grupoid, MIN(puntuacion) as minPuntuacion " +
            "FROM ctfs " +
            "GROUP BY grupoid"

    try {
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.executeQuery().use { rs ->
                    val mejorPosiciones = mutableMapOf<Int, Int>()
                    while (rs.next()) {
                        val grupoId = rs.getInt("grupoid")
                        val mejorPosicion = rs.getInt("minPuntuacion")
                        mejorPosiciones[grupoId] = mejorPosicion
                    }

                    val updateSql = "UPDATE grupos SET mejorposCTFid = ? WHERE grupoid = ?"
                    conn.prepareStatement(updateSql).use { updateStmt ->
                        for ((grupoId, mejorPosicion) in mejorPosiciones) {
                            updateStmt.setInt(1, mejorPosicion)
                            updateStmt.setInt(2, grupoId)
                            updateStmt.addBatch()
                        }
                        val updateCounts = updateStmt.executeBatch()
                        var totalUpdates = 0
                        for (updateCount in updateCounts) {
                            if (updateCount >= 0) {
                                totalUpdates += updateCount
                            }
                        }
                        return totalUpdates
                    }
                }
            }
        }
    } catch (ex: SQLException) {
        ex.printStackTrace()
        return null
    }
}*/