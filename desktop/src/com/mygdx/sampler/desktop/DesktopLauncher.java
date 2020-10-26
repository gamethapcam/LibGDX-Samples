package com.mygdx.sampler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.mygdx.sampler.GdxGeneratedSample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Game test";
		config.width = 800;
		config.height = 600;
		config.resizable = true;

		//Pack all textures into texture atlas, spritesheet
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.pot = true;
		settings.fast = true;
		settings.combineSubdirectories = true;
		settings.paddingX = 1;
		settings.paddingY = 1;
		settings.edgePadding = true;
		TexturePacker.process(settings,"raw_textures","./", "textures");

		new LwjglApplication(new GdxGeneratedSample(), config);
	}
}
