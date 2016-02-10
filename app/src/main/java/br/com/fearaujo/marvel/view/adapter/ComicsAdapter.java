package br.com.fearaujo.marvel.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.fearaujo.marvel.R;
import br.com.fearaujo.marvel.model.Result;
import br.com.fearaujo.marvel.service.IImageLoader;
import br.com.fearaujo.marvel.view.activity.DetailsActivity_;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder>{

    private List<Result> results;
    private IImageLoader imageLoader;
    private Activity context;

    public ComicsAdapter(List<Result> results, Activity context, IImageLoader imageLoader){
        this.results = results;
        this.imageLoader = imageLoader;
        this.context = context;

        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        String uri = result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension();

        holder.tvIssueNumber.setText("#" + String.valueOf(result.getIssueNumber()));
        imageLoader.loadImage(uri, holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvIssueNumber;
        public ImageView ivThumbnail;
        //private IExtract api;

        public ViewHolder(View itemView)  {
            super(itemView);
            this.tvIssueNumber = (TextView) itemView.findViewById(R.id.tvIssueNumber);
            this.ivThumbnail = (ImageView) itemView.findViewById(R.id.ivComic);

            //api = (IExtract) activity;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailsActivity_.class);
            intent.putExtra("result", results.get(getAdapterPosition()));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                    Pair.create(v.findViewById(R.id.linearComicRoot), "linearComicRoot"));
            ActivityCompat.startActivity(context, intent, options.toBundle());
        }
    }

}
