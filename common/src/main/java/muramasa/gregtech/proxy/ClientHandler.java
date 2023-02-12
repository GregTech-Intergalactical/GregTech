package muramasa.gregtech.proxy;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.gregtech.GregTech;
import muramasa.gregtech.block.BlockCasing;
import muramasa.gregtech.data.GregTechData;
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
        File dir = new File(".", "resourcepacks");
        File target = new File(dir, "GregTech-Old-Machine-Textures.zip");


        //if(!target.exists())
        try {
            dir.mkdirs();
            InputStream in = GregTech.class.getResourceAsStream("/assets/gregtech/gt5u-machine-base.zip");
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
        target = new File(dir, "GregTech-New-Machine-Textures.zip");


        //if(!target.exists())
        try {
            dir.mkdirs();
            InputStream in = GregTech.class.getResourceAsStream("/assets/gregtech/new-machine-base.zip");
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
