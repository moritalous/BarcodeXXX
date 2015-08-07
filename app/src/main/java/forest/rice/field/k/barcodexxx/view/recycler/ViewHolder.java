package forest.rice.field.k.barcodexxx.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import forest.rice.field.k.barcodexxx.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.recycler_row;

    public final TextView textView;
    public final ImageView imageView;

    public ViewHolder(View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(R.id.text);
        imageView = (ImageView)itemView.findViewById(R.id.image_view);
    }
}
