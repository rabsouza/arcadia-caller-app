package br.com.battista.arcadiacaller.fragment.view;


import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.BLUE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.GREEN;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.ORANGE;
import static br.com.battista.arcadiacaller.model.enuns.NameGuildEnum.RED;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Strings;

import java.text.MessageFormat;

import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.constants.BundleConstant;
import br.com.battista.arcadiacaller.fragment.BaseFragment;
import br.com.battista.arcadiacaller.model.Campaign;
import br.com.battista.arcadiacaller.model.Guild;
import br.com.battista.arcadiacaller.model.HeroGuild;
import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
import br.com.battista.arcadiacaller.util.AndroidUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;

public class CampaignViewGuildsFragment extends BaseFragment {

    private static final String TAG = CampaignViewGuildsFragment.class.getSimpleName();

    private Campaign campaign;

    public CampaignViewGuildsFragment() {
    }

    public static CampaignViewGuildsFragment newInstance(Campaign campaign) {
        CampaignViewGuildsFragment fragment = new CampaignViewGuildsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BundleConstant.DATA, campaign);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: Create detail new campaign!");
        final View viewFragment = inflater.inflate(R.layout.fragment_campaign_view_guilds, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewFragment.findViewById(R.id.fab_next_view_scenery);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processNextAction(viewFragment);
            }
        });

        processDataFragment(viewFragment, getArguments());

        return viewFragment;
    }

    private void processDataFragment(View viewFragment, Bundle bundle) {
        Log.d(TAG, "processDataFragment: Processs bundle data Fragment!");
        if (bundle.containsKey(BundleConstant.DATA)) {
            campaign = (Campaign) bundle.getSerializable(BundleConstant.DATA);

            TextView txtAlias = (TextView) viewFragment.findViewById(R.id.view_card_view_campaign_alias);
            txtAlias.setText(MessageFormat.format("{0} - {1}", campaign.getKey(), campaign.getAlias()));

            fillGuildBlue(viewFragment);
            fillGuildGreen(viewFragment);
            fillGuildRed(viewFragment);
            fillGuildOrange(viewFragment);

        }
    }

    private void fillGuildOrange(View viewFragment) {
        Guild guildOrange = campaign.getHeroesGuild04();
        String userGuildOrange = campaign.getGuild04();
        if (!Strings.isNullOrEmpty(userGuildOrange) && guildOrange != null) {
            Log.i(TAG, "processDataFragment: Fill guild Orange!");
            NameGuildEnum nameGuild = ORANGE;

            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_orange_img);
            ImageLoadUtils.loadImage(getContext(), nameGuild.getUrlImg(), imageView);

            String textGuildName = getContext().getText(R.string.hint_view_card_guild_name).toString();
            TextView txtNameGuild = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_orange_title);
            txtNameGuild.setText(MessageFormat.format(textGuildName, getContext().getText(nameGuild.getResId())));

            String textUsername = getContext().getText(R.string.hint_view_card_username).toString();
            TextView txtUsername = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_orange_username);
            txtUsername.setText(MessageFormat.format(textUsername, userGuildOrange));

            ImageView imgHero01 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_orange_hero_01_img);
            TextView txtHero01 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_orange_hero_01_name);
            HeroGuild hero01 = guildOrange.getHero01();
            if (hero01 != null) {
                ImageLoadUtils.loadImage(getContext(), hero01.getHero().getUrlPhoto(), imgHero01);
                txtHero01.setText(hero01.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero01);
                txtHero01.setText(R.string.none);
            }

            ImageView imgHero02 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_orange_hero_02_img);
            TextView txtHero02 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_orange_hero_02_name);
            HeroGuild hero02 = guildOrange.getHero02();
            if (hero02 != null) {
                ImageLoadUtils.loadImage(getContext(), hero02.getHero().getUrlPhoto(), imgHero02);
                txtHero02.setText(hero02.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero02);
                txtHero02.setText(R.string.none);
            }

            ImageView imgHero03 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_orange_hero_03_img);
            TextView txtHero03 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_orange_hero_03_name);
            HeroGuild hero03 = guildOrange.getHero03();
            if (hero03 != null) {
                ImageLoadUtils.loadImage(getContext(), hero03.getHero().getUrlPhoto(), imgHero03);
                txtHero03.setText(hero03.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero03);
                txtHero03.setText(R.string.none);
            }

        } else {
            Log.i(TAG, "processDataFragment: Hide guild Orange!");
            viewFragment.findViewById(R.id.view_card_view_guild_orange).setVisibility(View.GONE);
        }
    }

    private void fillGuildRed(View viewFragment) {
        Guild guildRed = campaign.getHeroesGuild03();
        String userGuildRed = campaign.getGuild03();
        if (!Strings.isNullOrEmpty(userGuildRed) && guildRed != null) {
            Log.i(TAG, "processDataFragment: Fill guild Red!");
            NameGuildEnum nameGuild = RED;

            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_red_img);
            ImageLoadUtils.loadImage(getContext(), nameGuild.getUrlImg(), imageView);

            String textGuildName = getContext().getText(R.string.hint_view_card_guild_name).toString();
            TextView txtNameGuild = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_red_title);
            txtNameGuild.setText(MessageFormat.format(textGuildName, getContext().getText(nameGuild.getResId())));

            String textUsername = getContext().getText(R.string.hint_view_card_username).toString();
            TextView txtUsername = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_red_username);
            txtUsername.setText(MessageFormat.format(textUsername, userGuildRed));

            ImageView imgHero01 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_red_hero_01_img);
            TextView txtHero01 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_red_hero_01_name);
            HeroGuild hero01 = guildRed.getHero01();
            if (hero01 != null) {
                ImageLoadUtils.loadImage(getContext(), hero01.getHero().getUrlPhoto(), imgHero01);
                txtHero01.setText(hero01.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero01);
                txtHero01.setText(R.string.none);
            }

            ImageView imgHero02 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_red_hero_02_img);
            TextView txtHero02 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_red_hero_02_name);
            HeroGuild hero02 = guildRed.getHero02();
            if (hero02 != null) {
                ImageLoadUtils.loadImage(getContext(), hero02.getHero().getUrlPhoto(), imgHero02);
                txtHero02.setText(hero02.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero02);
                txtHero02.setText(R.string.none);
            }

            ImageView imgHero03 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_red_hero_03_img);
            TextView txtHero03 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_red_hero_03_name);
            HeroGuild hero03 = guildRed.getHero03();
            if (hero03 != null) {
                ImageLoadUtils.loadImage(getContext(), hero03.getHero().getUrlPhoto(), imgHero03);
                txtHero03.setText(hero03.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero03);
                txtHero03.setText(R.string.none);
            }

        } else {
            Log.i(TAG, "processDataFragment: Hide guild Red!");
            viewFragment.findViewById(R.id.view_card_view_guild_red).setVisibility(View.GONE);
        }
    }

    private void fillGuildGreen(View viewFragment) {
        Guild guildGreen = campaign.getHeroesGuild02();
        String userGuildGreen = campaign.getGuild02();
        if (!Strings.isNullOrEmpty(userGuildGreen) && guildGreen != null) {
            Log.i(TAG, "processDataFragment: Fill guild Green!");
            NameGuildEnum nameGuild = GREEN;

            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_green_img);
            ImageLoadUtils.loadImage(getContext(), nameGuild.getUrlImg(), imageView);

            String textGuildName = getContext().getText(R.string.hint_view_card_guild_name).toString();
            TextView txtNameGuild = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_green_title);
            txtNameGuild.setText(MessageFormat.format(textGuildName, getContext().getText(nameGuild.getResId())));

            String textUsername = getContext().getText(R.string.hint_view_card_username).toString();
            TextView txtUsername = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_green_username);
            txtUsername.setText(MessageFormat.format(textUsername, userGuildGreen));

            ImageView imgHero01 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_green_hero_01_img);
            TextView txtHero01 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_green_hero_01_name);
            HeroGuild hero01 = guildGreen.getHero01();
            if (hero01 != null) {
                ImageLoadUtils.loadImage(getContext(), hero01.getHero().getUrlPhoto(), imgHero01);
                txtHero01.setText(hero01.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero01);
                txtHero01.setText(R.string.none);
            }

            ImageView imgHero02 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_green_hero_02_img);
            TextView txtHero02 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_green_hero_02_name);
            HeroGuild hero02 = guildGreen.getHero02();
            if (hero02 != null) {
                ImageLoadUtils.loadImage(getContext(), hero02.getHero().getUrlPhoto(), imgHero02);
                txtHero02.setText(hero02.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero02);
                txtHero02.setText(R.string.none);
            }

            ImageView imgHero03 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_green_hero_03_img);
            TextView txtHero03 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_green_hero_03_name);
            HeroGuild hero03 = guildGreen.getHero03();
            if (hero03 != null) {
                ImageLoadUtils.loadImage(getContext(), hero03.getHero().getUrlPhoto(), imgHero03);
                txtHero03.setText(hero03.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero03);
                txtHero03.setText(R.string.none);
            }

        } else {
            Log.i(TAG, "processDataFragment: Hide guild Green!");
            viewFragment.findViewById(R.id.view_card_view_guild_green).setVisibility(View.GONE);
        }
    }

    private void fillGuildBlue(View viewFragment) {
        Guild guildBlue = campaign.getHeroesGuild01();
        String userGuildBlue = campaign.getGuild01();
        if (!Strings.isNullOrEmpty(userGuildBlue) && guildBlue != null) {
            Log.i(TAG, "processDataFragment: Fill guild Blue!");
            NameGuildEnum nameGuild = BLUE;

            ImageView imageView = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_blue_img);
            ImageLoadUtils.loadImage(getContext(), nameGuild.getUrlImg(), imageView);

            String textGuildName = getContext().getText(R.string.hint_view_card_guild_name).toString();
            TextView txtNameGuild = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_blue_title);
            txtNameGuild.setText(MessageFormat.format(textGuildName, getContext().getText(nameGuild.getResId())));

            String textUsername = getContext().getText(R.string.hint_view_card_username).toString();
            TextView txtUsername = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_blue_username);
            txtUsername.setText(MessageFormat.format(textUsername, userGuildBlue));

            ImageView imgHero01 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_blue_hero_01_img);
            TextView txtHero01 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_blue_hero_01_name);
            HeroGuild hero01 = guildBlue.getHero01();
            if (hero01 != null) {
                ImageLoadUtils.loadImage(getContext(), hero01.getHero().getUrlPhoto(), imgHero01);
                txtHero01.setText(hero01.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero01);
                txtHero01.setText(R.string.none);
            }

            ImageView imgHero02 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_blue_hero_02_img);
            TextView txtHero02 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_blue_hero_02_name);
            HeroGuild hero02 = guildBlue.getHero02();
            if (hero02 != null) {
                ImageLoadUtils.loadImage(getContext(), hero02.getHero().getUrlPhoto(), imgHero02);
                txtHero02.setText(hero02.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero02);
                txtHero02.setText(R.string.none);
            }

            ImageView imgHero03 = (ImageView) viewFragment.findViewById(R.id.view_card_view_guild_blue_hero_03_img);
            TextView txtHero03 = (TextView) viewFragment.findViewById(R.id.view_card_view_guild_blue_hero_03_name);
            HeroGuild hero03 = guildBlue.getHero03();
            if (hero03 != null) {
                ImageLoadUtils.loadImage(getContext(), hero03.getHero().getUrlPhoto(), imgHero03);
                txtHero03.setText(hero03.getHero().getName());
            } else {
                ImageLoadUtils.loadImage(getContext(), R.drawable.hero_blank, imgHero03);
                txtHero03.setText(R.string.none);
            }

        } else {
            Log.i(TAG, "processDataFragment: Hide guild Blue!");
            viewFragment.findViewById(R.id.view_card_view_guild_blue).setVisibility(View.GONE);
        }
    }

    private void processNextAction(View view) {
        Log.d(TAG, "processNextAction: Process next action -> Fragment CampaignDetailGuildsFragment!");

        AndroidUtils.snackbar(view, R.string.msg_blank_fragment);
    }

}
