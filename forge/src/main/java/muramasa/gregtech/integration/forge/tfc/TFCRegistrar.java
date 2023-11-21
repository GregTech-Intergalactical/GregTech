package muramasa.gregtech.integration.forge.tfc;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeFluid;
import muramasa.antimatter.material.TextureSet;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.integration.forge.tfc.datagen.TFCBlockTagProvider;
import muramasa.gregtech.integration.forge.tfc.datagen.TFCItemTagProvider;
import muramasa.gregtech.integration.forge.tfc.datagen.TFCLangProvider;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tesseract.FluidPlatformUtils;

import java.util.function.BiConsumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.gregtech.data.Materials.*;

public class TFCRegistrar implements IAntimatterRegistrar {

    public static Material[] array;
    public TFCRegistrar(){
        onRegistrarInit();
    }
    @Override
    public String getId() {
        return Ref.MOD_TFC;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_INIT){
            array = new Material[]{Bauxite, Cobaltite, Galena, Uraninite, VanadiumMagnetite, BrownLimonite, Hematite, Sheldonite, Palladium};
            /*for (Material material : array) {
                Helpers.mapOfKeys(Ore.Grade.class, (grade) -> {
                    new GTTFCOreItem(GTIRef.ID, grade.name().toLowerCase() + "_" + material.getId());
                    return Helpers.mapOfKeys(Rock.class, (rock) -> {
                        new GTTFCOreBlock(GTIRef.ID, material, rock, grade);
                        return true;
                    });
                });
            }*/
            Helpers.mapOfKeys(Rock.class, (rock) -> {
                Material material = Material.get(rock.name().toLowerCase());
                if (material == Material.NULL){
                    material = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, rock.name().toLowerCase(), rock.color().col, TextureSet.NONE));
                    material.flags(DUST);
                }
                AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, "raw_" + rock.name().toLowerCase(), material, new Texture(Ref.MOD_TFC, "block/rock/raw/" + rock.name().toLowerCase()), SoundType.STONE, false).setStateSupplier(() -> rock.getBlock(Rock.BlockType.RAW).get().defaultBlockState()).setHardnessAndResistance(rock.category().hardness(6.5F), 10.0F).setHarvestLevel(1));
                AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, rock.name().toLowerCase() + "_gravel", material, new Texture(Ref.MOD_TFC, "block/rock/gravel/" + rock.name().toLowerCase()), SoundType.GRAVEL, false).setSandLike(true).setHardnessAndResistance(rock.category().hardness(2.0F)).setStateSupplier(() -> rock.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState()).setHarvestLevel(1).setRequiresTool(true));
                return true;
            });
            Helpers.mapOfKeys(SandBlockType.class, (sand) -> {
                Material material = Material.get(sand.name().toLowerCase() + "_sand");
                if (material == Material.NULL){
                    material = AntimatterAPI.register(Material.class, new Material(GTIRef.ID, sand.name().toLowerCase() + "_sand", sand.getDustColor(), TextureSet.NONE));
                    material.flags(DUST);
                }
                AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, sand.name().toLowerCase() + "_sand", material, new Texture(Ref.MOD_TFC,"block/sand/" + sand.name().toLowerCase()), SoundType.SAND, false)).setSandLike(true).setRequiresTool(true).setFallingDustColor(sand.getDustColor()).setStateSupplier(() -> AntimatterPlatformUtils.getBlockFromId(Ref.MOD_TFC, "sand/" + sand.name().toLowerCase()).defaultBlockState());
                AntimatterAPI.register(StoneType.class, new StoneType(GTIRef.ID, sand.name().toLowerCase() + "_raw_sandstone", material, new Texture(Ref.MOD_TFC, "block/sandstone/bottom/" + sand.name().toLowerCase()), SoundType.SAND, false).setStateSupplier(() -> AntimatterPlatformUtils.getBlockFromId(Ref.MOD_TFC, "raw_sandstone/" + sand.name().toLowerCase()).defaultBlockState()));
                return true;
            });
            AntimatterMaterialTypes.LIQUID.set((m, i) -> {
                if (m == null || !AntimatterMaterialTypes.LIQUID.allowGen(m)) return MaterialTypeFluid.getEmptyFluidAndLog(AntimatterMaterialTypes.LIQUID, m);
                if (m.getId().equals("water")) return FluidPlatformUtils.createFluidStack(Fluids.WATER, i);
                else if (m.getId().equals("lava")) return FluidPlatformUtils.createFluidStack(Fluids.LAVA, i);
                else if (m == SaltWater) return FluidPlatformUtils.createFluidStack(TFCFluids.SALT_WATER.getSource(), i);
                AntimatterFluid fluid = AntimatterAPI.get(AntimatterFluid.class, AntimatterMaterialTypes.LIQUID.getId() + "_" + m.getId());
                if (fluid == null) throw new IllegalStateException("Tried to get null fluid");
                return FluidPlatformUtils.createFluidStack(fluid.getFluid(), i);
            });
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
        if (isEnabled()) {
            FMLJavaModLoadingContext.get().getModEventBus().register(this);
            MinecraftForge.EVENT_BUS.addListener(this::registerRecipeLoaders);
            AntimatterDynamics.clientProvider(GTIRef.ID, () -> new TFCLangProvider(GTIRef.ID, "TFC en_us Lang", "en_us"));
        }
    }

    public void registerRecipeLoaders(AntimatterLoaderEvent event){
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> event.registrat.add(GTIRef.ID, a, b);
        loader.accept("tfc_machine_recipes", MachineRecipes::init);
    }

    @SubscribeEvent
    public void onProviders(AntimatterProvidersEvent ev) {
        ev.event.addProvider(Ref.MOD_TFC, () -> new AntimatterFluidTagProvider(Ref.MOD_TFC, "TFC Fluid Tags", false){
            @Override
            protected void processTags(String domain) {
                super.processTags(domain);
                this.tag(TagUtils.getForgelikeFluidTag("salt_water")).add(TFCFluids.SALT_WATER.getSource());
            }
        });
        AntimatterBlockTagProvider[] blockTagProviders = new AntimatterBlockTagProvider[1];
        blockTagProviders[0] = new TFCBlockTagProvider( Ref.MOD_TFC, "TFC Block Tags", false);
        ev.event.addProvider(Ref.MOD_TFC, () -> new TFCItemTagProvider(Ref.MOD_TFC, "TFC Item Tags", false,  blockTagProviders[0]));
        ev.event.addProvider(Ref.MOD_TFC, () -> blockTagProviders[0]);

    }

    @Override
    public boolean isEnabled() {
        return AntimatterAPI.isModLoaded(Ref.MOD_TFC);
    }
}
