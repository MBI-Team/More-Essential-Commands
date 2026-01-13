package com.github.mbi_team.moreessentialcommands.fabric

import com.github.mbi_team.moreessentialcommands.MoreEssentialCommands
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback

class MoreEssentialCommandsFabric : ModInitializer {
    override fun onInitialize() {
        // Initialize common module
        MoreEssentialCommands.init()
        
        // Register commands
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            MoreEssentialCommands.registerCommands(dispatcher)
        }
    }
}
