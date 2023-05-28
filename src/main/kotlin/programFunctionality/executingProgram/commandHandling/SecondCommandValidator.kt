package programFunctionality.executingProgram.commandHandling

import programFunctionality.executingProgram.mediator.ExecutingMediator

class SecondCommandValidator(private val mediator: ExecutingMediator,private val args:Array<String>): CommandValidator {
    private val ctfid : Int = args[1].toInt()
    private val grupoId : Int = args[2].toInt()
    private val ctfidList : List<Int> = CtfParticipants.participaciones.map {it.id}
    private val grupoIdList : List<Int> = CtfParticipants.participaciones.map {it.grupoId}
    init {
        checkValues()
        mediator.selectCommand(this)
    }



    override fun checkValues() {
        require(args.size == 3 )
        require(value = ctfid  in ctfidList) {"ERROR: El número de parametros no es adecuado."}
        require( value = grupoId in grupoIdList) {"ERROR: El número de parametros no es adecuado."}
    }


}