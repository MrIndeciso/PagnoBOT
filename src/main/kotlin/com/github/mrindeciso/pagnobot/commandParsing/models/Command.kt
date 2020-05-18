package com.github.mrindeciso.pagnobot.commandParsing.models

import java.sql.Timestamp

data class Command(

        val permission: UserLevel,

        val username: String,

        val channelName: String,

        val commandTime: Timestamp,

        val commandRoot: CommandName,

        val commandTokens: List<CommandFragment>

)