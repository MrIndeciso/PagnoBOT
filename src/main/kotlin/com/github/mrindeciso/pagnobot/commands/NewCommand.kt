package com.github.mrindeciso.pagnobot.commands

import com.github.philippheuer.events4j.simple.domain.EventSubscriber
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import com.github.twitch4j.common.enums.CommandPermission
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.mapdb.HTreeMap
import java.util.concurrent.ConcurrentMap

class NewCommand: KoinComponent {

    val dbMap: HTreeMap<String, String> by inject()

    @EventSubscriber
    fun handleEvent(event: ChannelMessageEvent) {
        if (event.message.contains("!newcommand")) {
            if (/*event.permissions.contains(CommandPermission.MODERATOR)*/ true) {
                val newCommand = event.message.removePrefix("!newcommand ")
                val commandName = newCommand.split(' ')[0]
                val commandOutput = newCommand.removePrefix("$commandName ")
                if (dbMap.containsKey(commandName)) {
                    event.twitchChat.sendMessage("reafstreams", "C'è già un comando con lo stesso nome")
                } else {
                    dbMap[commandName] = commandOutput
                    event.twitchChat.sendMessage("reafstreams", "Comando registrato")
                }
            } else {
                event.twitchChat.sendMessage("reafstreams", "Devi essere moderatore per usare questo comando")
            }
        }
    }

}