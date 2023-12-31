# Teamix
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Teamix is a messaging and collaboration platform designed for teams and organizations to streamline communication and collaboration.
With Teamix, you can create organizations, engage in discussions through channels and topics, and enjoy a range of features to enhance teamwork.

## Key Features
### 1. Organization Management:
- Create and join organizations to collaborate with team members effortlessly.
- You can create your own organization and add members to it.
### 2. Channels and Topics:
- Engage in meaningful discussions through channels and topics.
### 3. Search Functionality:
- Members can search for their channels.
### 4. Direct Messaging:
- Foster collaboration through private messaging between team members.
### 5. Member Profiles:
- Members can see their names, profile pictures, and Email address.
- Members can change their names and profile pictures.
### 6. Theming Options:
- Choose between dark and light themes for a visually appealing experience.
### 7. Multilingual Support:
- Support for English, Arabic, Chinese, and more.
### 8. Saved Later:
- Members can add topics and messages to the saved later.

## Videos
**Getting Started with Teamix:**

https://github.com/team-chocolate-cake/Teamix/assets/83548062/aaac91af-1e1a-4c98-b7af-4227f430b9ce

https://github.com/team-chocolate-cake/Teamix/assets/83548062/0f7a862c-af87-4f7b-999b-0180bca02159

https://github.com/team-chocolate-cake/Teamix/assets/83548062/1ea06670-de3c-4ebc-90b4-47a9a112e027

## Architecture
Teamix follows the clean architecture approach for scalability and maintainability.
The app consists of 5 Modules which are app, buildScr, data, domain and presentation.

**I. Presentation:** It contains 2 modules which are viewmodel and ui.
  - **1. ui:** It contains the composables, routes and the UI built in compose.
     It is responsible for showing data.
  - **2. viewModel:** It contains the ViewModels, UiStates, screen interactions, and effects and mappers.
    It is responsible for state management.
    
**II. Domain:** It contains 2 modules which are entities and usecases.
  - **1. entities:** It contains the domain models.
  - **2. usecases:** It contains the business logic of the application.
    
**III. data:** It contains 3 modules which are local, remote and repository.
  - **1. local:** It contains the local data source implementation.
  - **2. remote:** It contains the remote data source implementation.
  - **3. repository:** It contains the repository implementation, DTOs, data sources and mappers.
    
**IV. buildScr:** It contains the libraries and configurations that will be called in gradle files.

**V. app:** It contains the app and the dependency injection.

Finally, this exceptional app applies the MVVM (Model-View-ViewModel), leveraging Hilt Dagger for dependency injection.
Additionally, it utilizes Firebase Cloud Firestore as the robust backend infrastructure,
Jetpack Compose for creating stunning user interfaces, and Kotlin Coroutines for efficient concurrency handling.

## Getting Started
To get started with this project, follow these steps:

1. Clone the repository to your local machine.
  
3. Open the project in Android Studio.

5. Connect your Firebase project by adding the `google-services.json` file to the app module and the remote module. This file contains your Firebase project configurations.

7. Build and run the application on an emulator or a physical device.

## Contributors

<a href="https://github.com/team-chocolate-cake/Teamix/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=team-chocolate-cake/Teamix" />
</a>
<br>
<br>

## Download the App
You can download the latest version of our app here:
- [Download the App](https://github.com/team-chocolate-cake/Teamix/releases/tag/v1.0.0)
  
## License
Teamix is open source and released under the [MIT License](LICENSE).
