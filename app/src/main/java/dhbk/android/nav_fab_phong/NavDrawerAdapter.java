package dhbk.android.nav_fab_phong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by huynhducthanhphong on 2/2/16.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.NavViewHolder>{
    private final Context context;
    private final List<NavDrawerItem> items;
    private final LayoutInflater inflater;
    private RecyclerViewItemClickListener onItemClickListener;

    public NavDrawerAdapter(Context context, List<NavDrawerItem> items) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    // return view của từng hàng của list (chưa gắn data)
    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        return new NavViewHolder(view);
    }

    // gán data cho list
    @Override
    public void onBindViewHolder(NavViewHolder holder, final int position) {
        NavDrawerItem item = items.get(position);
        holder.name.setText(item.name);
        holder.icon.setImageResource(item.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class NavViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;

        public NavViewHolder(View view) {
            super(view);
            icon = (ImageView) view.findViewById(R.id.nav_item_icon);
            name = (TextView) view.findViewById(R.id.nav_item_name);
        }
    }
}
