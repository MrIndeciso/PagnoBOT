package com.github.mrindeciso.pagnobot.utils

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

fun ChannelMessageEvent.sendChatMessage(text: String?) = twitchChat.sendMessage(channel.name, text)

