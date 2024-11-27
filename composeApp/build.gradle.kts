import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsCompose)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.jetbrains.kotlin.serialization)
	alias(libs.plugins.ksp)
	alias(libs.plugins.room)
}

kotlin {
	androidTarget {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_11)
		}
	}

	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "ComposeApp"
			isStatic = true
		}
	}

	jvm("desktop")

	room {
		schemaDirectory("$projectDir/schemas")
	}

	sourceSets {
		val desktopMain by getting

		androidMain.dependencies {
			implementation(compose.preview)
			implementation(libs.androidx.activity.compose)

			implementation(libs.koin.android)
			implementation(libs.koin.androidx.compose)
			implementation(libs.ktor.client.okhttp)
		}
		commonMain.dependencies {
			implementation(compose.runtime)
			implementation(compose.foundation)
			implementation(compose.material3)
			implementation(compose.ui)
			implementation(compose.components.resources)
			implementation(compose.components.uiToolingPreview)
			implementation(libs.androidx.lifecycle.viewmodel)
			implementation(libs.androidx.lifecycle.runtime.compose)
			implementation(compose.materialIconsExtended)

			implementation(libs.androidx.lifecycle.viewmodel)
			implementation(libs.androidx.lifecycle.runtime.compose)
			implementation(libs.jetbrains.compose.navigation)
			implementation(libs.kotlinx.serialization.json)
			implementation(libs.androidx.room.runtime)
			implementation(libs.sqlite.bundled)
			implementation(libs.koin.compose)
			implementation(libs.koin.compose.viewmodel)
			api(libs.koin.core)

			implementation(libs.bundles.ktor)
			implementation(libs.bundles.coil)
		}
		desktopMain.dependencies {
			implementation(compose.desktop.currentOs)
			implementation(libs.kotlinx.coroutines.swing)
			implementation(libs.ktor.client.okhttp)
		}
		nativeMain.dependencies {
			implementation(libs.ktor.client.darwin)
		}

		dependencies {
			ksp(libs.androidx.room.compiler)
		}
	}
}

android {
	namespace = "com.vishal2376.bookie"
	compileSdk = libs.versions.android.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "com.vishal2376.bookie"
		minSdk = libs.versions.android.minSdk.get().toInt()
		targetSdk = libs.versions.android.targetSdk.get().toInt()
		versionCode = 1
		versionName = "1.0"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}

dependencies {
	debugImplementation(compose.uiTooling)
}

compose.desktop {
	application {
		mainClass = "com.vishal2376.bookie.MainKt"

		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "com.vishal2376.bookie"
			packageVersion = "1.0.0"
		}
	}
}
