package com.example.android.project3_baking;

import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.project3_baking.Adapter.IngredientAdapter;
import com.example.android.project3_baking.Model.Ingredient;
import com.example.android.project3_baking.Model.Step;
import com.example.android.project3_baking.Utils.Network;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MasterRecipeStepDetailFragment extends Fragment implements ExoPlayer.EventListener{

    private TextView txtDescription;
    private TextView txtNoInternet;
    private TextView txtNoVideo;
    private RecyclerView rv_Ingredients;
    private boolean statusClickIngredient = false;
    private Step step;
    private Ingredient[] ingredients;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private PlaybackState.Builder mBuilder;
    private static MediaSession mMediaSession;

    public MasterRecipeStepDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        txtDescription = view.findViewById(R.id.txt_description);
        txtNoInternet = view.findViewById(R.id.txt_warning_recipe_detail);
        txtNoVideo = view.findViewById(R.id.txt_warning_recipe_detail_video);
        simpleExoPlayerView = view.findViewById(R.id.player_recipe_step);
        rv_Ingredients = view.findViewById(R.id.rv_ingredients);

        if (statusClickIngredient){

            rv_Ingredients.setVisibility(View.VISIBLE);
            txtDescription.setVisibility(View.GONE);
            txtNoInternet.setVisibility(View.GONE);
            txtNoVideo.setVisibility(View.GONE);
            simpleExoPlayerView.setVisibility(View.GONE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

            rv_Ingredients.setLayoutManager(linearLayoutManager);

            IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext());
            ingredientAdapter.setIngredients(ingredients);

            rv_Ingredients.setAdapter(ingredientAdapter);

        } else {

            rv_Ingredients.setVisibility(View.GONE);
            txtDescription.setText(step.getDescription());

            if (step.getVideoURL().isEmpty()) {
                txtNoVideo.setVisibility(View.VISIBLE);
                simpleExoPlayerView.setVisibility(View.GONE);
            } else {
                txtNoVideo.setVisibility(View.GONE);
                simpleExoPlayerView.setVisibility(View.VISIBLE);

                if (Network.verifyConnection(getContext())) {
                    txtNoInternet.setVisibility(View.GONE);
                    simpleExoPlayerView.setVisibility(View.VISIBLE);

                    initializePlayer(Uri.parse(step.getVideoURL()));
                } else {
                    txtNoInternet.setVisibility(View.VISIBLE);
                    simpleExoPlayerView.setVisibility(View.GONE);
                }
            }
        }

        return view;
    }

    public void setStep(Step step){
        this.step = step;
    }

    public void setIngredient(Ingredient[] ingredients){ this.ingredients = ingredients; }

    public void setStatusClickIngredient(boolean status){ this.statusClickIngredient = status; }

    public void initializePlayer(Uri media){
        if (mExoPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getActivity(), "baking");
            MediaSource mediaSource = new ExtractorMediaSource(media, new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            mBuilder.setState(PlaybackState.STATE_PLAYING, mExoPlayer.getCurrentPosition(), 1f);
        }else if((playbackState == ExoPlayer.STATE_READY)){
            mBuilder.setState(PlaybackState.STATE_PAUSED, mExoPlayer.getCurrentPosition(), 1f);
        }
//        mMediaSession.setPlaybackState(mBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
