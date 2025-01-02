package hibi.blahaj.sound;

import hibi.blahaj.Blahaj;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModSoundEvents {
    public static final List<SoundEvent> BLOCK_CUDDLY_ITEM = new ArrayList<>();
    public static final SoundEvent BLOCK_CUDDLY_ITEM_HIT = register("block.blahaj.cuddly_item.hit");

    private static Identifier id(String id) {
        return Identifier.of(Blahaj.MOD_ID, id);
    }

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, id(id), SoundEvent.of(id(id)));
    }

	public static SoundEvent getRandomSqueak() {
		return BLOCK_CUDDLY_ITEM.get(Blahaj.RANDOM.nextInt(BLOCK_CUDDLY_ITEM.size()));
	}

    public static void init() {
		for (int i = 1; i < 6; i++) {
			BLOCK_CUDDLY_ITEM.add(register("block.blahaj.cuddly_item.use." + i));
		}
	}
}
