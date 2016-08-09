package br.com.battista.arcadiacaller.fragment.detail;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.UserService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;
import static java.lang.Boolean.FALSE;

public class CampaignDetailGuildsFragment extends BaseFragment {

    private static final String TAG = CampaignDetailGuildsFragment.class.getSimpleName();

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
                if (!hasFocus && !Strings.isNullOrEmpty(textEdit.getText().toString()) && textEdit.getError() == null) {

                    final String loginGuild = textEdit.getText().toString().trim();
                    Log.i(TAG, MessageFormat.format("onFocusChange: Validate the login guild: {0}.", loginGuild));

                    final Context context = getContext();
                    new AsyncTask<Void, Integer, Boolean>() {
                        @Override
                        protected Boolean doInBackground(Void... voids) {
                            try {
                                String token = MainApplication.instance().getToken();
                                return Inject.provideUserService().existsUsername(token, loginGuild);
                            } catch (Exception e) {
                                Log.e(TAG, e.getLocalizedMessage(), e);
                            }
                            return false;
                        }

                        @Override
                        protected void onPostExecute(Boolean result) {
                            if (!result) {
                                Log.i(TAG, MessageFormat.format("onPostExecute: Not exists the login guild: {0}.", loginGuild));
                                String msgErrorUsername = context.getString(R.string.msg_username_guild_required);
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
        ImageView imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_blue);
        ImageLoadUtils
                .loadImage(getContext(),
                        BLUE.getUrlImg(),
                        imageView);

        imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_green);
        ImageLoadUtils
                .loadImage(getContext(),
                        GREEN.getUrlImg(),
                        imageView);

        imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_red);
        ImageLoadUtils
                .loadImage(getContext(),
                        RED.getUrlImg(),
                        imageView);

        imageView = (ImageView) viewFragment.findViewById(R.id.detail_card_view_guilds_img_orange);
        ImageLoadUtils
                .loadImage(getContext(),
                        ORANGE.getUrlImg(),
                        imageView);
    }

    private void processDataFragment(View view, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            txtLoginBlue.setText(campaign.getGuild01());
            txtLoginGreen.setText(campaign.getGuild02());
            txtLoginRed.setText(campaign.getGuild03());
            txtLoginOrange.setText(campaign.getGuild04());
        }
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailHeroesFragment!");

        if (campaign == null) {
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_not_found);
            return;
        }

        txtLoginBlue = (EditText) view.findViewById(R.id.detail_card_view_guilds_login_blue);
        final String loginBlue = txtLoginBlue.getText().toString().trim();
        txtLoginGreen = (EditText) view.findViewById(R.id.detail_card_view_guilds_login_green);
        final String loginGreen = txtLoginGreen.getText().toString().trim();
        txtLoginRed = (EditText) view.findViewById(R.id.detail_card_view_guilds_login_red);
        final String loginRed = txtLoginRed.getText().toString().trim();
        txtLoginOrange = (EditText) view.findViewById(R.id.detail_card_view_guilds_login_orange);
        final String loginOrange = txtLoginOrange.getText().toString().trim();

        if (Strings.isNullOrEmpty(loginBlue) && Strings.isNullOrEmpty(loginGreen) && Strings.isNullOrEmpty(loginRed) && Strings.isNullOrEmpty(loginOrange)) {
            AndroidUtils.snackbar(view, R.string.msg_at_east_one_guild_required);
            return;
        }

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    replaceDetailFragment(CampaignDetailHeroesFragment.newInstance(campaign), R.id.detail_container);
                } else {
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_update_campaign);
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
                        Guild guild = campaign.getHeroesGuild01();
                        if (guild == null) {
                            guild = new Guild().name(BLUE).user(user).savedMoney(FALSE).defeats(0).victories(0);
                        } else {
                            guild.setUser(user);
                            guild.setName(BLUE);
                        }
                        campaign.setGuild01(loginBlue);
                        campaign.setHeroesGuild01(guild);
                        Log.i(TAG, MessageFormat.format("doInBackground: Fill the guild Blue: {} in campaign!", guild));
                    } else {
                        Log.i(TAG, "doInBackground: Clear data guild Blue!");
                        campaign.setGuild01(null);
                        campaign.setHeroesGuild01(null);
                    }

                    if (!Strings.isNullOrEmpty(loginGreen)) {
                        User user = userService.findByUsername(token, loginGreen);
                        Guild guild = campaign.getHeroesGuild02();
                        if (guild == null) {
                            guild = new Guild().name(GREEN).user(user).savedMoney(FALSE).defeats(0).victories(0);
                        } else {
                            guild.setUser(user);
                            guild.setName(GREEN);
                        }
                        campaign.setGuild02(loginGreen);
                        campaign.setHeroesGuild02(guild);
                        Log.i(TAG, MessageFormat.format("doInBackground: Fill the guild Green: {} in campaign!", guild));
                    } else {
                        Log.i(TAG, "doInBackground: Clear data guild Green!");
                        campaign.setGuild02(null);
                        campaign.setHeroesGuild02(null);
                    }

                    if (!Strings.isNullOrEmpty(loginRed)) {
                        User user = userService.findByUsername(token, loginRed);
                        Guild guild = campaign.getHeroesGuild03();
                        if (guild == null) {
                            guild = new Guild().name(RED).user(user).savedMoney(FALSE).defeats(0).victories(0);
                        } else {
                            guild.setUser(user);
                            guild.setName(RED);
                        }
                        campaign.setGuild03(loginRed);
                        campaign.setHeroesGuild03(guild);
                        Log.i(TAG, MessageFormat.format("doInBackground: Fill the guild Red: {} in campaign!", guild));
                    } else {
                        Log.i(TAG, "doInBackground: Clear data guild Red!");
                        campaign.setGuild03(null);
                        campaign.setHeroesGuild03(null);
                    }

                    if (!Strings.isNullOrEmpty(loginOrange)) {
                        User user = userService.findByUsername(token, loginOrange);
                        Guild guild = campaign.getHeroesGuild04();
                        if (guild == null) {
                            guild = new Guild().name(ORANGE).user(user).savedMoney(FALSE).defeats(0).victories(0);
                        } else {
                            guild.setUser(user);
                            guild.setName(ORANGE);
                        }
                        campaign.setGuild04(loginOrange);
                        campaign.setHeroesGuild04(guild);
                        Log.i(TAG, MessageFormat.format("doInBackground: Fill the guild Orange: {} in campaign!", guild));
                    } else {
                        Log.i(TAG, "doInBackground: Clear data guild Orange!");
                        campaign.setGuild04(null);
                        campaign.setHeroesGuild04(null);
                    }

                    CampaignService campaignService = Inject.provideCampaignService();
                    Log.i(TAG, "doInBackground: Update the campaign with data Guilds!");
                    campaign = campaignService.update(token, campaign);

                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
