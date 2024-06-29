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

    public static final RegistryKey<PointOfInterestType> BLOCK_BSFURNACE_KEY = point("bs_furnace");
    public static final PointOfInterestType BLOCK_BSFURNACE = registerPointOfInterestType("bs_furnace", ModBlocks.BLOCK_BSFURNACE);
    public static final VillagerProfession BLOCK_BSFURNACE_MASTER = registerVillagerProfession("bs_furnace_master", BLOCK_BSFURNACE_KEY);

    private static VillagerProfession registerVillagerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(BetterSynthesis.MOD_ID, name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));
    }

    private static PointOfInterestType registerPointOfInterestType(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(BetterSynthesis.MOD_ID, name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> point(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(BetterSynthesis.MOD_ID, name));
    }

    public static void registerVillagers() {

    }


}
