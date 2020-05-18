package com.github.mrindeciso.pagnobot.commandParsing

import com.github.mrindeciso.pagnobot.commandParsing.models.Command
import com.github.mrindeciso.pagnobot.commandParsing.models.CommandArgument
import com.github.mrindeciso.pagnobot.commandParsing.models.CommandName
import com.github.mrindeciso.pagnobot.commandParsing.models.UserLevel
import com.github.twitch4j.chat.TwitchChat
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.sql.Timestamp

class CommandTokenizer : KoinComponent {

    val chat: TwitchChat by inject()

    fun parse(command: String): Command {
        return if (command.contains(' ')) {
            val commandRoot = command.subSequence(0, command.indexOf(' ')).toString()
            val splits = command.removePrefix(commandRoot).removePrefix(" ").split(';')
            Command(
                    UserLevel.STREAMER,
                    "console",
                    "",
                    Timestamp(System.currentTimeMillis()),
                    CommandName(commandRoot),
                    splits.map { CommandArgument(it) }
            )
        } else {
            Command(
                    UserLevel.STREAMER,
                    "console",
                    "",
                    Timestamp(System.currentTimeMillis()),
                    CommandName(command),
                    listOf()
            )
        }
    }

    fun parse(command: ChannelMessageEvent): Command {
        return if (command.message.contains(' ')) {
            val commandRoot = command.message.subSequence(0, command.message.indexOf(' ')).removePrefix("!").toString()
            val splits = command.message.removePrefix("!").removePrefix(commandRoot).removePrefix(" ").split(';')
            Command(
                    UserLevel.fromPermission(command.permissions),
                    command.user.name,
                    command.channel.name,
                    Timestamp(command.firedAt.timeInMillis),
                    CommandName(commandRoot),
                    splits.map { CommandArgument(it) }
            )
        } else {
            Command(
                    UserLevel.fromPermission(command.permissions),
                    command.user.name,
                    command.channel.name,
                    Timestamp(command.firedAt.timeInMillis),
                    CommandName(command.message.removePrefix("!")),
                    listOf()
            )
        }
    }

}