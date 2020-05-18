package com.github.mrindeciso.pagnobot.commands.predefined

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.GenericCommand

class InfoCommand(command: Command) : GenericCommand() {

    init {
        sendText("PagnottaBOT V1.0")
    }

}