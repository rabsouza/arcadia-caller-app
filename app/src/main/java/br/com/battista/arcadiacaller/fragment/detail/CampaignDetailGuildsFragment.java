package br.com.battista.arcadiacaller.fragment.detail;


import static java.lang.Boolean.FALSE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.fragment.CampaignsFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class CampaignDetailGuildsFragment extends BaseFragment {

    private static final String TAG = CampaignsFragment.class.getSimpleName();

    private Campaign campaign;
    private EditText txtLoginBlue;
    private EditText txtLoginRed;
    private EditText txtLoginOrange;
    private EditText txtLoginGreen;

    public CampaignDetailGuildsFragment() {
    }

    public static CampaignDetailGuildsFragment newInstance(Campaign campaign) {
        CampaignDetailGuildsFragment fragment = new CampaignDetailGuildsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail guilds campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_guilds, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_guilds_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        txtLoginBlue = (EditText) viewFragment.findViewById(R.id.detail_card_view_guilds_login_blue);
        createValidateLoginGuild(txtLoginBlue);

        txtLoginOrange = (EditText) viewFragment.findViewById(R.id.detail_card_view_guilds_login_orange);
        createValidateLoginGuild(txtLoginOrange);

        txtLoginRed = (EditText) viewFragment.findViewById(R.id.detail_card_view_guilds_login_red);
        createValidateLoginGuild(txtLoginRed);

        txtLoginGreen = (EditText) viewFragment.findViewById(R.id.detail_card_view_guilds_login_green);
        createValidateLoginGuild(txtLoginGreen);

        loadGuildsImg(viewFragment);
        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void createValidateLoginGuild(EditText txtLoginGuild) {
        final int resIdText = txtLoginGuild.getId();
        txtLoginGuild.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                final EditText textEdit = (EditText) view.findViewById(resIdText);
                if (!hasFocus && !Strings.isNullOrEmpty(textEdit.getText().toString())) {

                    final String loginGuild = textEdit.getText().toString().trim();
                    Log.i(TAG, MessageFormat.format("onFocusChange: Validate the login guild: {0}.", loginGuild));

                    new AsyncTask<Void, Integer, Boolean>() {
                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            String token = MainApplication.instance().getToken();
                            return Inject.provideUserService().existsUsername(token, loginGuild);
                        }

                        @Override
                        protected void onPostExecute(Boolean result) {
                            if (!result) {
                                Log.i(TAG, MessageFormat.format("onPostExecute: Not exists the login guild: {0}.", loginGuild));
                                String msgErrorUsername = getContext().getString(R.string.msg_username_guild_required);
                                AndroidUtils.changeErrorEditText(textEdit, msgErrorUsername, true);
                            } else {
                                Log.i(TAG, MessageFormat.format("onPostExecute: Exists the login guild: {0}.", loginGuild));
                                AndroidUtils.changeErrorEditText(textEdit);
                            }
                        }

                    }.execute();
                }
            }
        });
    }

    private void loadGuildsImg(View viewFragment) {
        Glide.with(getContext())
                .load(NameGuildEnum.BLUE.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_blue));

        Glide.with(getContext())
                .load(NameGuildEnum.GREEN.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_green));

        Glide.with(getContext())
                .load(NameGuildEnum.RED.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_red));

        Glide.with(getContext())
                .load(NameGuildEnum.ORANGE.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_orange));
    }

    private void processDataFragment(View view, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtLoginBlue.setText(campaign.getGuild01());
            txtLoginGreen.setText(campaign.getGuild02());
            txtLoginOrange.setText(campaign.getGuild03());
            txtLoginRed.setText(campaign.getGuild04());
        }
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailSceneryFragment!");

        final String loginBlue = txtLoginBlue.getText().toString().trim();
        final String loginRed = txtLoginRed.getText().toString().trim();
        final String loginGreen = txtLoginGreen.getText().toString().trim();
        final String loginOrange = txtLoginOrange.getText().toString().trim();

        if (Strings.isNullOrEmpty(loginBlue) && Strings.isNullOrEmpty(loginGreen) && Strings.isNullOrEmpty(loginRed) && Strings.isNullOrEmpty(loginOrange)) {
            AndroidUtils.snackbar(view, R.string.msg_at_east_one_guild_required);
            return;
        }

        if (campaign == null) {
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_not_found);
            return;
        }

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_loading, false) {

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    replaceDetailFragment(CampaignDetailSceneryFragment.newInstance(campaign, getLocationScenery()));
                } else {
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_create_campaign);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();

                    UserService userService = Inject.provideUserService();
                    if (!Strings.isNullOrEmpty(loginBlue)) {
                        User user = userService.findByUsername(token, loginBlue);
                        campaign.setGuild01(loginBlue);
                        Guild guild = Guild.builder().name(NameGuildEnum.BLUE).user(user).savedMoney(FALSE).defeats(0).victories(0).build();
                        campaign.setHeroesGuild01(guild);
                    }

                    if (!Strings.isNullOrEmpty(loginGreen)) {
                        User user = userService.findByUsername(token, loginGreen);
                        campaign.setGuild02(loginGreen);
                        Guild guild = Guild.builder().name(NameGuildEnum.GREEN).user(user).savedMoney(FALSE).defeats(0).victories(0).build();
                        campaign.setHeroesGuild02(guild);
                    }

                    if (!Strings.isNullOrEmpty(loginOrange)) {
                        User user = userService.findByUsername(token, loginOrange);
                        campaign.setGuild03(loginOrange);
                        Guild guild = Guild.builder().name(NameGuildEnum.ORANGE).user(user).savedMoney(FALSE).defeats(0).victories(0).build();
                        campaign.setHeroesGuild03(guild);
                    }

                    if (!Strings.isNullOrEmpty(loginRed)) {
                        User user = userService.findByUsername(token, loginRed);
                        campaign.setGuild04(loginRed);
                        Guild guild = Guild.builder().name(NameGuildEnum.RED).user(user).savedMoney(FALSE).defeats(0).victories(0).build();
                        campaign.setHeroesGuild04(guild);
                    }

                    CampaignService campaignService = Inject.provideCampaignService();
                    campaign = campaignService.update(token, campaign);
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

    @NonNull
    private LocationSceneryEnum getLocationScenery() {
        LocationSceneryEnum locationScenery = LocationSceneryEnum.NONE;
        if (campaign == null) {
            locationScenery = LocationSceneryEnum.NONE;
        } else if (campaign.getScenery1() == null || campaign.getScenery2() == null || campaign.getScenery3() == null) {
            locationScenery = LocationSceneryEnum.OUT_CIRCLE;
        } else if (campaign.getScenery4() == null || campaign.getScenery5() == null) {
            locationScenery = LocationSceneryEnum.INNER_CIRCLE;
        } else if (campaign.getScenery6() == null) {
            locationScenery = LocationSceneryEnum.ULTIMATE;
        }
        return locationScenery;
    }

}
