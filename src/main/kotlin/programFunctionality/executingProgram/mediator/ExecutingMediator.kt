package programFunctionality.executingProgram.mediator

import programFunctionality.executingProgram.commandHandling.CommandExecution

interface ExecutingMediator {
    fun select(sender: CommandExecution, args: Any? = null)
}