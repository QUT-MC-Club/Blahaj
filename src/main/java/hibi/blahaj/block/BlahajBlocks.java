package hibi.blahaj.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.block.*;
import net.minecraft.client.render.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

import static hibi.blahaj.Blahaj.*;

public class BlahajBlocks {

	public static final Identifier GRAY_SHARK_ID = Identifier.of(MOD_ID, "gray_shark");
	public static final Identifier BLAHAJ_ID = Identifier.of(MOD_ID, "blue_shark");
	public static final Identifier BLAVINGAD_ID = Identifier.of(MOD_ID, "blue_whale");
	public static final Identifier BREAD_ID = Identifier.of(MOD_ID, "bread");

	public static Block GRAY_SHARK_BLOCK;
	public static Item GRAY_SHARK_ITEM;
	public static Block BLAHAJ_BLOCK;
	public static Item BLAHAJ_ITEM;
	public static Block BLAVINGAD_BLOCK;
	public static Item BLAVINGAD_ITEM;
	public static Block BREAD_BLOCK;
	public static Item BREAD_ITEM;

	public static void register() {
		GRAY_SHARK_BLOCK = Registry.register(Registries.BLOCK, GRAY_SHARK_ID, new CuddlyBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_WOOL)));
		BLAHAJ_BLOCK = Registry.register(Registries.BLOCK, BLAHAJ_ID, new CuddlyBlock(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL)));
		BLAVINGAD_BLOCK = Registry.register(Registries.BLOCK, BLAVINGAD_ID, new CuddlyBlock(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL)));
		BREAD_BLOCK = Registry.register(Registries.BLOCK, BREAD_ID, new CuddlyBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL)));

		GRAY_SHARK_ITEM = Registry.register(Registries.ITEM, GRAY_SHARK_ID, new CuddlyItem(GRAY_SHARK_BLOCK, new Item.Settings().maxCount(1).attributeModifiers(CuddlyItem.createAttributeModifiers()), "block.blahaj.gray_shark.tooltip"));
		BLAHAJ_ITEM = Registry.register(Registries.ITEM, BLAHAJ_ID, new CuddlyItem(BLAHAJ_BLOCK, new Item.Settings().maxCount(1).attributeModifiers(CuddlyItem.createAttributeModifiers()), "block.blahaj.blue_shark.tooltip"));
		BLAVINGAD_ITEM = Registry.register(Registries.ITEM, BLAVINGAD_ID, new CuddlyItem(BLAVINGAD_BLOCK, new Item.Settings().maxCount(1).attributeModifiers(CuddlyItem.createAttributeModifiers()), "block.blahaj.blue_whale.tooltip"));
		BREAD_ITEM = Registry.register(Registries.ITEM, BREAD_ID, new CuddlyItem(BREAD_BLOCK, new Item.Settings().maxCount(1).attributeModifiers(CuddlyItem.createAttributeModifiers()), null));

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.add(new ItemStack(GRAY_SHARK_ITEM));
			entries.add(new ItemStack(BLAHAJ_ITEM));
			entries.add(new ItemStack(BLAVINGAD_ITEM));
			entries.add(new ItemStack(BREAD_ITEM));
		});
	}

	public static void registerClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), GRAY_SHARK_BLOCK, BLAHAJ_BLOCK, BLAVINGAD_BLOCK, BREAD_BLOCK);
	}

}
