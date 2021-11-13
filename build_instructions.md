<div>
  <img src="https://i.imgur.com/9jkn0cO.png" align="left">
  <img src="https://i.imgur.com/9jkn0cO.png" align="right">
</div>
<h1 align="center">GregTech Intergalactical</h1>

<h1 align="center">Build Instructions</h1>

[comment]: <> (## Using remote dependencies)

[comment]: <> (If you only plan on developing GTI.)

[comment]: <> (### **You'll need**)

[comment]: <> (* IntelliJ Idea or Visual Studio Code &#40;OSS!&#41;)

[comment]: <> (* A copy of the [GregTech]&#40;https://github.com/GregTech-Intergalactical/GregTech&#41;  repository.)

[comment]: <> (### **Steps**)

[comment]: <> (* Run ./gradlew genIntellijRuns &#40;or ./gradlew genVSCodeRuns&#41; in GregTech root.)

[comment]: <> (* Open folder in your editor, import as a gradle project in Idea.)

[comment]: <> (* Ensure project target is Java 8.)

[comment]: <> (* Run **runData** configuration. You might have to set the classpath to GregTech.main.)

[comment]: <> (* Run **runClient** configuration to run GTI.)

[comment]: <> (## Using local dependencies.)

  * IntelliJ Idea (2020.3!) or Visual Studio Code/Neovim
  * GregTech and AntimatterAPI
```console
   git clone --recurse-submodules https://github.com/GregTech-Intergalactical/GregTech
```
* For Linux run
 ```console
   ./gradlew genIntellijRuns
```
* For Windows run
 ```console
   gradlew genIntellijRuns
```
* Open folder in your editor, import as a gradle project in Idea.

* Run **runClient** configuration to run GTI. You might have to set classpath to GregTech.main

### **Enabling hotswap**

* JDTLS-based editors work out of the box.
* For Intellij, follow <http://hotswapagent.org/> instructions to install DCEVM and hotswap agent. Set 
  build type in Build, Execution and Deployment -> Gradle to IDEA. 
* It is recommended to use DCEVM for hotswapping. For Linux (Arch) installing **jdk8-openjdk-dcevm** package and setting it as JDK is enough. It allows immediate hotswapping. Otherwise hotswapping code can cause issues.
* Hotswapping Mixin code in Antimatter is not supported.

## Issues

* If Build, Execution and Deployment -> Gradle is set to Idea instead of Gradle you will have to uncomment remap != false in all Antimatter mixins for the project to compile.
  By searching for "/\*remap = false,\*/" and replacing with "remap = false," it should fix all Mixin issues.
* Should Idea tell you that local packages are missing, press File -> Invalidate caches and restart.

  
### **Both**

* It is recommended to use DCEVM for hotswapping. For Linux (Arch) installing **jdk8-openjdk-dcevm** package and setting it as JDK is enough. It allows immediate hotswapping. Otherwise hotswapping code can cause issues.
* Hotswapping Mixin code in Antimatter is not supported.
