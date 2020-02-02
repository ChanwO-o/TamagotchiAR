package com.parkchanwoo.tamagotchiar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

public class StartFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;
	private ImageView ivLogo;
	private ImageButton ibStart;

	public StartFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_start, container, false);
		ivLogo = v.findViewById(R.id.ivLogo);
		ibStart = v.findViewById(R.id.ibStart);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

		ibStart.setOnClickListener(view -> {
			ivLogo.setVisibility(View.GONE);
			ibStart.setVisibility(View.GONE);

//			getActivity().getSupportFragmentManager().beginTransaction()
//					.add(R.id.flMainFragments, new PetInfoFragment(), "PetInfoFragment")
//					.commit();
		});
	}
}
