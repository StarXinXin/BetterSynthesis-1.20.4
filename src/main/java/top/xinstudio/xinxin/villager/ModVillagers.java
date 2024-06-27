package top.xinstudio.xinxin.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import top.xinstudio.xinxin.BetterSynthesis;
import top.xinstudio.xinxin.block.ModBlocks;

public class ModVillagers {
    public static final RegistryKey<PointOfInterestType> Cooking_Stoves_POI_KEY = point("cooking_stoves_poi");
    public static final PointOfInterestType Cooking_Stoves_POI = registerPointOfInterestType("cooking_stoves_poi", ModBlocks.BLOCK_BSFURNACE);
    public static final VillagerProfession Cooking_Stoves_MASTER = registerVillagerProfession("cooking_stoves_master",Cooking_Stoves_POI_KEY);
    private static VillagerProfession registerVillagerProfession(String name,RegistryKey<PointOfInterestType> type){
        return Registry.register(Registries.VILLAGER_PROFESSION,new Identifier(BetterSynthesis.MOD_ID,name),
                new VillagerProfession(name,entry -> entry.matchesKey(type),entry -> entry.matchesKey(type),
                        ImmutableSet.of(),ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));
    }
    private static PointOfInterestType registerPointOfInterestType(String name, Block block){
        return PointOfInterestHelper.register(new Identifier(BetterSynthesis.MOD_ID,name),1,1,block);
    }
    private static RegistryKey<PointOfInterestType> point(String name){
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE,new Identifier(BetterSynthesis.MOD_ID,name));
    }
    public static void registerVillagers(){

    }
}
