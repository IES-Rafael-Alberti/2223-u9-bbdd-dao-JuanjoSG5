import programFunctionality.argsFilter.ArgsFilter

data class Ctf(val id: Int, val grupoId: Int, val puntuacion: Int)
data class Grupo(val grupoid: Int,val mejorCtfId: Int = 0)

fun main(args: Array<String>) {
    println(args[0])
    println(args[1])
    println(args[2])

    val participaciones = CtfParticipants.participaciones
    val filterParameters = ArgsFilter(args)
    val mejoresCtfByGroupId = calculaMejoresResultados(participaciones)

}

/**
 * TODO
 *
 * @param participaciones
 * @return devuelve un mutableMapOf<Int, Pair<Int, Ctf>> donde
 *      Key: el grupoId del grupo
 *      Pair:
 *          first: Mejor posición
 *          second: Objeto CTF el que mejor ha quedado
 */
private fun calculaMejoresResultados(participaciones: List<Ctf>): MutableMap<Int, Pair<Int, Ctf>> {
    val participacionesByCTFId = participaciones.groupBy { it.id }
    val participacionesByGrupoId = participaciones.groupBy { it.grupoId }
    // Guarda id del participante, la id del grupo y la puntuacion maxima del participante en ese grupo
    val mejoresCtfByGroupId = mutableMapOf<Int, Pair<Int, Ctf>>()
    participacionesByCTFId.values.forEach { ctfs ->
        // Ordena el grupo segun la puntucion minima debido al valor ASCII por
        // lo que usamos el reverse para obtener el maximo
        val ctfsOrderByPuntuacion = ctfs.sortedBy { it.puntuacion }.reversed()
        participacionesByGrupoId.keys.forEach { grupoId ->
            // grupoID toma el valor de uno y busca el primer indice en el que es 1
            val posicionNueva = ctfsOrderByPuntuacion.indexOfFirst { it.grupoId == grupoId }
            if (posicionNueva >= 0) {
                val posicionMejor = mejoresCtfByGroupId.getOrDefault(grupoId, null)
                if (posicionMejor != null) {
                    if (posicionNueva < posicionMejor.first){
                        mejoresCtfByGroupId[grupoId] = Pair(posicionNueva, ctfsOrderByPuntuacion[posicionNueva])
                    }
                } else {
                    mejoresCtfByGroupId[grupoId] = Pair(posicionNueva, ctfsOrderByPuntuacion[posicionNueva])
                }
            }
        }
    }

    return mejoresCtfByGroupId
}
