import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

version = "2018.2"

project {

    buildType(Build)
    builType(Test)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "+:build/libs/* => target"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            tasks = "clean build"
            buildFile = ""
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})


object Test : BuildType({
    name = "Test"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            tasks = "clean integrationTest"
            buildFile = ""
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})
