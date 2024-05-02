plugins {
    id("rhcehd123.android.library")
}

android {
    namespace = "dev.rhcehd123.core.model"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

}

dependencies {

}