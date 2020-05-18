package com.github.mrindeciso.pagnobot.commandParsing

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.CustomCommand
import com.github.mrindeciso.pagnobot.commands.predefined.*
import org.koin.core.KoinComponent

class CommandExecutor : KoinComponent {

    fun execute(command: Command) {
        println(command.toString())
        when (command.commandRoot.command) {
            "newcommand", "newcmd" -> NewCommand(command)
            "deletecommand", "delcmd" -> DeleteCommand(command)
            "aliascommand", "aliascmd" -> AliasCommand(command)
            "cmds", "commands", "list", "comandi" -> ListCommands(command)
            "info" -> InfoCommand(command)
            "ping" -> PingCommand(command)
            else -> CustomCommand(command)
        }
    }

}