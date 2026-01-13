pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
    }
    plugins {
        id("architectury-plugin") version "3.4-SNAPSHOT"
    }
}

rootProject.name = "More-Essential-Commands"

include("common")
include("fabric")
include("neoforge")
