package com.myband.myband.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myband.myband.R;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    @BindView(R.id.txtUserName)
    TextView mTxtUserName;

    @BindView(R.id.txtLogin)
    TextView mTxtLogin;

    @BindView(R.id.txtCategory)
    TextView mTxtCategory;

    private User user;
    Unbinder unbinder;

    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        Parcelable parcelable = Parcels.wrap(user);
        args.putParcelable("user", parcelable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Parcelable parcelable = getArguments().getParcelable("user");
            user = Parcels.unwrap(parcelable);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, layout);

        mTxtUserName.setText(user.getUserName());
        mTxtLogin.setText(user.getLogin());
        switch (user.getCategory().getId().intValue()) {
            case 1:
                mTxtCategory.setText(getResources().getString(R.string.typeArtist));
                break;
            case 2:
                mTxtCategory.setText(getResources().getString(R.string.typeBand));
                break;
            case 3:
                mTxtCategory.setText(getResources().getString(R.string.typePromoter));
                break;
            default:
                mTxtCategory.setText(getResources().getString(R.string.typeArtist));
                break;
        }
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
