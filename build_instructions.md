## Build Instructions:

- You'll need:
1. IntelliJ IDEA
2. [GregTech](https://github.com/GregTech-Intergalactical/GregTech) + [AntimatterAPI](https://github.com/GregTech-Intergalactical/AntimatterAPI) + [TesseractAPI](https://github.com/GregTech-Intergalactical/TesseractAPI) from the [GregTech-Intergalactical organization](https://github.com/GregTech-Intergalactical)

- Make 3 folders in the same directory (case sensitive on linux/osx, case insensitive on windows) ->

![Folders](https://i.imgur.com/VSd2pfR.png)

- Place the repository contents in their respective folders

- Import `build.gradle` in the GregTech folder with IntelliJ (or File -> New -> Project from Existing Sources), then run the `genIntellijRuns` gradle task to generate run configurations.

- Run `build.antimatterapi runDataIdea` in IntelliJ, setting `Use classpath of module` to `gregtech.antimatterapi.main`

- Run `runDataIdea` to generate GregTech item/block .json files

- Run `runClientIdea` to start game in IntelliJ
