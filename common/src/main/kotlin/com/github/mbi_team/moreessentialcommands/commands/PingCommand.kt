package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component

class PingCommand : BaseCommand() {
    override val name = "ping"
    override val permissionLevel = 0
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder.executes { context ->
            val source = context.source
            val player = source.player
            
            if (player != null) {
                val ping = source.connection.latency
                source.sendSystemMessage(Component.literal("Your ping: $ping ms"))
            } else {
                source.sendSystemMessage(Component.literal("This command can only be used by players"))
            }
            
            1
        }
    }
}
