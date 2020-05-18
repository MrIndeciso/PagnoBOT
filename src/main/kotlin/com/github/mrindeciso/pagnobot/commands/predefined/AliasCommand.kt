package com.github.mrindeciso.pagnobot.commands.predefined

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.GenericCommand

class AliasCommand(command: Command) : GenericCommand() {

    init {
        if (command.commandTokens.size == 2 && command.permission.hasElevatedPermissions()) {
            if (!aliasMap.containsKey(command.commandTokens[1].command)) {
                sendError("")
            } else {
                val commandNum = aliasMap[command.commandTokens[1].command]
                aliasMap[command.commandTokens[0].command] = commandNum
                sendSuccess("")
            }
        } else {
            sendError("")
        }
    }

}