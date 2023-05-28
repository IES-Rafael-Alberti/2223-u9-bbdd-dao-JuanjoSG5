import log.LogHandler

fun main(){

    val participations : List<Int> = CtfParticipants.participaciones.map {it.id}
    LogHandler.info("Lista id participantes: $participations")
}