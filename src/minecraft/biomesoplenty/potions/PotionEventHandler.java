package biomesoplenty.potions;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.Potions;

public class PotionEventHandler
{
	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.isPotionActive(Potions.paralysis.get()))
		{
			event.entityLiving.motionX = 0.0;
			if (!event.entityLiving.isAirBorne) {
				event.entityLiving.motionY = 0.0;
			}
			event.entityLiving.motionZ = 0.0;

			if (event.entityLiving instanceof EntityCreeper) {
				((EntityCreeper)event.entityLiving).setCreeperState(-1);
			}

			if (event.entityLiving.getActivePotionEffect(Potions.paralysis.get()).getDuration() == 0)
			{
				event.entityLiving.removePotionEffect(Potions.paralysis.get().id);
				return;
			}
		}
	}

	@ForgeSubscribe
	public void onEndermanTP(EnderTeleportEvent event)
	{
		if (event.entityLiving.isPotionActive(Potions.paralysis.get())) {
			event.setCanceled(true);
		}
	}
}
