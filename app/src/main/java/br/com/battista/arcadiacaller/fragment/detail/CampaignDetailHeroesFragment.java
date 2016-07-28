package br.com.battista.arcadiacaller.fragment.detail;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.HeroService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class CampaignDetailHeroesFragment extends BaseFragment {

    private static final String TAG = CampaignDetailHeroesFragment.class.getSimpleName();

    private Campaign campaign;

    private MaterialBetterSpinner spnGuildBlueHero01;
    private MaterialBetterSpinner spnGuildBlueHero02;
    private MaterialBetterSpinner spnGuildBlueHero03;

    private MaterialBetterSpinner spnGuildGreenHero01;
    private MaterialBetterSpinner spnGuildGreenHero02;
    private MaterialBetterSpinner spnGuildGreenHero03;

    private MaterialBetterSpinner spnGuildRedHero01;
    private MaterialBetterSpinner spnGuildRedHero02;
    private MaterialBetterSpinner spnGuildRedHero03;

    private MaterialBetterSpinner spnGuildOrangeHero01;
    private MaterialBetterSpinner spnGuildOrangeHero02;
    private MaterialBetterSpinner spnGuildOrangeHero03;

    private Map<String, Hero> heroMap = Maps.newLinkedHashMap();

    public CampaignDetailHeroesFragment() {
    }

    public static CampaignDetailHeroesFragment newInstance(Campaign campaign) {
        CampaignDetailHeroesFragment fragment = new CampaignDetailHeroesFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail guilds campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_detail_heroes, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_heroes_campaign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        loadGuildsImg(viewFragment);
        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void loadGuildsImg(View viewFragment) {
        Glide.with(getContext())
                .load(NameGuildEnum.BLUE.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_img));

        Glide.with(getContext())
                .load(NameGuildEnum.GREEN.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_img));

        Glide.with(getContext())
                .load(NameGuildEnum.RED.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_img));

        Glide.with(getContext())
                .load(NameGuildEnum.ORANGE.getUrlImg())
                .crossFade()
                .into((ImageView) viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_img));

    }

    private void processDataFragment(View view, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            loadHeroes(view);
        }
    }

    private void loadHeroes(final View viewFragment) {

        new AsyncTask<Void, Integer, Boolean>() {

            List<Hero> heroes;

            @Override
            protected Boolean doInBackground(Void... voids) {
                String token = MainApplication.instance().getToken();
                HeroService heroService = Inject.provideHeroService();
                heroes = heroService.findAll(token);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {

                for (Hero hero : heroes) {
                    heroMap.put(hero.getName(), hero);
                }

                ArrayList<String> namesHeroes = Lists.newArrayList(heroMap.keySet());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, (List<String>) namesHeroes);

                if (!Strings.isNullOrEmpty(campaign.getGuild01())) {
                    spnGuildBlueHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_hero_01);
                    spnGuildBlueHero01.setAdapter(arrayAdapter);
                    spnGuildBlueHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_hero_02);
                    spnGuildBlueHero02.setAdapter(arrayAdapter);
                    spnGuildBlueHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_hero_03);
                    spnGuildBlueHero03.setAdapter(arrayAdapter);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue).setVisibility(View.INVISIBLE);
                }

                if (!Strings.isNullOrEmpty(campaign.getGuild02())) {
                    spnGuildGreenHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_hero_01);
                    spnGuildGreenHero01.setAdapter(arrayAdapter);
                    spnGuildGreenHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_hero_02);
                    spnGuildGreenHero02.setAdapter(arrayAdapter);
                    spnGuildGreenHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_hero_03);
                    spnGuildGreenHero03.setAdapter(arrayAdapter);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green).setVisibility(View.INVISIBLE);
                }

                if (!Strings.isNullOrEmpty(campaign.getGuild03())) {
                    spnGuildRedHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_hero_01);
                    spnGuildRedHero01.setAdapter(arrayAdapter);
                    spnGuildRedHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_hero_02);
                    spnGuildRedHero02.setAdapter(arrayAdapter);
                    spnGuildRedHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_hero_03);
                    spnGuildRedHero03.setAdapter(arrayAdapter);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red).setVisibility(View.INVISIBLE);
                }

                if (!Strings.isNullOrEmpty(campaign.getGuild04())) {
                    spnGuildOrangeHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_hero_01);
                    spnGuildOrangeHero01.setAdapter(arrayAdapter);
                    spnGuildOrangeHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_hero_02);
                    spnGuildOrangeHero02.setAdapter(arrayAdapter);
                    spnGuildOrangeHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_hero_03);
                    spnGuildOrangeHero03.setAdapter(arrayAdapter);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange).setVisibility(View.INVISIBLE);
                }

            }

        }.execute();

    }


    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailSceneryFragment!");

        if (campaign == null) {
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_not_found);
            return;
        }

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    replaceDetailFragment(CampaignDetailSceneryFragment.newInstance(campaign, campaign.getLocationCurrent()));
                } else {
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_create_campaign);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();

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

}
