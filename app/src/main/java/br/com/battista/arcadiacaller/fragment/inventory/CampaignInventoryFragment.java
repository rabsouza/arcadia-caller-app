package br.com.battista.arcadiacaller.fragment.inventory;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.activity.MainActivity;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Card;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.HeroGuild;
import br.com.battista.arcadiacaller.model.enuns.ActionEnum;
import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.model.enuns.TypeCardEnum;
import br.com.battista.arcadiacaller.service.CampaignService;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;

public class CampaignInventoryFragment extends BaseFragment {

    private static final String TAG = CampaignInventoryFragment.class.getSimpleName();
    private final Map<String, Card> cardMap = Maps.newTreeMap();
    public Integer position = 0;
    private Campaign campaign;

    public CampaignInventoryFragment() {
    }

    public static CampaignInventoryFragment newInstance(Campaign campaign, Integer position) {
        CampaignInventoryFragment fragment = new CampaignInventoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        args.putInt(BundleConstant.POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail new campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_inventory, container, false);

        loadAllCards(GroupCardEnum.NONE, viewFragment);
        processDataFragment(viewFragment, getArguments());

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_inventory_campaign);
        final Guild guild = campaign.getGuildPosition(position + 1);
        if (guild != null && guild.getHero01() != null && guild.getHero02() != null && guild.getHero03() != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    processNextAction(viewFragment, Boolean.FALSE);
                }
            });
        } else {
            fab.setImageResource(R.drawable.ic_done);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    processNextAction(viewFragment, Boolean.TRUE);
                }
            });
        }

        return viewFragment;
    }

    public void loadAllCards(final GroupCardEnum groupCard, final View viewFragment) {
        Log.i(TAG, "loadCards: Load all cards!");

        new AsyncTask<Void, Integer, Boolean>() {

            public List<Card> cards;

            @Override
            protected void onPostExecute(Boolean result) {
                for (Card card : cards) {
                    cardMap.put(card.getKey().toUpperCase(), card);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, Lists.newArrayList(cardMap.keySet()));

                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_01)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_02)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_03)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_04)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_death_curse_01)).setAdapter(adapter);

                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_01)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_02)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_03)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_04)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_death_curse_01)).setAdapter(adapter);

                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_01)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_02)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_03)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_04)).setAdapter(adapter);
                ((AutoCompleteTextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_death_curse_01)).setAdapter(adapter);

            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String token = MainApplication.instance().getToken();
                    if (groupCard != null && !groupCard.equals(GroupCardEnum.NONE)) {
                        cards = Inject.provideCardService().findByGroup(token, groupCard);
                    } else {
                        cards = Inject.provideCardService().findAll(token);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
                return true;
            }
        }.execute();
    }

    private void loadMainActivity() {
        Bundle args = new Bundle();
        args.putString(BundleConstant.ACTION, ActionEnum.START_FRAGMENT_CAMPAIGNS.name());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.putExtras(args);

        getContext().startActivity(intent);
    }

    private void processDataFragment(View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Process bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            TextView txtAlias = (TextView) viewFragment.findViewById(R.id.view_card_campaign_inventory_alias);
            txtAlias.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            if (bundle.containsKey(BundleConstant.POSITION)) {
                position = bundle.getInt(BundleConstant.POSITION);
            } else {
                position = 1;
            }

            fillGuild(viewFragment);
        } else {
            campaign = new Campaign();
            AndroidUtils.snackbar(viewFragment, getContext().getText(R.string.msg_error_campaign_not_found).toString());
        }
    }

    private void fillGuild(View viewFragment) {
        Guild guild = campaign.getGuildPosition(position);
        if (guild != null) {
            String userGuild = guild.getUser().getUsername();
            Log.i(TAG, MessageFormat.format("processDataFragment: Fill guild position {0}!", position));
            NameGuildEnum nameGuild = guild.getName();

            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.view_card_inventory_guild_img);
            ImageLoadUtils.loadImage(getContext(), nameGuild.getUrlImg(), imageView);

            String textGuildName = getContext().getText(R.string.hint_view_inventory_guild_name).toString();
            TextView txtNameGuild = (TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_title);
            txtNameGuild.setText(MessageFormat.format(textGuildName, getContext().getText(nameGuild.getResId())));

            String textUsername = getContext().getText(R.string.hint_view_inventory_username).toString();
            TextView txtUsername = (TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_username);
            txtUsername.setText(MessageFormat.format(textUsername, userGuild));

            CheckBox chbSavedCoin = (CheckBox) viewFragment.findViewById(R.id.view_card_inventory_guild_saved_coin);
            chbSavedCoin.setChecked(guild.getSavedMoney());

            Log.i(TAG, "fillGuild: Fill data hero 01!");
            ImageView imgHero01 = (ImageView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_img);
            TextView txtHero01 = (TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_name);
            HeroGuild hero01 = guild.getHero01();
            if (hero01 != null) {
                ImageLoadUtils.loadImage(getContext(), hero01.getHero().getUrlPhoto(), imgHero01);
                txtHero01.setText(hero01.getHero().getName());

                if (hero01.getCard1() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_01)).setText(hero01.getCard1().getKey());
                }
                if (hero01.getCard2() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_02)).setText(hero01.getCard2().getKey());
                }
                if (hero01.getCard3() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_03)).setText(hero01.getCard3().getKey());
                }
                if (hero01.getCard4() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_04)).setText(hero01.getCard4().getKey());
                }
                if (hero01.getCurseCard() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_01_card_death_curse_01)).setText(hero01.getCurseCard().getKey());
                }
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero01);
                txtHero01.setText(R.string.none);
            }

            Log.i(TAG, "fillGuild: Fill data hero 02!");
            ImageView imgHero02 = (ImageView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_img);
            TextView txtHero02 = (TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_name);
            HeroGuild hero02 = guild.getHero02();
            if (hero02 != null) {
                ImageLoadUtils.loadImage(getContext(), hero02.getHero().getUrlPhoto(), imgHero02);
                txtHero02.setText(hero02.getHero().getName());

                if (hero02.getCard1() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_01)).setText(hero02.getCard1().getKey());
                }
                if (hero02.getCard2() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_02)).setText(hero02.getCard2().getKey());
                }
                if (hero02.getCard3() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_03)).setText(hero02.getCard3().getKey());
                }
                if (hero02.getCard4() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_04)).setText(hero02.getCard4().getKey());
                }
                if (hero02.getCurseCard() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_02_card_death_curse_01)).setText(hero02.getCurseCard().getKey());
                }
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero02);
                txtHero02.setText(R.string.none);
            }

            Log.i(TAG, "fillGuild: Fill data hero 03!");
            ImageView imgHero03 = (ImageView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_img);
            TextView txtHero03 = (TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_name);
            HeroGuild hero03 = guild.getHero03();
            if (hero03 != null) {
                ImageLoadUtils.loadImage(getContext(), hero03.getHero().getUrlPhoto(), imgHero03);
                txtHero03.setText(hero03.getHero().getName());

                if (hero03.getCard1() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_01)).setText(hero03.getCard1().getKey());
                }
                if (hero03.getCard2() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_02)).setText(hero03.getCard2().getKey());
                }
                if (hero03.getCard3() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_03)).setText(hero03.getCard3().getKey());
                }
                if (hero03.getCard4() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_04)).setText(hero03.getCard4().getKey());
                }
                if (hero03.getCurseCard() != null) {
                    ((TextView) viewFragment.findViewById(R.id.view_card_inventory_guild_hero_03_card_death_curse_01)).setText(hero03.getCurseCard().getKey());
                }
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero03);
                txtHero03.setText(R.string.none);
            }

        } else {
            Log.i(TAG, MessageFormat.format("processDataFragment: Hide guild position {0}!", position));
            viewFragment.findViewById(R.id.view_card_inventory_guild).setVisibility(View.GONE);
        }
    }

    private void processNextAction(View view, final Boolean lastGuild) {
        Log.d(TAG, MessageFormat.format(
                "processNextAction: Process next action -> Activity CampaignInventoryActivity -> Fragment CampaignInventoryFragment -> Position: {0}!",
                position));

        Guild guild = campaign.getGuildPosition(position);
        if (guild != null) {
            Log.i(TAG, MessageFormat.format("processNextAction: Fill guild data in campaign with position {0}!", position));
            CheckBox chbSavedCoin = (CheckBox) view.findViewById(R.id.view_card_inventory_guild_saved_coin);
            guild.setSavedMoney(chbSavedCoin.isChecked());

            Boolean hasError = Boolean.FALSE;

            Log.i(TAG, "fillGuild: Fill data hero 01 in campaign!");
            HeroGuild hero01 = guild.getHero01();
            if (hero01 != null) {
                final String keyCard01 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_01)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard01)) {
                    final Card card = cardMap.get(keyCard01);
                    if (card != null) {
                        hero01.setCard1(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_01)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard02 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_02)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard02)) {
                    final Card card = cardMap.get(keyCard02);
                    if (card != null) {
                        hero01.setCard2(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_02)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard03 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_03)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard03)) {
                    final Card card = cardMap.get(keyCard03);
                    if (card != null) {
                        hero01.setCard3(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_03)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard04 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_04)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard04)) {
                    final Card card = cardMap.get(keyCard04);
                    if (card != null) {
                        hero01.setCard4(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_upgrade_04)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCardDeathCurse = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_death_curse_01)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCardDeathCurse)) {
                    final Card card = cardMap.get(keyCardDeathCurse);
                    if (card != null && TypeCardEnum.DEATH_CURSE.equals(card.getType())) {
                        hero01.setCurseCard(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_01_card_death_curse_01)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
            }

            Log.i(TAG, "fillGuild: Fill data hero 02 in campaign!");
            HeroGuild hero02 = guild.getHero02();
            if (hero02 != null) {
                final String keyCard01 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_01)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard01)) {
                    final Card card = cardMap.get(keyCard01);
                    if (card != null) {
                        hero02.setCard1(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_01)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard02 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_02)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard02)) {
                    final Card card = cardMap.get(keyCard02);
                    if (card != null) {
                        hero02.setCard2(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_02)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard03 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_03)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard03)) {
                    final Card card = cardMap.get(keyCard03);
                    if (card != null) {
                        hero02.setCard3(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_03)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard04 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_04)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard04)) {
                    final Card card = cardMap.get(keyCard04);
                    if (card != null) {
                        hero02.setCard4(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_upgrade_04)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCardDeathCurse = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_death_curse_01)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCardDeathCurse)) {
                    final Card card = cardMap.get(keyCardDeathCurse);
                    if (card != null && TypeCardEnum.DEATH_CURSE.equals(card.getType())) {
                        hero02.setCurseCard(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_02_card_death_curse_01)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
            }

            Log.i(TAG, "fillGuild: Fill data hero 03 in campaign!");
            HeroGuild hero03 = guild.getHero03();
            if (hero03 != null) {
                final String keyCard01 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_01)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard01)) {
                    final Card card = cardMap.get(keyCard01);
                    if (card != null) {
                        hero03.setCard1(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_01)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard02 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_02)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard02)) {
                    final Card card = cardMap.get(keyCard02);
                    if (card != null) {
                        hero03.setCard2(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_02)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard03 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_03)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard03)) {
                    final Card card = cardMap.get(keyCard03);
                    if (card != null) {
                        hero03.setCard3(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_03)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCard04 = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_04)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCard04)) {
                    final Card card = cardMap.get(keyCard04);
                    if (card != null) {
                        hero03.setCard4(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_upgrade_04)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
                final String keyCardDeathCurse = ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_death_curse_01)).getText().toString().trim().toUpperCase();
                if (!Strings.isNullOrEmpty(keyCardDeathCurse)) {
                    final Card card = cardMap.get(keyCardDeathCurse);
                    if (card != null && TypeCardEnum.DEATH_CURSE.equals(card.getType())) {
                        hero03.setCurseCard(card);
                    } else {
                        hasError = Boolean.TRUE;
                        ((TextView) view.findViewById(R.id.view_card_inventory_guild_hero_03_card_death_curse_01)).setError(getString(R.string.msg_warn_key_card));
                    }
                }
            }

            if (!hasError) {
                final View currentView = view;
                new ProgressApp(getActivity(), R.string.msg_action_saving, false) {
                    @Override
                    protected void onPostExecute(Boolean result) {
                        if (result) {
                            Log.i(TAG, "onPostExecute: Success update the inventory campaign!");
                            if (lastGuild) {
                                loadMainActivity();
                            } else {
                                replaceDetailFragment(CampaignInventoryFragment.newInstance(campaign, ++position), R.id.inventory_container);
                            }
                        } else {
                            Log.i(TAG, "onPostExecute: Error update the inventory campaign!");
                            AndroidUtils.snackbar(currentView, R.string.msg_error_campaign_update_inventory);
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
            } else {
                Log.i(TAG, "onPostExecute: Error validation the inventory campaign!");
                AndroidUtils.snackbar(view, R.string.msg_error_campaign_update_inventory);
            }

        } else {
            Log.i(TAG, "onPostExecute: Error the inventory campaign!");
            AndroidUtils.snackbar(view, R.string.msg_error_campaign_update_inventory);
        }

    }
}