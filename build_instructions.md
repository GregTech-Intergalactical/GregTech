<div>
  <img src="https://i.imgur.com/9jkn0cO.png" align="left">
  <img src="https://i.imgur.com/9jkn0cO.png" align="right">
</div>
<h1 align="center">GregTech Intergalactical</h1>

<h1 align="center">Build Instructions</h1>

## Using remote dependencies

If you only plan on developing GTI.

### **You'll need**

* IntelliJ Idea or Visual Studio Code (OSS!)
* A copy of the [GregTech](https://github.com/GregTech-Intergalactical/GregTech)  repository.

### **Steps**

* Run ./gradlew genIntellijRuns (or ./gradlew genVSCodeRuns) in GregTech root.

* Open folder in your editor, import as a gradle project in Idea.

* Ensure project target is Java 8.
* Run **runData** configuration. You might have to set the classpath to GregTech.main.
* Run **runClient** configuration to run GTI.

## Using local dependencies.

  * IntelliJ Idea or Visual Studio Code (OSS!)
* A copy of the [GregTech](https://github.com/GregTech-Intergalactical/GregTech)  repository.
* A copy of the [AntimatterAPI](https://github.com/GregTech-Intergalactical/AntimatterAPI)  repository as subfolder AntimatterAPI in the GregTech folder.
* (Optional) A copy of the [TesseractAPI](https://github.com/GregTech-Intergalactical/TesseractAPI)  repository as a subfolder in the AntimatterAPI folder.

* Open folder in your editor, import as a gradle project in Idea.

* Ensure project target is Java 8.
* Run **antimatter:runData** configuration. You might have to set the classpath to GregTech.antimatter.main.
* Run **runData** configuration. You might have to set the classpath to GregTech.main.
* Run **runClient** configuration to run GTI.

## Issues

### **Local dependencies**

* If Build, Execution and Deployment -> Gradle is set to Idea instead of Gradle you will have to uncomment remap != false in all Antimatter mixins for the project to compile.
  
* Should Idea tell you that all local packages are missing, press File -> Invalidate caches and restart.

### **Remote dependencies**

* Antimatter is missing item models for some reason.
  
### **Both**

* It is recommended to use DCEVM for hotswapping. For Linux (Arch) installing **jdk8-openjdk-dcevm** package and setting it as JDK is enough. It allows immediate hotswapping. Otherwise hotswapping code can cause issues.
* Hotswapping Mixin code in Antimatter is not supported.
