# HackerNewsReader

This sample project is to demostrate an android app built for https://github.com/HackerNews/API. It has 2 pages, top stories and comment. 

## Objective
The objective of the project is to demostrate:
- implementation of MVP
- unit test 
- android test
- unified code coverage report using jacoco

In order to run testing and generate coverage report, switch to mockDebug build variant and hit the following command in Android Terminal.

`gradlew clean jacocoTestReport`

Coverage report is located at \app\build\reports\jacoco\jacocoTestReport\html\index.html

Ensure that you have already turned off device animation before run the android test as documented [here](https://developer.android.com/training/testing/espresso/setup.html#set-up-environment).

## Libraries
Libraries used in this sample project:
- Retrofit & Gson
- Data Binding
- RxJava & RxAndroid
- Dagger
- Mockito
- Robolectric
- Espresso
