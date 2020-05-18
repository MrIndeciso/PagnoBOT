package com.github.mrindeciso.pagnobot.commands

import com.github.mrindeciso.pagnobot.chat.ChatManager
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import org.mapdb.HTreeMap

abstract class GenericCommand : KoinComponent {

    val chatManager: ChatManager by inject()

    val commandMap: HTreeMap<String, String> by inject(named("commandMap"))
    val aliasMap: HTreeMap<String, String> by inject(named("aliasMap"))

    fun executeCommand(name: String): Boolean {
        if (aliasMap.containsKey(name)) {
            val code = aliasMap[name]
            if (commandMap.containsKey(code)) {
                commandMap[code]?.let { chatManager.sendMessage(it) }
                return true
            }
        }
        return false
    }

    fun sendText(message: String) {
        chatManager.sendMessage(message)
    }

    fun sendSuccess(user: String) {
        chatManager.sendPrivateMessage(user, "Comando eseguito con successo")
    }

    fun sendError(user: String) {
        chatManager.sendPrivateMessage(user, "Errore durante l'esecuzione del comando")
    }

}