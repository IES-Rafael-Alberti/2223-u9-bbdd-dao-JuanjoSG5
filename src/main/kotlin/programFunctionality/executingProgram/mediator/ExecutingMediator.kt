package programFunctionality.executingProgram.mediator

import programFunctionality.executingProgram.commandHandling.CommandValidator

interface ExecutingMediator {
    fun selectCommand(sender: CommandValidator)
}