package programFunctionality.executingProgram.commandHandling

import programFunctionality.executingProgram.mediator.ConcreteMediator

class ThirdCommandValidator(private val mediator: ConcreteMediator, private val args:Array<String>): CommandValidator {
    internal var checker: Int = 0
    private val grupoId : Int = args[2].toInt()
    private val grupoIdList : List<Int> = CtfParticipants.participaciones.map {it.grupoId}

    init {
        checkValues()
        mediator.selectCommand(this)
    }

    override fun checkValues() {
        require(args.size == 2) {"ERROR: El número de parametros no es adecuado."}
        if (grupoId in grupoIdList){
            checker = 1
        }
    }
}