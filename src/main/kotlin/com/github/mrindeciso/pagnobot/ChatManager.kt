package com.github.mrindeciso.pagnobot

import com.github.mrindeciso.pagnobot.commands.CustomCommand
import com.github.mrindeciso.pagnobot.commands.DeleteCommand
import com.github.mrindeciso.pagnobot.commands.NewCommand
import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.TwitchClient


class ChatManager(client: TwitchClient) {

    init {
        client.chat.apply {
            joinChannel("reafstreams")
            println("Chat Manager init")
            eventManager.getEventHandler(SimpleEventHandler::class.java).apply {
                registerListener(NewCommand())
                registerListener(DeleteCommand())
                registerListener(CustomCommand())
            }
        }
    }

}