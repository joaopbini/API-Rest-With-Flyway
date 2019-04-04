## Heroku Deploy Preparing a Spring Boot app for Heroku
Before you can deploy the app to Heroku, you’ll need to create a Git repository for the application and add all of the code to it by running these commands

> git init

> git add .

> git commit -m "text msg"

In order to deploy to Heroku, you’ll first need to provision a new Heroku app. Run this command:

> heroku create

> heroku apps:rename newName

Now deploy your code:
> git push heroku master

All that said, the application is now deployed. You can visit the app’s URL by running this command:
> heroku open
