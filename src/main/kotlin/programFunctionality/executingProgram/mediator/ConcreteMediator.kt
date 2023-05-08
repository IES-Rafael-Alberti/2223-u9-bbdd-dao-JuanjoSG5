package programFunctionality.executingProgram.mediator

import Ctf
import dao.ctf.DaoImpCtf
import factoriaDatos.DataFactory
import programFunctionality.executingProgram.commandHandling.CommandValidator
import programFunctionality.executingProgram.commandHandling.FirstCommandValidator
import programFunctionality.executingProgram.commandHandling.SecondCommandValidator
import programFunctionality.executingProgram.commandHandling.ThirdCommandValidator
import programFunctionality.executingProgram.programExecution.FirstCommandExecution
import programFunctionality.executingProgram.programExecution.SecondCommandExecution
import programFunctionality.executingProgram.programExecution.ThirdCommandExecution

class ConcreteMediator(private var args:Array<String>): ExecutingMediator {

    // components may be injected or passed in constructor
    // but Mediator reference must be passed to each component
    val FirstCommand = FirstCommandValidator(this, args)
    val SecondCommand = SecondCommandValidator(this, args)
    val ThirdCommand = ThirdCommandValidator(this, args)
    private var checker = ThirdCommand.checker

    override fun selectCommand(sender: CommandValidator) {
        // checking which object is sender
        when (sender) {
            // Mediator knows how to handle the incoming message
            is FirstCommandValidator -> FirstCommandExecution()
            is SecondCommandValidator -> SecondCommandExecution()
            is ThirdCommandValidator -> ThirdCommandExecution(checker,args = args)
        }
    }

}