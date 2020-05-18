package com.github.mrindeciso.pagnobot.commandParsing.models

import com.github.twitch4j.common.enums.CommandPermission

enum class UserLevel {
    USER,
    SUBSCRIBER,
    MODERATOR,
    STREAMER;

    companion object {

        fun fromPermission(permissions: Set<CommandPermission>): UserLevel {
            return if (permissions.contains(CommandPermission.BROADCASTER)) STREAMER
            else if (permissions.contains(CommandPermission.MODERATOR)) MODERATOR
            else if (permissions.contains(CommandPermission.SUBSCRIBER)) SUBSCRIBER
            else USER
        }

    }

    fun hasElevatedPermissions(): Boolean {
        return this == MODERATOR || this == STREAMER
    }
}