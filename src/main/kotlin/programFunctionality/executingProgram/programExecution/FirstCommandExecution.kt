package programFunctionality.executingProgram.programExecution

import Ctf
import CtfParticipants
import Grupo
import dao.ctf.DaoImpCtf
import dao.grupos.DaoGruposImpl
import factoriaDatos.DataFactory
import programFunctionality.executingProgram.calculator.Calculator

class FirstCommandExecution() : ExecuteProgram {

    private val participations = CtfParticipants.participaciones
    val dataSource = DataFactory.getDataSource(DataFactory.DataSourceType.Embedded)
    private val dao = DaoGruposImpl(dataSource)

    init {
        execute()
    }
    override fun execute() {
        val mejoresResultados = Calculator.calculaMejoresResultados(participations)
        for ((grupoId, mejorPosCtf) in mejoresResultados) {
            val mejorCtf = mejorPosCtf.second
            dao.updateGrupo(Grupo(grupoId,mejorCtf.id))
        }
    }
}