package in.karan.suman.foodkaserver.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import in.karan.suman.foodkaserver.Common.Common;
import in.karan.suman.foodkaserver.Interface.ItemClickListener;
import in.karan.suman.foodkaserver.R;

/**
 * Created by Suman on 15-Dec-17.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener,View.OnCreateContextMenuListener{


    public TextView txtMenuName;
    public ImageView imageView;


    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView) itemView.findViewById(R.id.menu_image);
        txtMenuName=(TextView) itemView.findViewById(R.id.menu_name);


        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Select the action");
        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);

    }
}
