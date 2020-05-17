package com.github.mrindeciso.pagnobot.commands

import com.github.philippheuer.events4j.simple.domain.EventSubscriber
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

class DeleteCommand {

    @EventSubscriber
    fun handleEvent(event: ChannelMessageEvent) {

    }

}