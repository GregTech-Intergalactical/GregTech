package muramasa.gregtech.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.block.BlockCasing;
import net.minecraft.client.renderer.RenderType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientHandler {

    public static void setup() {
        AntimatterAPI.all(BlockCasing.class, t -> ModelUtils.setRenderLayer(t, RenderType.cutout()));
        copyProgrammerArtIfMissing();
    }

    private static void copyProgrammerArtIfMissing() {
        writeResourcePack("GregTech-Old-Machine-Textures", "gt5u-machine-base");
        writeResourcePack("GregTech-New-Machine-Textures", "new-machine-base");
        writeResourcePack("New-Stone-Textures", "new-stone-textures");
        writeResourcePack("Former-Gui-Textures", "new-gui-textures");
    }

    private static void writeResourcePack(String writeName, String readName){
        File dir = new File(AntimatterPlatformUtils.getConfigDir().getParent().toFile(), "resourcepacks");
        File target = new File(dir, writeName + ".zip");


        //if(!target.exists())
        try {
            dir.mkdirs();
            InputStream in = GregTech.class.getResourceAsStream("/assets/" + GTIRef.ID + "/" + readName + ".zip");
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
