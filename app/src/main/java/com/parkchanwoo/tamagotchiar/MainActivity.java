package com.parkchanwoo.tamagotchiar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();

	private TamagotchiARFragment tamagotchiARFragment;
	private ModelRenderable androidRenderable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tamagotchiARFragment = (TamagotchiARFragment) getSupportFragmentManager().findFragmentById(R.id.tamagotchi_ar_fragment); //Find the fragment, using fragment manager

		//Build the ModelRenderable
		ModelRenderable.builder()
				.setSource(this, R.raw.android_logo)
				.build()
				.thenAccept(renderable -> androidRenderable = renderable)
				.exceptionally(
						throwable -> {
							Log.e(TAG, "Unable to load renderable");
							return null;
						});

		//Listen for onTap events
		tamagotchiARFragment.setOnTapArPlaneListener(
				(HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
					if (androidRenderable == null)
						return;
					Anchor anchor = hitResult.createAnchor();
					AnchorNode anchorNode = new AnchorNode(anchor); //Build a node of type AnchorNode
					anchorNode.setParent(tamagotchiARFragment.getArSceneView().getScene()); //Connect the AnchorNode to the Scene
					TransformableNode transformableNode = new TransformableNode(tamagotchiARFragment.getTransformationSystem()); //Build a node of type TransformableNode
					transformableNode.setParent(anchorNode); //Connect the TransformableNode to the AnchorNode
					transformableNode.setRenderable(androidRenderable); //Attach the Renderable
					transformableNode.select(); //Set the node
				}
		);


	}
}
