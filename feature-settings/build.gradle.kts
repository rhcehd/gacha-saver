plugins {
    id("rhcehd123.android.library")
    id("rhcehd123.android.library.compose")
    id("rhcehd123.android.feature")
    //id("rhcehd123.android.hilt")
}

android {
    namespace = "dev.rhcehd123.feature.setting"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

}

dependencies {

    implementation(project(":core-data"))
    implementation(project(":core-model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.hilt.navigation.compose)

    /*implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)*/
}