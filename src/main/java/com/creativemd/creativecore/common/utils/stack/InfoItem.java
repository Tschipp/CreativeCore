package com.creativemd.creativecore.common.utils.stack;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class InfoItem extends InfoStack {
	
	public Item item;
	
	public InfoItem(Item item, int stackSize) {
		super(stackSize);
		this.item = item;
	}
	
	public InfoItem(Item item) {
		this(item, 1);
	}
	
	public InfoItem() {
		super();
	}
	
	@Override
	protected void writeToNBTExtra(NBTTagCompound nbt) {
		ResourceLocation location = item.REGISTRY.getNameForObject(item);
		if (location != null)
			nbt.setString("item", location.toString());
		else
			System.out.println("Invalid InfoBlock entry. Item does not exists");
	}
	
	@Override
	protected void loadFromNBTExtra(NBTTagCompound nbt) {
		item = Item.REGISTRY.getObject(new ResourceLocation(nbt.getString("item")));
	}
	
	@Override
	public boolean isInstanceIgnoreSize(InfoStack info) {
		if (info instanceof InfoItem)
			return ((InfoItem) info).item == item;
		if (info instanceof InfoItemStack)
			return item == ((InfoItemStack) info).stack.getItem();
		return false;
	}
	
	@Override
	public InfoStack copy() {
		return new InfoItem(item, stackSize);
	}
	
	@Override
	public ItemStack getItemStack(int stacksize) {
		return new ItemStack(item, stacksize);
	}
	
	@Override
	protected boolean isStackInstanceIgnoreSize(ItemStack stack) {
		return stack.getItem() == item;
	}
	
	@Override
	public boolean equalsIgnoreSize(Object object) {
		return object instanceof InfoItem && ((InfoItem) object).item == this.item;
	}
	
	@Override
	public ArrayList<ItemStack> getAllPossibleItemStacks() {
		ArrayList<ItemStack> stacks = new ArrayList<>();
		stacks.add(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		return stacks;
	}
	
}
