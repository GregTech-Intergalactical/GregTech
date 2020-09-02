## Build Instructions:

- You'll need:
1. IntelliJ IDEA
2. [GregTech](https://github.com/GregTech-Intergalactical/GregTech) 

- Run git clone --recurse-submodules https://github.com/GregTech-Intergalactical/GregTech

- Run ./gradlew genIntellijRuns in GregTech root.

- Run ./gradlew :antimatter:runData in GregTech root.

- Run ./gradlew runData in GregTech root.

- Import build into i.e. Idea.

- In Edit configurations, press (+) and select Gradle. For project, select GregTech and for task, select runClient.

- Run the given task configuration.

