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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.exception.ValidatorException;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.Hero;
import br.com.battista.arcadiacaller.model.HeroGuild;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.service.HeroService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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

    private final Map<String, Hero> heroMap = Maps.newLinkedHashMap();

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
                Log.i(TAG, "onPostExecute: Load all heroes Guilds!");
                for (Hero hero : heroes) {
                    heroMap.put(hero.getName(), hero);
                }

                ArrayList<String> namesHeroes = Lists.newArrayList(heroMap.keySet());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, (List<String>) namesHeroes);

                if (!Strings.isNullOrEmpty(campaign.getGuild01()) && campaign.getHeroesGuild01() != null) {
                    spnGuildBlueHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_hero_01);
                    spnGuildBlueHero01.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild01().getHero01() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 01 to Guild Blue!");
                        HeroGuild hero01 = campaign.getHeroesGuild01().getHero01();
                        spnGuildBlueHero01.setText(hero01.getHero().getName());
                    }

                    spnGuildBlueHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_hero_02);
                    spnGuildBlueHero02.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild01().getHero02() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 03 to Guild Blue!");
                        HeroGuild hero02 = campaign.getHeroesGuild01().getHero02();
                        spnGuildBlueHero02.setText(hero02.getHero().getName());
                    }

                    spnGuildBlueHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue_hero_03);
                    spnGuildBlueHero03.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild01().getHero03() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 03 to Guild Blue!");
                        HeroGuild hero03 = campaign.getHeroesGuild01().getHero03();
                        spnGuildBlueHero03.setText(hero03.getHero().getName());
                    }

                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue).setVisibility(View.VISIBLE);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_blue).setVisibility(View.GONE);
                }

                if (!Strings.isNullOrEmpty(campaign.getGuild02()) && campaign.getHeroesGuild02() != null) {
                    spnGuildGreenHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_hero_01);
                    spnGuildGreenHero01.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild02().getHero01() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 01 to Guild Green!");
                        HeroGuild hero01 = campaign.getHeroesGuild02().getHero01();
                        spnGuildGreenHero01.setText(hero01.getHero().getName());
                    }

                    spnGuildGreenHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_hero_02);
                    spnGuildGreenHero02.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild02().getHero02() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 02 to Guild Green!");
                        HeroGuild hero02 = campaign.getHeroesGuild02().getHero02();
                        spnGuildGreenHero02.setText(hero02.getHero().getName());
                    }

                    spnGuildGreenHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green_hero_03);
                    spnGuildGreenHero03.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild02().getHero03() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 03 to Guild Green!");
                        HeroGuild hero03 = campaign.getHeroesGuild02().getHero03();
                        spnGuildGreenHero03.setText(hero03.getHero().getName());
                    }

                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green).setVisibility(View.VISIBLE);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_green).setVisibility(View.GONE);
                }

                if (!Strings.isNullOrEmpty(campaign.getGuild03()) && campaign.getHeroesGuild03() != null) {
                    spnGuildRedHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_hero_01);
                    spnGuildRedHero01.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild03().getHero01() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 01 to Guild Red!");
                        HeroGuild hero01 = campaign.getHeroesGuild03().getHero01();
                        spnGuildRedHero01.setText(hero01.getHero().getName());
                    }


                    spnGuildRedHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_hero_02);
                    spnGuildRedHero02.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild03().getHero02() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 02 to Guild Red!");
                        HeroGuild hero02 = campaign.getHeroesGuild03().getHero02();
                        spnGuildRedHero02.setText(hero02.getHero().getName());
                    }

                    spnGuildRedHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red_hero_03);
                    spnGuildRedHero03.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild03().getHero03() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 03 to Guild Red!");
                        HeroGuild hero03 = campaign.getHeroesGuild03().getHero03();
                        spnGuildRedHero03.setText(hero03.getHero().getName());
                    }

                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red).setVisibility(View.VISIBLE);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_red).setVisibility(View.GONE);
                }

                if (!Strings.isNullOrEmpty(campaign.getGuild04()) && campaign.getHeroesGuild04() != null) {
                    spnGuildOrangeHero01 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_hero_01);
                    spnGuildOrangeHero01.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild04().getHero01() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 01 to Guild Orange!");
                        HeroGuild hero01 = campaign.getHeroesGuild04().getHero01();
                        spnGuildOrangeHero01.setText(hero01.getHero().getName());
                    }

                    spnGuildOrangeHero02 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_hero_02);
                    spnGuildOrangeHero02.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild04().getHero02() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 02 to Guild Orange!");
                        HeroGuild hero02 = campaign.getHeroesGuild04().getHero02();
                        spnGuildOrangeHero02.setText(hero02.getHero().getName());
                    }

                    spnGuildOrangeHero03 = (MaterialBetterSpinner)
                            viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange_hero_03);
                    spnGuildOrangeHero03.setAdapter(arrayAdapter);
                    if (campaign.getHeroesGuild04().getHero03() != null) {
                        Log.i(TAG, "onPostExecute: Load the hero 03 to Guild Orange!");
                        HeroGuild hero03 = campaign.getHeroesGuild04().getHero03();
                        spnGuildOrangeHero03.setText(hero03.getHero().getName());
                    }

                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange).setVisibility(View.VISIBLE);
                } else {
                    viewFragment.findViewById(R.id.detail_card_view_heroes_guild_orange).setVisibility(View.GONE);
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

        if (!Strings.isNullOrEmpty(campaign.getGuild01()) && campaign.getHeroesGuild01() != null) {
            Guild heroesGuild01 = campaign.getHeroesGuild01();

            Hero hero01 = heroMap.get(spnGuildBlueHero01.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 01: {0} to Guild Blue!", hero01));
            HeroGuild heroGuild = HeroGuild.builder().hero(hero01).active(TRUE).deleted(FALSE).build();
            heroesGuild01.setHero01(heroGuild);

            Hero hero02 = heroMap.get(spnGuildBlueHero02.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 02: {0} to Guild Blue!", hero02));
            heroGuild = HeroGuild.builder().hero(hero02).active(TRUE).deleted(FALSE).build();
            heroesGuild01.setHero02(heroGuild);

            Hero hero03 = heroMap.get(spnGuildBlueHero03.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 03: {0} to Guild Blue!", hero03));
            heroGuild = HeroGuild.builder().hero(hero03).active(TRUE).deleted(FALSE).build();
            heroesGuild01.setHero03(heroGuild);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild02()) && campaign.getHeroesGuild02() != null) {
            Guild heroesGuild02 = campaign.getHeroesGuild02();

            Hero hero01 = heroMap.get(spnGuildGreenHero01.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 01: {0} to Guild Green!", hero01));
            HeroGuild heroGuild = HeroGuild.builder().hero(hero01).active(TRUE).deleted(FALSE).build();
            heroesGuild02.setHero01(heroGuild);

            Hero hero02 = heroMap.get(spnGuildGreenHero02.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 02: {0} to Guild Green!", hero02));
            heroGuild = HeroGuild.builder().hero(hero02).active(TRUE).deleted(FALSE).build();
            heroesGuild02.setHero02(heroGuild);

            Hero hero03 = heroMap.get(spnGuildGreenHero03.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 03: {0} to Guild Green!", hero03));
            heroGuild = HeroGuild.builder().hero(hero03).active(TRUE).deleted(FALSE).build();
            heroesGuild02.setHero03(heroGuild);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild03()) && campaign.getHeroesGuild03() != null) {
            Guild heroesGuild03 = campaign.getHeroesGuild03();

            Hero hero01 = heroMap.get(spnGuildRedHero01.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 01: {0} to Guild Red!", hero01));
            HeroGuild heroGuild = HeroGuild.builder().hero(hero01).active(TRUE).deleted(FALSE).build();
            heroesGuild03.setHero01(heroGuild);

            Hero hero02 = heroMap.get(spnGuildRedHero02.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 02: {0} to Guild Red!", hero02));
            heroGuild = HeroGuild.builder().hero(hero02).active(TRUE).deleted(FALSE).build();
            heroesGuild03.setHero02(heroGuild);

            Hero hero03 = heroMap.get(spnGuildRedHero03.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 03: {0} to Guild Red!", hero03));
            heroGuild = HeroGuild.builder().hero(hero03).active(TRUE).deleted(FALSE).build();
            heroesGuild03.setHero03(heroGuild);
        }

        if (!Strings.isNullOrEmpty(campaign.getGuild04()) && campaign.getHeroesGuild04() != null) {
            Guild heroesGuild04 = campaign.getHeroesGuild04();

            Hero hero01 = heroMap.get(spnGuildOrangeHero01.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 01: {0} to Guild Orange!", hero01));
            HeroGuild heroGuild = HeroGuild.builder().hero(hero01).active(TRUE).deleted(FALSE).build();
            heroesGuild04.setHero01(heroGuild);

            Hero hero02 = heroMap.get(spnGuildOrangeHero02.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 02: {0} to Guild Orange!", hero02));
            heroGuild = HeroGuild.builder().hero(hero02).active(TRUE).deleted(FALSE).build();
            heroesGuild04.setHero02(heroGuild);

            Hero hero03 = heroMap.get(spnGuildOrangeHero03.getText().toString());
            Log.i(TAG, MessageFormat.format("processNextAction: Fill the hero 03: {0} to Guild Orange!", hero03));
            heroGuild = HeroGuild.builder().hero(hero03).active(TRUE).deleted(FALSE).build();
            heroesGuild04.setHero03(heroGuild);
        }

        final View currentView = view;
        new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    replaceDetailFragment(CampaignDetailSceneryFragment.newInstance(campaign, campaign.getLocationCurrent()));
                } else {
                    AndroidUtils.snackbar(currentView, R.string.msg_failed_update_campaign);
                }
                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();

                    CampaignService campaignService = Inject.provideCampaignService();
                    Log.i(TAG, "doInBackground: Update the campaign with data heroes!");
                    campaign = campaignService.update(token, campaign);

                } catch (ValidatorException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                    return false;
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

}
