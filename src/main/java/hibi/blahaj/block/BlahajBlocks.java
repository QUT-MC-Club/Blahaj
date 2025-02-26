package hibi.blahaj.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.block.*;
import net.minecraft.client.render.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import eu.pb4.polymer.core.api.item.*;
import eu.pb4.polymer.core.api.block.*;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.*;
import net.minecraft.util.*;
import xyz.nucleoid.packettweaker.PacketContext;
import java.util.*;

import static hibi.blahaj.Blahaj.*;

public class BlahajBlocks {
	
	public static final Identifier GRAY_SHARK_ID = Identifier.of(MOD_ID, "gray_shark");
	public static final Identifier BLAHAJ_ID = Identifier.of(MOD_ID, "blue_shark");
	public static final Identifier BLAVINGAD_ID = Identifier.of(MOD_ID, "blue_whale");
	public static final Identifier BREAD_ID = Identifier.of(MOD_ID, "bread");
	public static final Identifier BROWN_BEAR_ID = Identifier.of(MOD_ID, "brown_bear");

	public static Block GRAY_SHARK_BLOCK;
	public static Block BLAHAJ_BLOCK;
	public static Block BLAVINGAD_BLOCK;
	public static Block BREAD_BLOCK;
	public static Block BROWN_BEAR_BLOCK;

	public static final List<String> PRIDE_NAMES = List.of(
		"ace", "agender", "aro", "aroace", "bi", "demiboy", "demigirl",
		"demi_r", "demi_s", "enby", "gay", "genderfluid", "genderqueer", "greyrose",
		"grey_r", "grey_s", "intersex", "lesbian", "pan", "poly", "pride", "trans");

	public static List<Block> BLOCKS = new ArrayList<>();
	public static List<Item> ITEMS = new ArrayList<>();

	public static final ItemGroup ITEM_GROUP = PolymerItemGroupUtils.builder()
            .displayName(Text.of("Blåhaj"))
            .icon(Items.COD::getDefaultStack).entries((context, entries) -> {
                for (Item item : ITEMS) {
					entries.add(new ItemStack(item));
				}
            }).build();

	public static void register() {

		GRAY_SHARK_BLOCK = registerCuddlyBlockAndItem(GRAY_SHARK_ID, "block.blahaj.gray_shark.tooltip");
		BLAHAJ_BLOCK = registerCuddlyBlockAndItem(BLAHAJ_ID, "block.blahaj.blue_shark.tooltip");
		BLAVINGAD_BLOCK = registerCuddlyBlockAndItem(BLAVINGAD_ID, "block.blahaj.blue_whale.tooltip");
		BREAD_BLOCK = registerCuddlyBlockAndItem(BREAD_ID, null);
		BROWN_BEAR_BLOCK = registerCuddlyBlockAndItem(BROWN_BEAR_ID, "block.blahaj.brown_bear.tooltip");

		for (String name : PRIDE_NAMES) {
			Identifier id = Identifier.of(MOD_ID, name + "_shark");
			registerCuddlyBlockAndItem(id, "block.blahaj.blue_shark.tooltip");
		}
		PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(MOD_ID, "item_group"), ITEM_GROUP);
	}

	public static Block registerCuddlyBlockAndItem(Identifier id, String tooltip) {
		
		RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
		Block block = Registry.register(Registries.BLOCK, id, new CuddlyBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).registryKey(blockKey)));
		Item item = Registry.register(Registries.ITEM, id, new CuddlyItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey().maxCount(1).attributeModifiers(CuddlyItem.createAttributeModifiers()).equipmentSlot((entity, stack) -> EquipmentSlot.HEAD), tooltip));

		BLOCKS.add(block);
		ITEMS.add(item);
		return block;
	}
}
