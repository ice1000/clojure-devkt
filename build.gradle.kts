import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.*

val kotlinVersion = "1.2.40"

group = "org.intellij.clojure.devkt"
version = "v1.0"

plugins {
  java
  kotlin("jvm") version "1.2.40"
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

java.sourceSets {
  "main" {
    java.setSrcDirs(listOf("src"))
    withConvention(KotlinSourceSet::class) {
      kotlin.setSrcDirs(listOf("src"))
    }
    resources.setSrcDirs(listOf("res"))
  }

  "test" {
    java.setSrcDirs(emptyList<Any>())
    withConvention(KotlinSourceSet::class) {
      kotlin.setSrcDirs(emptyList<Any>())
    }
    resources.setSrcDirs(emptyList<Any>())
  }
}

repositories {
  mavenCentral()
  jcenter()
  maven("https://jitpack.io")
}

dependencies {
  compileOnly(kotlin("compiler-embeddable", kotlinVersion))
  compileOnly(files(*File("lib").listFiles()))
}

