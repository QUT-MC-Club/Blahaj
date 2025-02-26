package hibi.blahaj.sound;

import hibi.blahaj.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.random.Random;

import java.util.*;

public class BlahajSoundEvents {

    public static final List<SoundEvent> BLOCK_CUDDLY_ITEM = new ArrayList<>();
    public static final SoundEvent BLOCK_CUDDLY_ITEM_HIT = register("block.blahaj.cuddly_item.hit");

    private static Identifier id(String id) {
        return Identifier.of(Blahaj.MOD_ID, id);
    }

    private static SoundEvent register(String id) {
        return SoundEvent.of(id(id));
}

    public static void init() {
		for (int i = 1; i < 6; i++) {
			BLOCK_CUDDLY_ITEM.add(register("block.blahaj.cuddly_item.use." + i));
		}
	}

	public static SoundEvent getRandomSqueak(Random random) {
		return BLOCK_CUDDLY_ITEM.get(random.nextInt(BLOCK_CUDDLY_ITEM.size()));
	}

}
