package muramasa.gregtech.integration.forge.tfc.ore;

import muramasa.antimatter.Ref;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.gregtech.GTIRef;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

public class GTTFCOreBlock extends BlockBasic {
    final Rock rock;
    final Material material;
    final Ore.Grade grade;
    public GTTFCOreBlock(String domain, Material material, Rock rock) {
        super(domain, rock.name().toLowerCase() + "_" + material.getId(), Properties.of(net.minecraft.world.level.material.Material.STONE, MaterialColor.STONE).sound(SoundType.STONE).strength(rock.category().hardness(6.5F), 10.0F).requiresCorrectToolForDrops());
        this.rock = rock;
        this.material = material;
        this.grade = null;
    }
    public GTTFCOreBlock(String domain, Material material, Rock rock, Ore.Grade grade) {
        super(domain, grade.name().toLowerCase() + "_" + rock.name().toLowerCase() + "_" + material.getId(), Properties.of(net.minecraft.world.level.material.Material.STONE, MaterialColor.STONE).sound(SoundType.STONE).strength(rock.category().hardness(6.5F), 10.0F).requiresCorrectToolForDrops());
        this.rock = rock;
        this.material = material;
        this.grade = grade;
    }

    @Override
    public Texture[] getTextures() {
        String prefix = grade == null ? "" : grade.name().toLowerCase() + "_";
        return new Texture[]{new Texture(Ref.MOD_TFC, "block/rock/raw/" + rock.name().toLowerCase()), new Texture(GTIRef.ID, "block/tfc/ore/" + prefix + material.getId())};
    }
}
