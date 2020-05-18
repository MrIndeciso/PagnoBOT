package com.github.mrindeciso.pagnobot.chat

import com.github.philippheuer.events4j.simple.SimpleEventHandler
import com.github.twitch4j.chat.TwitchChat
import org.koin.core.KoinComponent
import org.koin.core.inject


class ChatManager(
        val channelName: String
) : KoinComponent {

    val chat: TwitchChat by inject()

    fun init() {
        chat.apply {
            joinChannel(channelName)
            println("Chat Manager init")
            eventManager.getEventHandler(SimpleEventHandler::class.java).apply {
                registerListener(ChannelMessageListener())
            }
        }
    }

    fun sendMessage(message: String) {
        chat.sendMessage(channelName, message)
    }

    fun sendPrivateMessage(user: String, message: String) {
        chat.sendMessage(channelName, message)
    }

}