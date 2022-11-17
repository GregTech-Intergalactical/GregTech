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
* Open folder in your editor, import as a gradle project in Idea.

* ~~Run **Minecraft Client** configuration to run GTI. You might have to set classpath to GregTech.forge.main or GregTech.fabric.main~~ Not currently working, use the runClient gradle configurations for forge and fabric submodules instead for now, or run one of these commands:
  (replace forge with fabric to run fabric side)
* Linux
```console
 ./gradlew :forge:runClient
```

* Windows
```console
 gradlew :forge:runClient
```
