package dev.agustacandi.learn.pestsentry.utils

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

object Helper {
    fun reloadKoinModules() {
        unloadKoinModules(ConstVal.koinModules)
        loadKoinModules(ConstVal.koinModules)
    }
}