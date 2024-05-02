plugins {
    id("rhcehd123.android.library")
    id("rhcehd123.android.hilt")
}

android {
    namespace = "dev.rhcehd123.core.data"

    /*defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }*/

}

dependencies {

    implementation(project(":core-model"))
    implementation(project(":core-datastore"))
    implementation(project(":core-network"))
    /*implementation("androidx.core:core-ktx:1.12.0")
       implementation("androidx.appcompat:appcompat:1.6.1")
       implementation("com.google.android.material:material:1.11.0")
       testImplementation("junit:junit:4.13.2")
       androidTestImplementation("androidx.test.ext:junit:1.1.5")
       androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")*/

    //implementation(libs.androidx.core.ktx)
}