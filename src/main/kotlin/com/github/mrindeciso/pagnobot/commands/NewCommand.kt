package com.github.mrindeciso.pagnobot.commands

import com.github.mrindeciso.pagnobot.utils.sendChatMessage
import com.github.philippheuer.events4j.simple.domain.EventSubscriber
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.github.twitch4j.common.enums.CommandPermission
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.mapdb.HTreeMap

class NewCommand: KoinComponent {

    val dbMap: HTreeMap<String, String> by inject()

    @EventSubscriber
    fun handleEvent(event: ChannelMessageEvent) {
        if (event.message.contains("!newcommand")) {
            if (event.permissions.contains(CommandPermission.MODERATOR)) {
                val newCommand = event.message.removePrefix("!newcommand ")
                val commandName = newCommand.split(' ')[0]
                val commandOutput = newCommand.removePrefix("$commandName ")
                if (dbMap.containsKey(commandName)) {
                    event.sendChatMessage("C'è già un comando con lo stesso nome")
                } else {
                    dbMap[commandName] = commandOutput
                    event.sendChatMessage("Comando registrato")
                }
            } else {
                event.sendChatMessage("Devi essere moderatore per usare questo comando")
            }
        }
    }

}