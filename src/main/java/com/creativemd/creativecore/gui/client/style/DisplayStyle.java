package com.creativemd.creativecore.gui.client.style;

import com.creativemd.creativecore.gui.GuiRenderHelper;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public abstract class DisplayStyle {
	
	public void renderStyle(int x, int y, GuiRenderHelper helper, int width, int height)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, 0);
		renderStyle(helper, width, height);
		GlStateManager.popMatrix();
	}
	
	public abstract void renderStyle(GuiRenderHelper helper, int width, int height);
	
}