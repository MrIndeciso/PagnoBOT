package com.github.mrindeciso.pagnobot.commands

import com.github.mrindeciso.pagnobot.utils.sendChatMessage
import com.github.philippheuer.events4j.simple.domain.EventSubscriber
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.mapdb.HTreeMap

class DeleteCommand : KoinComponent {

    private val dbMap: HTreeMap<String, String> by inject()

    @EventSubscriber
    fun handleEvent(event: ChannelMessageEvent) {
        if (event.message.contains("!deletecommand")) {
            if (/*event.permissions.contains(CommandPermission.MODERATOR)*/ true) {
                val deletedCommand = event.message.removePrefix("!deletecommand ")
                if (dbMap.containsKey(deletedCommand)) {
                    dbMap.remove(deletedCommand)
                    event.sendChatMessage("Comando rimosso")
                } else {
                    event.sendChatMessage("Impossibile trovare il comando specificato")
                }
            } else {
                event.sendChatMessage("Devi essere moderatore per usare questo comando")
            }
        }
    }

}