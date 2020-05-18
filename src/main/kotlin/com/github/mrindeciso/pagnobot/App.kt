package com.github.mrindeciso.pagnobot

import com.github.mrindeciso.pagnobot.chat.ChatManager
import com.github.mrindeciso.pagnobot.commandParsing.CommandExecutor
import com.github.mrindeciso.pagnobot.commandParsing.CommandTokenizer
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer
import kotlin.concurrent.thread

//const val accessToken = "7zwk39w7imgpnmpjdmv92jtvwcmaez"
//const val refreshToken = "su1tsk2fewxmgszfsqqziqz7c01nzt1b7r6a7i1tzr4u3fqxpc"
//const val clientId = "gp762nuuoqcoxypju8c569th9wz7q5"

const val accessToken = "oc7k053gudj48wogwn0rah0eicmw8f"
const val refreshToken = "69xgelsmhuamqgkl4ff36zn0jzl85176lwaf3tiuxfmrzn0opj"
const val clientId = "gp762nuuoqcoxypju8c569th9wz7q5"

val twitchModule = module {
    single { OAuth2Credential("twitch", accessToken) }
    single {
        TwitchClientBuilder.builder()
                .withClientId(clientId)
                .withEnableChat(true)
                .withChatAccount(get())
                .build()
    }
    single {
        get<TwitchClient>().eventManager
    }
    single {
        get<TwitchClient>().chat
    }
}

val dbModule = module {
    single {
        DBMaker
                .fileDB("commands.db")
                .fileMmapEnable()
                .closeOnJvmShutdown()
                .make()
    }
    single(named("commandMap")) {
        get<DB>().hashMap("commandMap").keySerializer(Serializer.STRING).valueSerializer(Serializer.STRING).createOrOpen()
    }
    single(named("aliasMap")) {
        get<DB>().hashMap("aliasMap", Serializer.STRING, Serializer.STRING).createOrOpen()
    }
}

val commandModule = module {
    single {
        CommandTokenizer()
    }
    single {
        CommandExecutor()
    }
}

val chatModule = module {
    single {
        ChatManager("reafstreams")
    }
}

fun main() {
    startKoin {
        modules(listOf(twitchModule, dbModule, commandModule, chatModule))

        koin.get<ChatManager>().init()

        thread {
            while (true) {
                val cmd = readLine()
                cmd?.let {
                    val command = koin.get<CommandTokenizer>().parse(it)
                    koin.get<CommandExecutor>().execute(command)
                }
            }
        }
    }
}