package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack

abstract class BaseCommand {
    protected abstract val name: String
    protected abstract val permissionLevel: Int
    
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val builder = LiteralArgumentBuilder.literal<CommandSourceStack>(name)
        configure(builder)
        dispatcher.register(builder.requires { it.hasPermission(permissionLevel) })
    }
    
    protected abstract fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>)
}
