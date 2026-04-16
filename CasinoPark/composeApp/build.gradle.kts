import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //Serialització ((CAl activar el plugin a l'altre gradle)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
            //Navegació
            implementation("androidx.navigation:navigation-compose:2.9.7")
            //Biblioteca extesa d'icones
            implementation ("androidx.compose.material:material-icons-extended:1.7.8")
            //DataStore
            implementation ("androidx.datastore:datastore-preferences:1.2.1")
            //Lifecycle
            implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")

            //Retrofit
            implementation ("com.squareup.retrofit2:retrofit:3.0.0")

            //Scalar converter de Retrofit
            implementation ("com.squareup.retrofit2:converter-scalars:3.0.0")

            //Gson converter de Retrofit
            implementation ("com.squareup.retrofit2:converter-gson:3.0.0")


            //Convertidors moshi de retrofit
            implementation ("com.squareup.moshi:moshi:1.15.2")
            implementation ("com.squareup.retrofit2:converter-moshi:3.0.0")

            implementation ("com.squareup.okhttp3:logging-interceptor:5.3.2")


            //Coil  (Per a carregar imatges d'internet
            implementation ("io.coil-kt:coil-compose:2.7.0")

            //Constraint layout per a Compose
            implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}


compose.desktop {
    application {
        mainClass = "com.tomasbusfri.files.casinopark.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.tomasbusfri.files.casinopark"
            packageVersion = "1.0.0"
        }
    }
}
