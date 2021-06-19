# CSC4111Project

## Installing and Using Gradle

Follow [the Gradle install guide](https://gradle.org/install/) to install the Gradle build tool and add it to your path.   
All build settings and dependencies will go in the `build.gradle` file at the directory level you want to configure for. 
For example, any global dependencies will go at the package root.   
To build the project, run `gradle build` in the IDE Terminal or with Command Prompt.   

## Installing and Using Git

Hopefully, you already have either the Git Command Line Interface (CLI) or the GitHub desktop app installed, but
if not, the simplest way to do so would be to download the desktop app [here](https://desktop.github.com/).   
To clone this repository, either type `git clone https://github.com/kfaerber13/CSC4111Project.git` from your
command line in the desired local folder or follow the interactive steps in the desktop app.   

### Creating Pull Requests

Following good software development practice, we will be reviewing all code through the use of GitHub's
Pull Requests (you can read more about pull requests [here](https://docs.github.com/en/github/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests). 
To create a pull request, simply use the command `gh pr create` after making your changes or create it in
the desktop app by going to "Branch" > "Create pull request" in the top navigation bar. If you get stuck or
just want more information about creating PRs, start by going to [this doc site](https://docs.github.com/en/github/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request).