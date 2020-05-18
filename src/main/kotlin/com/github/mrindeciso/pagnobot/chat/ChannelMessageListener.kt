package com.github.mrindeciso.pagnobot.chat

import com.github.mrindeciso.pagnobot.commandParsing.CommandExecutor
import com.github.mrindeciso.pagnobot.commandParsing.CommandTokenizer
import com.github.philippheuer.events4j.simple.domain.EventSubscriber
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.koin.core.KoinComponent
import org.koin.core.inject

class ChannelMessageListener : KoinComponent {

    private val commandTokenizer: CommandTokenizer by inject()
    private val commandExecutor: CommandExecutor by inject()

    @EventSubscriber
    fun listen(message: ChannelMessageEvent) {
        if (message.message[0] == '!') {
            val tokenized = commandTokenizer.parse(message)
            commandExecutor.execute(tokenized)
        }
    }

}