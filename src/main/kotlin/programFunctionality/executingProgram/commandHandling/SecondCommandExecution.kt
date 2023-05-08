package programFunctionality.executingProgram.commandHandling

import programFunctionality.executingProgram.mediator.ExecutingMediator

class SecondCommandExecution(private val mediator: ExecutingMediator, args:Array<String>): CommandExecution {
    private val ctfid : Int = args[1].toInt()
    private val grupoId : Int = args[2].toInt()
    private val ctfidList : List<Int> = CtfParticipants.participaciones.map {it.id}
    private val grupoIdList : List<Int> = CtfParticipants.participaciones.map {it.grupoId}
    init {
        checkValues()
        executeCommand()
    }

    override fun executeCommand() {
        TODO("Not yet implemented")
    }

    override fun checkValues() {
        require(value = ctfid  in ctfidList) {"ERROR: El número de parametros no es adecuado."}
        require( value = grupoId in grupoIdList) {"ERROR: El número de parametros no es adecuado."}
    }


}