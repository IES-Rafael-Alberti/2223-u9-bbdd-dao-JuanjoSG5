package programFunctionality.executingProgram.mediator

import CtfParticipants
import programFunctionality.executingProgram.commandHandling.CommandExecution
import programFunctionality.executingProgram.commandHandling.FirstCommandExecution
import programFunctionality.executingProgram.commandHandling.SecondCommandExecution

class ConcreteMediator: ExecutingMediator {

    // components may be injected or passed in constructor
    // but Mediator reference must be passed to each component
   // val SecondCommandExecuted = SecondCommandExecution(this, CtfParticipants.participaciones)
  //  val FirstCommandExecuted = FirstCommandExecution(this, CtfParticipants.participaciones)
    //val componentC = (this, CtfParticipants.participaciones)

    override fun select(sender: CommandExecution, args: Any?) {
        // checking which object is sender
        when (sender) {
            // Mediator knows how to handle the incoming message
            is FirstCommandExecution -> println("arg from A: $args")
            is SecondCommandExecution -> println("arg from B: ${args as Float * 3}")
            // is ComponentC -> (args as () -> Any).invoke()
        }
    }

}