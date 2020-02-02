package com.parkchanwoo.tamagotchiar;

import android.content.Context;

import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

public class RenderableFactory {

	public CompletableFuture<ModelRenderable> buildRenderable(Context context, int resource) {
		//Build the ModelRenderable
		return ModelRenderable.builder()
				.setSource(context, resource)
				.build();
	}
}
