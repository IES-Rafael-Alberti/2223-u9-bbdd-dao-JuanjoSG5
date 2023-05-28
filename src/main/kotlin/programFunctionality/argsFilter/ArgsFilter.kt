package programFunctionality.argsFilter

import programFunctionality.executingProgram.commandHandling.FirstCommandValidator
import programFunctionality.executingProgram.commandHandling.SecondCommandValidator
import programFunctionality.executingProgram.commandHandling.ThirdCommandValidator
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
    private val mediator = ConcreteMediator(args)
    init {
        val firstCheck : Boolean  = firstChecker(args)
        val secondCheck : Boolean = secondChecker(args)
        val thirdCheck : Boolean = thirdChecker(args)
        if (firstCheck) {
            FirstCommandValidator(mediator,args)
        }else if (secondCheck){
            SecondCommandValidator(mediator,args)
        }else if (thirdCheck){
            ThirdCommandValidator(mediator,args)
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