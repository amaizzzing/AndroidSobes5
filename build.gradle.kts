buildscript {
    val kotlin_version by extra("1.5.31")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.BuildPlugins.androidGradle)
        classpath(Dependencies.BuildPlugins.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}