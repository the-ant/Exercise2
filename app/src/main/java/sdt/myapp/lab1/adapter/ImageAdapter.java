package sdt.myapp.lab1.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import sdt.myapp.lab1.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<Integer> listItems;
    private Context context;
    private ItemClickListener listener;

    public interface ItemClickListener {
        void onItemClick(int image);
    }

    public ImageAdapter(Context context, List<Integer> listItems, ItemClickListener listener) {
        this.listener = listener;
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_image_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int image = listItems.get(position);
        Picasso.with(context).
                load(image)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.imageView.setOnClickListener(v -> {
            listener.onItemClick(image);
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
