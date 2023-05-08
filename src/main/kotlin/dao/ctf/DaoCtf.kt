package dao.ctf

import Ctf
import dao.Dao

interface DaoCtf: Dao {
    fun createCtf(ctf: Ctf): Int?
}