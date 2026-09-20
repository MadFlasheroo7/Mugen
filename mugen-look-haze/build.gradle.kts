plugins {
    alias(libs.plugins.mugen.cmp.library)
}

mavenPublishing {
    val artifactId = "mugen-look-haze"
    coordinates(
        groupId = "pro.jayeshseth.mugen",
        artifactId = artifactId,
        version = libs.versions.mugen.get()
    )

    pom {
        name.set(artifactId)
        description.set("Signature glassmorphic and glowing look & renderers for Mugen.")
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // api = consumers of mugen-look-haze automatically get mugen-core on the classpath
                api(projects.mugenCore)
                implementation(libs.haze)
                implementation(libs.glowingbutton)
                implementation(libs.hypnoticcanvas)
                implementation(libs.hypnoticcanvas.shaders)
            }
        }
    }
}
