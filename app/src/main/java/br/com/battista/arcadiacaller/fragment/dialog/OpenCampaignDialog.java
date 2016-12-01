package br.com.battista.arcadiacaller.fragment.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;

public class OpenCampaignDialog extends DialogFragment {

    private static final String TAG = OpenCampaignDialog.class.getSimpleName();
    private static final String DIALOG_OPEN_CAMPAIGN = "dialog_open_campaign";
    public static final int DIALOG_SHARE_CAMPAIGN_FRAGMENT = 1;

    private EditText txtKeyCampaign;
    private Button btnOpenCampaign;
    private Button btnCancelCampaign;

    public OpenCampaignDialog() {
    }

    public static OpenCampaignDialog newInstance() {
        OpenCampaignDialog fragment = new OpenCampaignDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void showDialog(@NonNull Fragment fragment) {
        Log.i(TAG, "showAbout: Show dialog open campaign!");

        setTargetFragment(fragment, DIALOG_SHARE_CAMPAIGN_FRAGMENT);

        FragmentManager fm = fragment.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(DIALOG_OPEN_CAMPAIGN);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        show(ft, DIALOG_OPEN_CAMPAIGN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFragment = inflater.inflate(R.layout.dialog_open_campaign, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        loadViews(viewFragment);
        return viewFragment;
    }

    private void loadViews(View viewFragment) {
        Log.i(TAG, "loadViews: load all views!");
        txtKeyCampaign = (EditText) viewFragment.findViewById(R.id.dialog_view_campaign_key);

        btnCancelCampaign = (Button) viewFragment.findViewById(R.id.dialog_view_campaign_btn_cancel);
        btnCancelCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent());
                getDialog().dismiss();
            }
        });

        btnOpenCampaign = (Button) viewFragment.findViewById(R.id.dialog_view_campaign_btn_open);
        btnOpenCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = txtKeyCampaign.getText().toString().trim().toUpperCase();
                getActivity().getIntent().putExtra(BundleConstant.KEY, key);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
                getDialog().dismiss();
            }
        });
    }

}
