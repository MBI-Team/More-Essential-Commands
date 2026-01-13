package com.github.mbi_team.moreessentialcommands

import com.github.mbi_team.moreessentialcommands.commands.*
import net.minecraft.commands.Commands

object MoreEssentialCommands {
    const val MOD_ID = "moreessentialcommands"
    const val MOD_NAME = "More Essential Commands"
    const val MOD_VERSION = "1.0.0"
    
    fun init() {
        println("$MOD_NAME v$MOD_VERSION initialized")
    }
    
    fun registerCommands(dispatcher: Commands.CommandDispatcher<*>) {
        // Register all commands here
        FlyCommand.register(dispatcher)
        HealCommand.register(dispatcher)
        SpawnCommand.register(dispatcher)
        RtpCommand.register(dispatcher)
        TpaCommand.register(dispatcher)
        TpAcceptCommand.register(dispatcher)
        TpDenyCommand.register(dispatcher)
        InvseeCommand.register(dispatcher)
        EnderchestCommand.register(dispatcher)
        GmCommand.register(dispatcher)
        BackCommand.register(dispatcher)
        PingCommand.register(dispatcher)
    }
}
