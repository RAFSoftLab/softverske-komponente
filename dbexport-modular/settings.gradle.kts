plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "dbexport-modular"
include("spec")
include("csv")
include("pdf")
include("app")
