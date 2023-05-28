package programFunctionality.executingProgram.programExecution

import Grupo
import dao.grupos.DaoGruposImpl
import factoriaDatos.DataFactory
import programFunctionality.executingProgram.calculator.Calculator

class SecondCommandExecution: ExecuteProgram {
    val dataSource = DataFactory.getDataSource(DataFactory.DataSourceType.Embedded)
    private val dao = DaoGruposImpl(dataSource)
    override fun execute() {
        val mejoresResultados = Calculator.calculaMejoresResultados(CtfParticipants.participaciones)
        for ((grupoId, mejorPosCtf) in mejoresResultados) {
            val mejorCtf = mejorPosCtf.second
            dao.updateGrupo(Grupo(grupoId, mejorCtf.id))
        }
    }
}