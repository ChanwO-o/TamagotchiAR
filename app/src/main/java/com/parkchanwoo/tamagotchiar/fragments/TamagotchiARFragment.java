package com.parkchanwoo.tamagotchiar.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

public class TamagotchiARFragment extends ArFragment {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;
	private ModelRenderable petRenderable, confettiRenderable;
	private AnchorNode anchorNode;
	private boolean petCreated = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
		Log.i(TAG, "onActivityCreated()");

		//Build the ModelRenderable
		ModelRenderable.builder()
				.setSource(getContext(), R.raw.dog1)
				.build()
				.thenAccept(renderable -> petRenderable = renderable)
				.exceptionally(
						throwable -> {
							Log.e(TAG, "Unable to load renderable");
							return null;
						});

		ModelRenderable.builder()
				.setSource(getContext(), R.raw.confetti)
				.build()
				.thenAccept(renderable -> confettiRenderable = renderable)
				.exceptionally(
						throwable -> {
							Log.e(TAG, "Unable to load renderable");
							return null;
						});

		//Listen for onTap events
		setOnTapArPlaneListener(
				(HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
					if (petRenderable == null || petCreated)
						return;
					Anchor anchor = hitResult.createAnchor();
					anchorNode = new AnchorNode(anchor); //Build a node of type AnchorNode
					anchorNode.setParent(getArSceneView().getScene()); //Connect the AnchorNode to the Scene
					TransformableNode transformableNode = new TransformableNode(getTransformationSystem()); //Build a node of type TransformableNode
					transformableNode.setParent(anchorNode); //Connect the TransformableNode to the AnchorNode
					transformableNode.setRenderable(petRenderable); //Attach the Renderable
					transformableNode.select(); //Set the node
					transformableNode.getScaleController().setMinScale(0.30f);
					transformableNode.getScaleController().setMaxScale(0.35f);
					petCreated = true; // prevent user from creating multiple pets
					confetti();
				}
		);
	}

	private void confetti() {
		TransformableNode confettiNode = new TransformableNode(getTransformationSystem());
		confettiNode.setLocalScale(new Vector3(0.1f, 0.1f, 0.1f));
		confettiNode.setParent(anchorNode);
		confettiNode.setRenderable(confettiRenderable);
	}
}
