package muramasa.gregtech.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.GregTech;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ServerHandler {
    public static void setup(){
        if (AntimatterPlatformUtils.isForge()){
            copyGTRubberJarIfMissing();
        }
    }

    private static void copyGTRubberJarIfMissing() {
        File dir = new File(AntimatterPlatformUtils.getConfigDir().getParent().toFile(), "mods");
        File target = new File(dir, "gtrubber-forge-0.2-1.18.2.jar");


        //if(!target.exists())
        try {
            dir.mkdirs();
            InputStream in = GregTech.class.getResourceAsStream("/META-INF/jars/gtrubber-forge-0.2-1.18.2.jar");
            FileOutputStream out = new FileOutputStream(target);

            byte[] buf = new byte[16384];
            int len = 0;
            while((len = in.read(buf)) > 0)
                out.write(buf, 0, len);

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
