package hibi.blahaj.block;

import hibi.blahaj.*;
import net.minecraft.block.*;
import net.minecraft.component.*;
import net.minecraft.component.type.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import eu.pb4.factorytools.api.item.FactoryBlockItem;
import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import eu.pb4.polymer.core.api.item.*;
import net.minecraft.item.tooltip.*;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import xyz.nucleoid.packettweaker.PacketContext;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import org.jetbrains.annotations.*;

import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;

import java.util.*;

public class CuddlyItem extends FactoryBlockItem implements Trinket {

	private final Text tooltip;

	public <T extends Block & PolymerBlock> CuddlyItem(Block block, Settings settings, String tooltip) {
		super((T) block, settings);
		this.tooltip = tooltip == null ? null : Text.translatable(tooltip).formatted(Formatting.GRAY);
		PolymerItemUtils.enableStonecutterFix();
		TrinketsApi.registerTrinket(this, this);
	}

	@Override
	public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
		super.onCraftByPlayer(stack, world, player);

		if (player != null) { // compensate for auto-crafter mods
			stack.set(BlahajDataComponentTypes.OWNER, player.getName());
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);

		if (this.tooltip != null) {
			tooltip.add(this.tooltip);
		}

		@Nullable Text ownerName = stack.get(BlahajDataComponentTypes.OWNER);
		//Text ownerName = Text.of("Default");
		if (ownerName != null) {
			@Nullable Text customName = stack.get(DataComponentTypes.CUSTOM_NAME);
			if (customName == null) {
				tooltip.add(Text.translatable("tooltip.blahaj.owner.craft", ownerName).formatted(Formatting.GRAY));
			} else {
				tooltip.add(Text.translatable("tooltip.blahaj.owner.rename", customName, ownerName).formatted(Formatting.GRAY));
			}
		}
	}

	public static final Identifier MINING_SPEED_MODIFIER_ID = Identifier.of(Blahaj.MOD_ID, "base_attack_damage");

	@Override
	public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
		return true;
	}
	@Override
	public boolean canEquipFromUse(ItemStack stack, LivingEntity entity) {
		return true;
	}

	public static AttributeModifiersComponent createAttributeModifiers() {
		return AttributeModifiersComponent.builder()
			.add(EntityAttributes.BLOCK_BREAK_SPEED, new EntityAttributeModifier(MINING_SPEED_MODIFIER_ID, -3.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND)
			.add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, -2.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND)
			.build();
	}
	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if (equipItem(user, stack)) {
			return ActionResult.SUCCESS;
		}
		return super.use(world, user, hand);
	}

	public static boolean equipItem(PlayerEntity user, ItemStack stack) {
		return equipItem((LivingEntity) user, stack);
	}

	public static boolean equipItem(LivingEntity user, ItemStack stack) {
		Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(user);
		if (optional.isPresent()) {
			TrinketComponent comp = optional.get();
			for (Map<String, TrinketInventory> group : comp.getInventory().values()) {
				for (TrinketInventory inv : group.values()) {
					for (int i = 0; i < inv.size(); i++) {
						if (inv.getStack(i).isEmpty()) {
							SlotReference ref = new SlotReference(inv, i);
							if (TrinketSlot.canInsert(stack, ref, user)) {
								ItemStack newStack = stack.copy();
								inv.setStack(i, newStack);
								Trinket trinket = TrinketsApi.getTrinket(stack.getItem());
								RegistryEntry<SoundEvent> soundEvent = trinket.getEquipSound(stack, ref, user);
								if (!stack.isEmpty() && soundEvent != null) {
								   user.emitGameEvent(GameEvent.EQUIP);
								   user.playSound(soundEvent.value(), 1.0F, 1.0F);
								}
								stack.setCount(0);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}