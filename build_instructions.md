## Build Instructions:

- You'll need:
1. IntelliJ IDEA
2. [GregTech](https://github.com/GregTech-Intergalactical/GregTech) 

- Run git clone --recurse-submodules https://github.com/GregTech-Intergalactical/GregTech

- Run ./gradlew genIntellijRuns in GregTech root.

- Import build into i.e. Idea.

- Ensure project target is Java 8.

- In Build, Execution, Deployment (under Settings), set Build and run using: Intellij IDEA under Build Tools.
    This step is crucial to allow hotswapping.
  
- Uncomment remap = false in all mixins.
  
- Run antimatter:runData run configuration(you might have to select antimatter.main as module)

- Run runData.

- Run runClient to run GregTech.

