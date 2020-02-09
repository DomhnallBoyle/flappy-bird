package com.domhnall_boyle.flappy_bird.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.domhnall_boyle.flappy_bird.R;
import com.domhnall_boyle.flappy_bird.databinding.ListItemLeaderboardEntryBinding;
import com.domhnall_boyle.flappy_bird.engine.rest.repositories.UserRespository;
import com.domhnall_boyle.flappy_bird.engine.rest.responses.UserResponse;
import com.domhnall_boyle.flappy_bird.game.Game;
import com.domhnall_boyle.flappy_bird.models.User;
import com.domhnall_boyle.flappy_bird.utilities.MyApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderboardFragment extends GameFragment {

    @BindView(R.id.highscores)
    RecyclerView recyclerView;

    private LeaderboardAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public LeaderboardFragment(Game game) {
        super(game);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        ButterKnife.bind(this, view);

        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        this.adapter = new LeaderboardAdapter();
        this.recyclerView.setAdapter(this.adapter);

        if (User.getInstance().isLoggedIn()) {
            new UserRespository().getHighScores().observe(this, topPlayers -> {
                if (topPlayers != null) {
                    this.adapter.setTopPlayers(topPlayers);
                }
            });
        } else {
            MyApplication.showMessage("You must be logged-in to view the global leaderboard");
        }

        return view;
    }

    public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.MyViewHolder> {
        private List<UserResponse> topPlayers;

        private class MyViewHolder extends RecyclerView.ViewHolder {
            ListItemLeaderboardEntryBinding binding;

            private MyViewHolder(ListItemLeaderboardEntryBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(UserResponse userResponse, int position) {
                this.binding.setPlayer(userResponse);
                this.binding.playerPosition.setText(String.valueOf(position));
            }
        }

        private void setTopPlayers(List<UserResponse> topPlayers) {
            this.topPlayers = topPlayers;
            notifyDataSetChanged();
        }

        @Override
        public LeaderboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ListItemLeaderboardEntryBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.list_item_leaderboard_entry,
                    parent,
                    false);

            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(this.topPlayers.get(position), position + 1);
        }

        @Override
        public int getItemCount() {
            return this.topPlayers == null ? 0 : this.topPlayers.size();
        }
    }
}
