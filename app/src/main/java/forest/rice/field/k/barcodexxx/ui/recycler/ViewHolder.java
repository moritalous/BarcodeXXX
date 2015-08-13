package forest.rice.field.k.barcodexxx.ui.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import forest.rice.field.k.barcodexxx.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.recycler_row;

    public final TextView noTextView;
    public final ImageView imageView;
    public final TextView nameTextView;
    public final CardView cardView;

    public ViewHolder(View itemView) {
        super(itemView);
        noTextView = (TextView)itemView.findViewById(R.id.text_no);
        imageView = (ImageView)itemView.findViewById(R.id.image_view);
        nameTextView = (TextView)itemView.findViewById(R.id.text_name);
        cardView = (CardView)itemView.findViewById(R.id.card_view);
    }
}
