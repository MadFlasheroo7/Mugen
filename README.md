# Mugen (無限)

> A modern, multiplatform, token-driven Compose design system built on complete separation of **Visual Identity (Tokens)**, **Drawing Engines (Renderers)**, and **Component Layout Defaults**.

Supported platforms: **Android**, **Desktop (JVM)**, **Web (JS & WasmJs)**, and **iOS (ARM64, Simulator, X64)**.

---

## Architecture Pillars

Mugen is built around three core architectural tenets:

1. **`MugenLook` (Who the UI is):**
   A pure visual identity carrying the 6 fundamental token families:
   - `colors` (`MugenColors`)
   - `shapes` (`MugenShapes`)
   - `motion` (`MugenMotion`)
   - `typography` (`MugenTypography`)
   - `spacing` (`MugenSpacing`)
   - `elevation` (`MugenElevation`)

2. **`MugenRendererSet` (How the UI is drawn):**
   The pluggable drawing layer. Decoupled from tokens:
   - `PlainRenderers`: Built-in zero-dependency clean drawing engine.
   - `HazeRendererSet`: Glassmorphic, glowing, corner-morphing drawing engine.
   - `MaterialRendererSet`: Material-3 surfaces and ripple drawing engine.

3. **`MugenDefaultsRegistry` (Zero magic numbers in core):**
   Core provides pure `@Immutable` component data class contracts. Individual Looks own and register their bespoke component dimensions, paddings, and metrics via `mugenDefaults { register(...) }`.
   Resolutions are dynamically resolved on demand and cached in `MugenDefaultsContainer` to eliminate memory allocations during recompositions.

---

## Module Layout

```text
Mugen/
├── mugen-core/                # Core Multiplatform Library (Tokens, Contracts, Components, PlainLook)
├── mugen-look-haze/           # Glassmorphic Haze Look & RendererSet (HazeTheme)
├── mugen-look-material/       # Material-3 Themed Look & RendererSet (MugenMaterialTheme)
├── samples/
│   └── mugen-playground/     # Multiplatform Showcase (Android, Desktop, Web JS/Wasm, iOS)
├── iosApp/                    # Xcode iOS Application
└── build-logic/               # Convention Plugins (mugen.cmp.library, mugen.cmp.application)
```

---

## Installation

Mugen is published to Maven Central. Ensure `mavenCentral()` is declared in your repository list (e.g. in `settings.gradle.kts`):

> [!NOTE]
> Look packages (`mugen-look-haze` and `mugen-look-material`) are currently in active development and are not published to Maven Central yet. Only `mugen-core` is available for `0.0.0-indev01`. Publishing for Look packages is planned for `indev02`.

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
```

### 1. Version Catalog (`gradle/libs.versions.toml`)

```toml
[versions]
mugen = "0.0.0-indev01"

[libraries]
mugen-core = { module = "pro.jayeshseth.mugen:mugen-core", version.ref = "mugen" }
# Optional Looks:
mugen-look-haze = { module = "pro.jayeshseth.mugen:mugen-look-haze", version.ref = "mugen" }
mugen-look-material = { module = "pro.jayeshseth.mugen:mugen-look-material", version.ref = "mugen" }
```

### 2. Multiplatform Dependencies (`build.gradle.kts`)

Add `mugen-core` to your module's `commonMain` dependencies:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.mugen.core)

            // Optional Looks:
            // implementation(libs.mugen.look.haze)
            // implementation(libs.mugen.look.material)
        }
    }
}
```

Or using direct coordinate string:

```kotlin
implementation("pro.jayeshseth.mugen:mugen-core:0.0.0-indev01")
```

---

## Quick Start

### 1. Basic Theme Setup

Wrap your application in `MugenTheme`:

```kotlin
// Using the built-in zero-dependency Plain Look:
MugenTheme(
    look = PlainLook(),
    renderers = PlainRenderers
) {
    MugenButton(onClick = { /* ... */ }) {
        MugenText("Hello Mugen")
    }
}
```

### 2. Using Bespoke Looks

Or use pre-bundled convenience themes:

```kotlin
// Signature Glassmorphic Theme:
HazeTheme {
    MugenCard {
        MugenText("Glassmorphic Card")
    }
}

// Material 3 Theme:
MugenMaterialTheme {
    MugenButton(onClick = { /* ... */ }) {
        MugenText("Material Action")
    }
}
```

### 3. Theme Overrides

Override component defaults at the theme level without writing a new Look:

```kotlin
MugenTheme(
    look = PlainLook(),
    overrides = MugenOverrides.build {
        button { it.copy(minHeight = 56.dp) }
        card { it.copy(shape = RoundedCornerShape(24.dp)) }
    }
) {
    // ...
}
```

---

## Building & Running the Playground

### Desktop (JVM)
```bash
./gradlew :samples:mugen-playground:run
```

### Web (WasmJs / JS)
```bash
./gradlew :samples:mugen-playground:wasmJsBrowserDevelopmentRun
# or
./gradlew :samples:mugen-playground:jsBrowserDevelopmentRun
```

### Android
```bash
./gradlew :samples:mugen-playground:assembleDebug
```

### iOS Framework
```bash
./gradlew :samples:mugen-playground:compileKotlinIosSimulatorArm64
```