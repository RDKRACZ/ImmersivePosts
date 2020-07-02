package twistedgate.immersiveposts.common.data;

import blusunrize.immersiveengineering.common.data.models.LoadedModelProvider;
import net.minecraft.block.FenceBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import twistedgate.immersiveposts.IPOMod;
import twistedgate.immersiveposts.IPOStuff;
import twistedgate.immersiveposts.enums.EnumPostMaterial;

/**
 * @author TwistedGate
 */
public class IPOItemModels extends LoadedModelProvider{
	IPOBlockStates blockStates;
	final ExistingFileHelper exFileHelper;
	public IPOItemModels(DataGenerator gen, ExistingFileHelper exHelper, IPOBlockStates blockStates){
		super(gen, IPOMod.ID, "item", exHelper);
		this.exFileHelper=exHelper;
		this.blockStates=blockStates;
	}
	
	@Override
	public String getName(){
		return "Item Models";
	}
	
	@Override
	protected void registerModels(){
		getBuilder(IPOMod.ID+":item/postbase").parent(new ExistingModelFile(modLoc("block/postbase"), this.exFileHelper));
		
		fence(IPOStuff.fence_Iron,		"fence/iron",		mcLoc("block/iron_block"));
		fence(IPOStuff.fence_Gold,		"fence/gold",		mcLoc("block/gold_block"));
		fence(IPOStuff.fence_Copper,	"fence/copper",		ieLoc("block/metal/storage_copper"));
		fence(IPOStuff.fence_Lead,		"fence/lead",		ieLoc("block/metal/storage_lead"));
		fence(IPOStuff.fence_Silver,	"fence/silver",		ieLoc("block/metal/storage_silver"));
		fence(IPOStuff.fence_Nickel,	"fence/nickel",		ieLoc("block/metal/storage_nickel"));
		fence(IPOStuff.fence_Constantan,"fence/constantan",	ieLoc("block/metal/storage_constantan"));
		fence(IPOStuff.fence_Electrum,	"fence/electrum",	ieLoc("block/metal/storage_electrum"));
		fence(IPOStuff.fence_Uranium,	"fence/uranium",	ieLoc("block/metal/storage_uranium_side"));
		
		for(EnumPostMaterial m:EnumPostMaterial.values())
			switch(m){
				case WOOD:case IRON:case ALUMINIUM:case STEEL:case NETHERBRICK:case CONCRETE:case CONCRETE_LEADED: continue;
				default:{
					String name="stick_"+m.toString().toLowerCase();
					getBuilder(IPOMod.ID+":item/"+name)
						.parent(getExistingFile(mcLoc("item/generated")))
						.texture("layer0", modLoc("item/"+name));
				}
			}
	}
	
	private ResourceLocation ieLoc(String str){
		return new ResourceLocation("immersiveengineering", str);
	}
	
	private void fence(FenceBlock block, String name, ResourceLocation texture){
		try{
			String[] s=name.split("/");
			getBuilder(IPOMod.ID+":block/fences/inventory/"+s[1]+"_fence_inventory")
				.parent(new ExistingModelFile(new ResourceLocation("block/fence_inventory"), this.exFileHelper))
				.texture("texture", texture);
		}catch(Throwable e){
			IPODataGen.log.warn("Oops.. {}", e.getMessage());
		}
	}
}