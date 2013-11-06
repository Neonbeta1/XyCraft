package soaryn.xycraft.core.handler;

import codechicken.lib.vec.BlockCoord;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import soaryn.xycraft.core.handler.RenderTickHandler;

public class StareTracker {

   private BlockCoord staring = new BlockCoord();
   boolean blockStare = false;
   private int started;
   private int last;


   public void update(MovingObjectPosition mop) {
      int time = (int)RenderTickHandler.getTime();
      if(time - this.last > 10) {
         this.blockStare = false;
      }

      if(mop.typeOfHit == EnumMovingObjectType.TILE) {
         if(!this.blockStare || this.staring.x != mop.blockX || this.staring.y != mop.blockY || this.staring.z != mop.blockZ) {
            this.staring.set(mop.blockX, mop.blockY, mop.blockZ);
            this.started = time;
         }

         this.last = time;
         this.blockStare = true;
      } else {
         this.blockStare = false;
      }

   }

   public int getStareTime() {
      return !this.blockStare?0:(int)RenderTickHandler.getTime() - this.started;
   }
}
