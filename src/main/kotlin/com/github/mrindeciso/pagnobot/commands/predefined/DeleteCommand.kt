package com.github.mrindeciso.pagnobot.commands.predefined

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.GenericCommand

class DeleteCommand(command: Command) : GenericCommand() {

    init {
        if (!command.permission.hasElevatedPermissions() || command.commandTokens.size != 1) {
            command.commandTokens.forEach {
                executeCommand(it.command)
            }
            sendError("")
        } else {
            if (aliasMap.containsKey(command.commandTokens[0].command)) {
                aliasMap.remove(command.commandTokens[0].command)
                sendSuccess("")
            } else {
                sendError("")
            }
        }
    }

}