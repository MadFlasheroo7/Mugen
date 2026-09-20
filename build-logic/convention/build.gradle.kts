plugins {
    `kotlin-dsl`
}

group = "pro.jayeshseth.mugen.buildlogic"

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "mugen.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("cmpLibraryCompose") {
            id = "mugen.cmp.library"
            implementationClass = "CmpLibraryConventionPlugin"
        }
        register("cmpApplication") {
            id = "mugen.cmp.application"
            implementationClass = "CmpApplicationConventionPlugin"
        }
    }
}