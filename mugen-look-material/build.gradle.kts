plugins {
    alias(libs.plugins.mugen.cmp.library)
}

mavenPublishing {
    val artifactId = "mugen-look-material"
    coordinates(
        groupId = "pro.jayeshseth.mugen",
        artifactId = artifactId,
        version = libs.versions.mugen.get()
    )

    pom {
        name.set(artifactId)
        description.set("Material 3 themed look & renderers for Mugen.")
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.mugenCore)
                // material3 needed for MaterialButtonRenderer ripple + MaterialTextRenderer
                implementation(compose.material3)
            }
        }
    }
}
