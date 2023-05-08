package programFunctionality.argsFilter

import programFunctionality.executingProgram.commandHandling.FirstCommandExecution
import programFunctionality.executingProgram.commandHandling.SecondCommandExecution
import programFunctionality.executingProgram.commandHandling.ThirdCommandExecution
import programFunctionality.executingProgram.mediator.ConcreteMediator


const val FirstCommand: String = "-a"
const val SecondCommand: String = "-d"
const val ThirdCommand: String = "-l"

/**
 * Filters the srguments passed to the main and effectively determines how the program will continue to operate
 *
 * @param args  arguments passed in the main
 */
class ArgsFilter(args:Array<String>) {
    var filterResult = 0
    private val mediator = ConcreteMediator()
    init {
        val firstCheck : Boolean  = firstChecker(args)
        val secondCheck : Boolean = secondChecker(args)
        val thirdCheck : Boolean = thirdChecker(args)
        if (firstCheck) {
            FirstCommandExecution(mediator,args)
        }else if (secondCheck){
            SecondCommandExecution(mediator,args)
        }else if (thirdCheck){
            ThirdCommandExecution(mediator,args)
        }else{
            error("Introduzca uno de los comandos del sistema")
        }
    }



    private fun firstChecker(args:Array<String>): Boolean {
        return args[0] == FirstCommand
    }
    private fun secondChecker(args:Array<String>): Boolean {
        return args[0] == SecondCommand
    }
    private fun thirdChecker(args:Array<String>): Boolean {
        return args[0] == ThirdCommand
    }
}