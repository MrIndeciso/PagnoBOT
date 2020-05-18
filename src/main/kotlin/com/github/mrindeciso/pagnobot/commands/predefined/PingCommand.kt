package com.github.mrindeciso.pagnobot.commands.predefined

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.GenericCommand

class PingCommand(command: Command) : GenericCommand() {

    init {
        sendText("Mi sentite?")
    }

}