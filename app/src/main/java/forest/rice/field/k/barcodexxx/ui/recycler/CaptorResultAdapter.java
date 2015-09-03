package forest.rice.field.k.barcodexxx.ui.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.HashMap;

import forest.rice.field.k.barcodexxx.entity.Captor;
import forest.rice.field.k.barcodexxx.entity.CaptorMap;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonNew;
import forest.rice.field.k.barcodexxx.entity.PokemonReGet;
import forest.rice.field.k.barcodexxx.util.CaptorUtil;
import forest.rice.field.k.barcodexxx.util.PokemonUtil;

public class CaptorResultAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final HashMap<String, Pokemon> pokemonMap;
    private final RequestManager glideManager;


    public CaptorResultAdapter(Context context, HashMap<String, Pokemon> pokemonMap) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.pokemonMap = pokemonMap;
        glideManager = Glide.with(context);
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(ViewHolder.LAYOUT_ID, parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should bDie updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Pokemon pokemon = pokemonMap.get(Integer.toString(position + 1));
        if (pokemon != null) {
            holder.noTextView.setText(PokemonUtil.getNoByStringWithFormat(pokemon));

            final Captor captor = CaptorMap.CAPTOR.get(pokemon.getCaptorId());
            if (captor == null) {
                holder.nameTextView.setText("やせいのポケモン\n" + pokemon.getName());
                holder.imageView.setOnClickListener(null);
            } else {
                holder.nameTextView.setText(CaptorUtil.getCaptorName(captor) + "の\n" + pokemon.getName());
            }

            if (pokemon instanceof PokemonNew) {
                holder.newTextView.setText("NEW!!");
                holder.newTextView.setVisibility(View.VISIBLE);
            } else if (pokemon instanceof PokemonReGet) {
                holder.newTextView.setText("ほかく！");
                holder.newTextView.setVisibility(View.VISIBLE);
            } else {
                holder.newTextView.setVisibility(View.GONE);
            }

            glideManager
//                .load("http://www.pokemon.jp/zukan/images/l/ff08ec6198db300abc91e69605469427.png")
//                .load(pokemon.getSmallImageUrl())
                    .load(PokemonUtil.getLargeImageUrl(pokemon))
//                .load(pokemon.getLargeImageUrl())
//                .placeholder(android.R.drawable.progress_horizontal)
                    .into(holder.imageView);
        }
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return pokemonMap.size();
    }

}
