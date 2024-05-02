import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName

plugins {
    id("rhcehd123.android.library")
    id("rhcehd123.android.hilt")
}

android {
    namespace = "dev.rhcehd123.core.datastore"

    /*defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }*/

}

dependencies {

    /*implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)*/

    implementation(project(":core-common"))
    implementation(project(":core-datastore-proto"))
    implementation(project(":core-model"))

    implementation(libs.androidx.datastore)
}
