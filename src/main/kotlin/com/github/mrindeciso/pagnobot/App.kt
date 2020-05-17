package com.github.mrindeciso.pagnobot

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer

//const val accessToken = "7zwk39w7imgpnmpjdmv92jtvwcmaez"
//const val refreshToken = "su1tsk2fewxmgszfsqqziqz7c01nzt1b7r6a7i1tzr4u3fqxpc"
//const val clientId = "gp762nuuoqcoxypju8c569th9wz7q5"

const val accessToken = "eks54vissa4g41tot827sug8yb3ngw"
const val refreshToken = "rywbw9ahpqfurru5al6ff7fab7af8o46fwa3j04wp560lwc3ck"
const val clientId = "gp762nuuoqcoxypju8c569th9wz7q5"

val baseModule = module {
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
        DBMaker
            .fileDB("commands.db")
            .fileMmapEnable()
            .closeOnJvmShutdown()
            .make()
    }
    single {
        get<DB>().hashMap("commandMap", Serializer.STRING, Serializer.STRING).createOrOpen()
    }
}

fun main() {
    startKoin {
        modules(listOf(baseModule))

        ChatManager(koin.get())
    }
}