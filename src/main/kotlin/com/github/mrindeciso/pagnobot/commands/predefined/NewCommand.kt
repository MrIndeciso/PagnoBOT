package com.github.mrindeciso.pagnobot.commands.predefined

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.GenericCommand

class NewCommand(command: Command) : GenericCommand() {

    init {
        if (command.commandTokens.size != 2 || !command.permission.hasElevatedPermissions())
            sendError(command.username)
        else {
            val newKey = commandMap.count().toString()
            commandMap[newKey] = command.commandTokens[1].command
            aliasMap[command.commandTokens[0].command] = newKey
            sendSuccess(command.username)
        }
    }

}