package com.github.mrindeciso.pagnobot.commands

import com.github.philippheuer.events4j.simple.domain.EventSubscriber
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.mapdb.HTreeMap
import java.util.concurrent.ConcurrentMap

class CustomCommand: KoinComponent {

    private val dbMap: HTreeMap<String, String> by inject()

    @EventSubscriber
    fun handleEvent(event: ChannelMessageEvent) {
        if (event.message.startsWith('!')) {
            val command = event.message.split(' ')[0].removePrefix("!")
            if (dbMap.containsKey(command)) {
                event.twitchChat.sendMessage("reafstreams", dbMap[command])
            }
        }
    }

}