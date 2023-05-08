package programFunctionality.executingProgram.commandHandling

import programFunctionality.executingProgram.mediator.ConcreteMediator

class ThirdCommandExecution(mediator: ConcreteMediator, args:Array<String>): CommandExecution {
    private var checker: Int = 0
    private val grupoId : Int = args[2].toInt()
    private val grupoIdList : List<Int> = CtfParticipants.participaciones.map {it.grupoId}


    override fun executeCommand() {
        TODO("Not yet implemented")
    }

    override fun checkValues() {
        if (grupoId in grupoIdList){
            checker = 1
        }
    }
}