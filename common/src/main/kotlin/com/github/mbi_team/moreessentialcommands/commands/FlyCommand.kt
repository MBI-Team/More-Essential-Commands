package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player

class FlyCommand : BaseCommand() {
    override val name = "fly"
    override val permissionLevel = 2
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /fly [on|off] [targets]
            .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                .then(net.minecraft.commands.Commands.literal("on")
                    .executes { context ->
                        val targets = EntityArgument.getPlayers(context, "targets")
                        targets.forEach { player ->
                            enableFly(player)
                        }
                        context.source.sendSystemMessage(Component.literal("Enabled fly mode for ${targets.size} players"))
                        targets.size
                    }
                )
                .then(net.minecraft.commands.Commands.literal("off")
                    .executes { context ->
                        val targets = EntityArgument.getPlayers(context, "targets")
                        targets.forEach { player ->
                            disableFly(player)
                        }
                        context.source.sendSystemMessage(Component.literal("Disabled fly mode for ${targets.size} players"))
                        targets.size
                    }
                )
            )
            // /fly [on|off]
            .then(net.minecraft.commands.Commands.literal("on")
                .executes { context ->
                    val player = context.source.player
                    if (player != null) {
                        enableFly(player)
                        context.source.sendSystemMessage(Component.literal("Enabled fly mode"))
                        1
                    } else {
                        context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                        0
                    }
                }
            )
            .then(net.minecraft.commands.Commands.literal("off")
                .executes { context ->
                    val player = context.source.player
                    if (player != null) {
                        disableFly(player)
                        context.source.sendSystemMessage(Component.literal("Disabled fly mode"))
                        1
                    } else {
                        context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                        0
                    }
                }
            )
            // /fly (toggle)
            .executes { context ->
                val player = context.source.player
                if (player != null) {
                    if (player.abilities.mayfly) {
                        disableFly(player)
                        context.source.sendSystemMessage(Component.literal("Disabled fly mode"))
                    } else {
                        enableFly(player)
                        context.source.sendSystemMessage(Component.literal("Enabled fly mode"))
                    }
                    1
                } else {
                    context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                    0
                }
            }
    }
    
    private fun enableFly(player: Player) {
        player.abilities.mayfly = true
        player.abilities.flying = true
        player.onUpdateAbilities()
    }
    
    private fun disableFly(player: Player) {
        player.abilities.mayfly = false
        player.abilities.flying = false
        player.onUpdateAbilities()
    }
}
