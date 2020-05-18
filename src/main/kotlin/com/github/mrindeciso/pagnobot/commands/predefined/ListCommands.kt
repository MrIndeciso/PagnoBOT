package com.github.mrindeciso.pagnobot.commands.predefined

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commands.GenericCommand

class ListCommands(command: Command) : GenericCommand() {

    init {
        val text = if (aliasMap.keys.isNotEmpty()) {
            aliasMap.keys.joinToString(prefix = "!", separator = ", !")
        } else {
            "Nessun comando registrato. Usa !newcommand per registrare un nuovo comando"
        }
        sendText(text)
    }

}