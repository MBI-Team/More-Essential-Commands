package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player

class HealCommand : BaseCommand() {
    override val name = "heal"
    override val permissionLevel = 2
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /heal [targets]
            .then(net.minecraft.commands.Commands.argument("targets", EntityArgument.players())
                .executes { context ->
                    val targets = EntityArgument.getPlayers(context, "targets")
                    targets.forEach { player ->
                        healPlayer(player)
                    }
                    context.source.sendSystemMessage(Component.literal("Healed ${targets.size} players"))
                    targets.size
                }
            )
            // /heal
            .executes { context ->
                val player = context.source.player
                if (player != null) {
                    healPlayer(player)
                    context.source.sendSystemMessage(Component.literal("Healed"))
                    1
                } else {
                    context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                    0
                }
            }
    }
    
    private fun healPlayer(player: Player) {
        player.health = player.maxHealth
        player.foodData.foodLevel = 20
        player.foodData.saturationLevel = 5.0f
        player.foodData.exhaustionLevel = 0.0f
        player.clearFire()
        player.removeAllEffects()
    }
}
