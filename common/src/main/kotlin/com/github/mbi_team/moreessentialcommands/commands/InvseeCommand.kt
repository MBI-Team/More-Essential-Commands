package com.github.mbi_team.moreessentialcommands.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.inventory.MenuType

class InvseeCommand : BaseCommand() {
    override val name = "invsee"
    override val permissionLevel = 2
    
    override fun configure(builder: LiteralArgumentBuilder<CommandSourceStack>) {
        builder
            // /invsee <player>
            .then(net.minecraft.commands.Commands.argument("target", EntityArgument.player())
                .executes { context ->
                    val sourcePlayer = context.source.player
                    val targetPlayer = EntityArgument.getPlayer(context, "target")
                    
                    if (sourcePlayer != null && targetPlayer != null) {
                        openInventoryScreen(sourcePlayer, targetPlayer)
                        sourcePlayer.sendSystemMessage(Component.literal("Opened inventory of ${targetPlayer.name.string}"))
                        1
                    } else {
                        context.source.sendSystemMessage(Component.literal("This command can only be used by players"))
                        0
                    }
                }
            )
    }
    
    private fun openInventoryScreen(source: ServerPlayer, target: ServerPlayer) {
        val containerId = source.nextContainerCounter()
        val menuProvider = object : SimpleMenuProvider({
            id: Int, playerInventory: Inventory, player: net.minecraft.world.entity.player.Player ->
            createInventoryMenu(id, playerInventory, target)
        }, Component.literal("${target.name.string}'s Inventory")) {}
        
        source.openMenu(menuProvider, containerId, source.position())
    }
    
    private fun createInventoryMenu(
        id: Int,
        playerInventory: Inventory,
        target: ServerPlayer
    ): AbstractContainerMenu {
        // Create a chest menu that shows the target's inventory
        // This is a simplified implementation, in a real mod you'd create a custom menu
        return ChestMenu.MenuConstructor { containerId, inventory, _ ->
            ChestMenu(MenuType.GENERIC_9x6, containerId, inventory, target.inventory, 6)
        }.createMenu(id, playerInventory, target)
    }
}
