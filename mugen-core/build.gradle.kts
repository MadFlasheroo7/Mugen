plugins {
    alias(libs.plugins.mugen.cmp.library)
}

mavenPublishing {
    val artifactId = "mugen-core"
    coordinates(
        groupId = "pro.jayeshseth.mugen",
        artifactId = artifactId,
        version = libs.versions.mugen.get()
    )

    pom {
        name.set(artifactId)
        description.set("Core headless tokens, component contracts, and PlainLook for Mugen.")
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                // material3 only for PlainButtonRenderer ripple + PlainTextRenderer Text
                implementation(compose.material3)
            }
        }
    }
}
