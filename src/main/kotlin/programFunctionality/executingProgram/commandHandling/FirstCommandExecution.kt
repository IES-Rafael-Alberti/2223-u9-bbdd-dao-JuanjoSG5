package programFunctionality.executingProgram.commandHandling

import Ctf
import programFunctionality.executingProgram.mediator.ExecutingMediator


/*
import Ctf
import CtfParticipants
import programFunctionality.executingProgram.mediator.ExecutingMediator
*/


class FirstCommandExecution(private val mediator: ExecutingMediator, private val args:Array<String>): CommandExecution {
    private val ctfid : Int = args[1].toInt()
    private val grupoId : Int = args[2].toInt()
    private val puntuacion : Int = args[3].toInt()
    private val ctfidList : List<Int> = CtfParticipants.participaciones.map {it.id}
    private val grupoIdList : List<Int> = CtfParticipants.participaciones.map {it.grupoId}
    private val puntuacionList : List<Int> = CtfParticipants.participaciones.map {it.puntuacion}

    init {
        checkValues()

    }
    override fun executeCommand() {
        TODO("Not yet implemented")
    }

    override fun checkValues() {
        require(value = ctfid  !in ctfidList) {"ERROR: El número de parametros no es adecuado."}
        require( value = grupoId !in grupoIdList) {"ERROR: El número de parametros no es adecuado."}
        require( value = puntuacion !in puntuacionList) {"ERROR: El número de parametros no es adecuado."}
    }




}
