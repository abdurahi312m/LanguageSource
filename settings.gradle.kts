pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LanguageSource"
include(":app")
include(":feature-language")
include(":feature-literature")
include(":feature-library")
include(":feature-training")
include(":domain")
include(":core")
//include(":data")
include(":feature-splash")
include(":feature-profile")
include(":feature-auth")
include(":data")
