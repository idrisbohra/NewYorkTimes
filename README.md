New York Times Articles

New York Times Articles application is used for fetching popular articles from the New York Times Articles.

In the following project, I used the below-mentioned techniques.

•	Kotlin(Programming language)
•	MVVM(Architectural Design Pattern)
•	Navigation Graph(Android Architectural Design Components)
•	Dagger Hilt(Android Architectural Design Components)
•	Retrofit
•	Kotlin Flow
•	Coroutines

This application is having 2 screens.

The 1st one is to display the list of Articles.

And clicking on any of them will display the article details on the 2nd screen.

- Technical Requirements:

To fetch the articles requires the API_KEY provided by https://developer.nytimes.com/.

Developer has to create an account and once the account is created, developer has to APIs section and needs to create an app.

Once the app is created, developer has to create an API key.

Developer also has to enable the APIs that he/she wants to access by using his generated API key.

- Unit Test Steps:

In order to create out the test, i used mocked objects instead of real things. Mocking is a process of simulating or creating behavior that is not real.

In getting list of articles flow, i first test the ViewModel then the Repository.

•	Test cases for NyTimesViewModel
1. Loading state verification test cases
2. API success state test case
3. API error state test case

To mock NyTimesArticlesRepository, i used Mockito library.
2. API success state test case
3. API error state test case

Summary:
This application uses the latest android architecture components to efficiently fetch and show list of NewYork Times articles 
along with common Unit test cases to test the functionality.

