# Movify

Demo app how to use Android Networking and incorporate modern libraries

## Introduction

  This project will follow a ViewModel approach to handle the data synchronization by repository which executed asynchronously by Kotlin Coroutines

## Libraries used

* [Moshi](http://square.github.io/moshi/1.x/moshi/) - A modern JSON library for Kotlin and Java
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
* [Okhttp](http://square.github.io/okhttp/) - An HTTP client for Android, Kotlin, and Java 
* [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern 
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) -  A component that helps to navigate between fragments and activities 
```
    //Moshi
    implementation "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    kapt "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    //Retrofit2
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofit2_version"
    implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    //Okhttp3
    implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"
    implementation 'com.squareup.okhttp3:logging-interceptor:3.11.0'

    //Picasso for Image Loading
    implementation("com.squareup.picasso:picasso:$picassoVersion") {
        exclude group: "com.android.support"
    }

    //Kotlin Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutineVersion"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion"

    implementation 'android.arch.lifecycle:extensions:1.1.1'

    //ui
    implementation 'androidx.recyclerview:recyclerview:1.1.0-beta03'
    implementation 'androidx.cardview:cardview:1.0.0'

    // Navigation Graph
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
```

### Api Reference
You have to create and put your api key into local.properties in the root of the project
```
tmdb_api_key = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
```
https://www.themoviedb.org/documentation/api


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

