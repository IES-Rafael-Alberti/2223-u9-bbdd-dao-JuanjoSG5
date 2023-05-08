package programFunctionality.executingProgram.programExecution

import dao.grupos.DaoGruposImpl
import factoriaDatos.DataFactory
import programFunctionality.executingProgram.calculator.Calculator

class ThirdCommandExecution(private val checker:Int, args: Array<String>): ExecuteProgram {
    private val grupoId = args[1].toInt()
    private val participations = CtfParticipants.participaciones
    val dataSource = DataFactory.getDataSource(DataFactory.DataSourceType.Embedded)
    private val dao = DaoGruposImpl(dataSource)

    init{
        execute()
    }
    override fun execute() {
        if (checker == 1){
            val grupos = dao.getAllGrupos()
            println("Procesado: Listado de todos los grupos")
            grupos.forEach {
                println("  GRUPO: ${it.grupoid}     MEJORCTF: ${it.mejorCtfId}")
            }
        } else {
            // Show information for the specified group
            val grupo = dao.getGrupoById(grupoId)
            println("Procesado: Listado participación del grupo \"$grupoId\"")
            println("  GRUPO: ${grupo?.grupoid}   MEJORCTF: ${grupo?.mejorCtfId}")
        }
    }
}