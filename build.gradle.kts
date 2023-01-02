@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidApplication) version Versions.gradlePlugin apply false
    id(Plugins.androidLibrary) version Versions.gradlePlugin apply false
    id(Plugins.kotlinAndroid) version Versions.kotlin apply false
    id(Plugins.hilt) version Versions.hilt apply false
}
