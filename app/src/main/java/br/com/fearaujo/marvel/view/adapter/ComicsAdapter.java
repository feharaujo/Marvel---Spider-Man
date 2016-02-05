package br.com.fearaujo.marvel.view.adapter;

import android.app.Activity;
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

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder>{

    private List<Result> results;
    private IImageLoader imageLoader;

    public ComicsAdapter(List<Result> results, Activity context, IImageLoader imageLoader){
        this.results = results;
        this.imageLoader = imageLoader;

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
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //api.saleDetails(purchases.get(getAdapterPosition()), getAdapterPosition());
        }
    }

}
