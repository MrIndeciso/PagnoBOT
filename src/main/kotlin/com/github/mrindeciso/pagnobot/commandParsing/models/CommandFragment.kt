package com.github.mrindeciso.pagnobot.commandParsing.models

sealed class CommandFragment(
        val command: String
)

class CommandName(command: String) : CommandFragment(command)

class CommandArgument(command: String) : CommandFragment(command)
