object Project : Project({
    uuid = "my_project_id" // uuid should be some constant, never changing string; it is important for preserving history, new id means new entity with new history
    id = "ExampleOfDSL"
    parentId = "_Root" // id of the parent project
    name = "Example of DSL"

    val vcsRoot = GitVcsRoot({
        uuid = "my_vcs_root_id"
        id = "ExampleOfDSL_VcsRoot"
        name = "Example of DSL VCS Root"
        url = "<url to my git repository>"
    })
    buildType {
        uuid = "my_build_type_id"
        id = "ExampleOfDSL_Build"
        name = "Build"
        buildNumberPattern = "%build.counter%"

        +:build/libs/* => target

        vcs {
            root(vcsRoot) // thanks to Kotlin, here we can have static reference to project VCS root
        }
        steps {
            gradle {
                name = "clean & build"
                tasks = "clean build"
            }
        }
        requirements {
            contains("teamcity.agent.jvm.os.name", "Linux")
        }
    }
})
