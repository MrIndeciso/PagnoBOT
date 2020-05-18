package com.github.mrindeciso.pagnobot.commands

import com.github.mrindeciso.pagnobot.commandParsing.models.Command

class CustomCommand(command: Command) : GenericCommand() {

    init {
        if (aliasMap.containsKey(command.commandRoot.command)) {
            val commandId = aliasMap[command.commandRoot.command]
            val commandText = commandMap[commandId]
            commandText?.let { sendText(it) }
        } else {
            println(aliasMap.keys.toString())
            println(commandMap.values.toString())
        }
    }

}