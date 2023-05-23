package com.example.shortsclone;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {
    private List<Post> postList;
    private Context context;

    public PagerAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagerAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tv_handle.setText(post.getCreator().getHandle());
        holder.tv_like.setText(String.valueOf(post.getReaction().getCount()));
        holder.tv_comment.setText(String.valueOf(post.getComment().getCount()));
        holder.videoView.setVideoURI(Uri.parse(post.getSubmission().getMediaUrl()));
        holder.tv_title.setText(post.getSubmission().getTitle());
        holder.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(post.getSubmission().getHyperlink()));
                context.startActivity(browserIntent);
            }
        });
        Glide.with(context).load(Uri.parse(post.getSubmission().getThumbnail())).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.iv_thumb);
        Glide.with(context).load(Uri.parse(post.getCreator().getPic())).placeholder(R.drawable.profile_user).error(R.drawable.profile_user).into(holder.iv_pic);
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                holder.iv_thumb.setVisibility(View.GONE);
                mediaPlayer.start();
                //Scaling Video
                float vidRatio = mediaPlayer.getVideoWidth()/(float)mediaPlayer.getVideoHeight();
                float screenRatio = holder.videoView.getWidth()/(float)holder.videoView.getHeight();
                float scale = vidRatio/screenRatio;
                if(scale>=1){
                    holder.videoView.setScaleX(scale);
                } else {
                    holder.videoView.setScaleY(1f/scale);
                }
            }
        });
        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setData(List<Post> posts){
        int positionStart = postList.size()+1;
        postList.addAll(posts);
        notifyItemRangeInserted(positionStart, posts.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
         VideoView videoView;
         ProgressBar progressBar;
         TextView tv_like;
         TextView tv_comment;
         ImageView iv_pic;
         TextView tv_handle;
         ImageView iv_thumb;
         TextView tv_title;
         Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            progressBar = itemView.findViewById(R.id.prog);
            tv_like = itemView.findViewById(R.id.tv_likes);
            tv_comment = itemView.findViewById(R.id.tv_comments);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_handle = itemView.findViewById(R.id.tv_handle);
            iv_thumb = itemView.findViewById(R.id.iv_thumbnail);
            tv_title = itemView.findViewById(R.id.tv_title);
            button = itemView.findViewById(R.id.button);
        }
    }
}
