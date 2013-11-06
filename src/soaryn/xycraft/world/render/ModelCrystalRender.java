package soaryn.xycraft.world.render;

import codechicken.lib.render.Vertex5;
import codechicken.lib.vec.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

public class ModelCrystalRender {

    private Vector3 yAxis = new Vector3(0.0D, 0.0D, 1.0D);
   private Vector3 zAxis = new Vector3(0.0D, 1.0D, 0.0D);
   private Vector3 xAxis = new Vector3(1.0D, 0.0D, 0.0D);
   private Vertex5[][] verts = new Vertex5[18][24];
    private static final ResourceLocation textur = new ResourceLocation("/xycraft/textures/blocks/crystal.png");


   public ModelCrystalRender() {
     // this.model.setTile(15, 15);
      this.verts[0][0] = new Vertex5(0.00395D, 0.364D, 0.0D, 0.0D, 0.0D);
      this.verts[0][1] = new Vertex5(0.09995D, 0.121D, 0.0D, 0.0D, 16.0D);
      this.verts[0][2] = new Vertex5(0.04704D, -0.121D, -0.08485D, 16.0D, 16.0D);
      this.verts[0][3] = new Vertex5(-0.04704D, 0.121D, -0.08485D, 16.0D, 0.0D);
      this.verts[0][4] = new Vertex5(0.00395D, 0.364D, 0.0D, 0.0D, 0.0D);
      this.verts[0][5] = new Vertex5(-0.04704D, 0.121D, -0.08485D, 0.0D, 16.0D);
      this.verts[0][6] = new Vertex5(-0.09995D, -0.121D, 0.0D, 16.0D, 16.0D);
      this.verts[0][7] = new Vertex5(-0.04704D, 0.121D, 0.08485D, 16.0D, 0.0D);
      this.verts[0][8] = new Vertex5(0.00395D, 0.364D, 0.0D, 0.0D, 0.0D);
      this.verts[0][9] = new Vertex5(-0.04704D, 0.121D, 0.08485D, 0.0D, 16.0D);
      this.verts[0][10] = new Vertex5(0.04704D, -0.121D, 0.08485D, 16.0D, 16.0D);
      this.verts[0][11] = new Vertex5(0.09995D, 0.121D, 0.0D, 16.0D, 0.0D);
      this.verts[0][12] = new Vertex5(-0.04704D, 0.121D, 0.08485D, 0.0D, 0.0D);
      this.verts[0][13] = new Vertex5(-0.09995D, -0.121D, -0.0D, 0.0D, 16.0D);
      this.verts[0][14] = new Vertex5(-0.00395D, -0.364D, 0.0D, 16.0D, 16.0D);
      this.verts[0][15] = new Vertex5(0.04704D, -0.121D, 0.08485D, 16.0D, 0.0D);
      this.verts[0][16] = new Vertex5(0.09995D, 0.121D, 0.0D, 0.0D, 0.0D);
      this.verts[0][17] = new Vertex5(0.04704D, -0.121D, 0.08485D, 0.0D, 16.0D);
      this.verts[0][18] = new Vertex5(-0.00395D, -0.364D, 0.0D, 16.0D, 16.0D);
      this.verts[0][19] = new Vertex5(0.04704D, -0.121D, -0.08485D, 16.0D, 0.0D);
      this.verts[0][20] = new Vertex5(-0.04704D, 0.121D, -0.08485D, 0.0D, 0.0D);
      this.verts[0][21] = new Vertex5(0.04704D, -0.121D, -0.08485D, 0.0D, 16.0D);
      this.verts[0][22] = new Vertex5(-0.00395D, -0.364D, 0.0D, 16.0D, 16.0D);
      this.verts[0][23] = new Vertex5(-0.09995D, -0.121D, -0.0D, 16.0D, 0.0D);
   }

   public void renderInv() {
     //  FMLClientHandler.instance().getClient().renderEngine.bindTexture(textur);
      this.renderSet(this.verts[0], this.yAxis, 1, new Vector3(0.0D, 0.0D, 0.0D), 0);
   }

   public void render(int iside, int amount, Vector3 pos) {
     /* double modX = 0.0D;
      double modY = 0.0D;
      double modZ = 0.0D;
      Vector3 axis = null;
      switch(iside) {
      case 0:
         modY = 0.3D;
         axis = this.zAxis;
         break;
      case 1:
         modY = -0.3D;
         axis = this.zAxis;
         break;
      case 2:
         modZ = 0.3D;
         axis = this.xAxis;
         break;
      case 3:
         modZ = -0.3D;
         axis = this.xAxis;
         break;
      case 4:
         modX = 0.3D;
         axis = this.yAxis;
         break;
      case 5:
         modX = -0.3D;
         axis = this.yAxis;
      }

      pos.add(0.5D + modX, 0.5D + modY, 0.5D + modZ);
      this.renderSet(this.verts[0], axis, amount, pos, iside);*/
     //  FMLClientHandler.instance().getClient().renderEngine.bindTexture(textur);
   }

   private void renderSet(Vertex5[] part, Vector3 axis, int amount, Vector3 pos, int iside) {
       FMLClientHandler.instance().getClient().renderEngine.bindTexture(textur);

   }
}
