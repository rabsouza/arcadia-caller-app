package br.com.battista.arcadiacaller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import br.com.battista.arcadiacaller.Inject;
import br.com.battista.arcadiacaller.MainApplication;
import br.com.battista.arcadiacaller.R;
import br.com.battista.arcadiacaller.model.StatisticUser;
import br.com.battista.arcadiacaller.model.User;
import br.com.battista.arcadiacaller.util.AppUtils;
import br.com.battista.arcadiacaller.util.DateUtils;
import br.com.battista.arcadiacaller.util.ImageLoadUtils;
import br.com.battista.arcadiacaller.util.ProgressApp;


public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Create new fragment Home!");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MainApplication application = MainApplication.instance();
        AppUtils.goToHomeIfUserIsNull(application, getContext());
        User user = application.getUser();
        loadUserInfo(view, user);
        loadStatisticUser(view, application);
        return view;
    }

    private void loadStatisticUser(final View view, MainApplication application) {

        final User user = application.getUser();
        final String token = application.getToken();

        Log.i(TAG, "loadStatisticUser: Load statistic user!");
        new ProgressApp(this.getActivity(), R.string.msg_action_loading, false) {
            private StatisticUser statisticUser;

            @Override
            protected void onPostExecute(Boolean result) {
                if (statisticUser == null) {
                    statisticUser = new StatisticUser();
                    statisticUser.initializeStatistic();
                }
                Log.i(TAG, "onPostExecute: Load all statistic user to campaigns!");
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_total)).setText(String.valueOf(statisticUser.getCampaigns()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_guilds)).setText(String.valueOf(statisticUser.getGuilds()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_completed)).setText(String.valueOf(statisticUser.getCompleteds()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_winners)).setText(String.valueOf(statisticUser.getCampaignWinners()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_defeats)).setText(String.valueOf(statisticUser.getCampaignDefeats()));

                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_medals_winners)).setText(String.valueOf(statisticUser.getCampaignMedalsWinners()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_medals_least_deaths)).setText(String.valueOf(statisticUser.getCampaignMedalsLeastDeaths()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_medals_most_coins)).setText(String.valueOf(statisticUser.getCampaignMedalsMostCoins()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_medals_won_rewards)).setText(String.valueOf(statisticUser.getCampaignMedalsWonRewards()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_campaigns_medals_won_titles)).setText(String.valueOf(statisticUser.getCampaignMedalsWonTitles()));

                Log.i(TAG, "onPostExecute: Load all statistic user to sceneries!");
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_total)).setText(String.valueOf(statisticUser.getSceneries()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_winners)).setText(String.valueOf(statisticUser.getSceneryWinners()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_defeats)).setText(String.valueOf(statisticUser.getSceneryDefeats()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_least_deaths)).setText(String.valueOf(statisticUser.getSceneryLeastDeaths()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_most_coins)).setText(String.valueOf(statisticUser.getSceneryMostCoins()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_won_rewards)).setText(String.valueOf(statisticUser.getSceneryWonRewards()));
                ((TextView) view.findViewById(R.id.card_view_home_statistic_sceneries_won_titles)).setText(String.valueOf(statisticUser.getSceneryWonTitles()));

                dismissProgress();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    statisticUser = Inject.provideStatisticUserService().findByUser(token, user.getUsername());
                    return true;
                } catch (Exception e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                    return false;
                }
            }
        }.execute();

    }

    private void loadUserInfo(View view, User user) {
        Log.i(TAG, "onCreateView: Load all data to User!");
        TextView txtUsername = (TextView) view.findViewById(R.id.card_view_home_username);
        txtUsername.setText(user.getUsername());

        TextView txtMail = (TextView) view.findViewById(R.id.card_view_home_mail);
        txtMail.setText(user.getMail());

        TextView txtCreated = (TextView) view.findViewById(R.id.card_view_home_created);
        Calendar created = Calendar.getInstance();
        created.setTime(user.getCreatedAt());
        txtCreated.setText(DateUtils.format(created));

        ImageView imageViewImg = (ImageView) view.findViewById(R.id.card_view_home_img);
        if (imageViewImg != null) {

            ImageLoadUtils
                    .loadImageWithImageError(getContext(),
                            user.getUrlAvatar(),
                            imageViewImg,
                            R.drawable.profile);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
