package forest.rice.field.k.barcodexxx.ui.recycler;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.db.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.Captor;
import forest.rice.field.k.barcodexxx.entity.CaptorMap;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.PokemonMap;
import forest.rice.field.k.barcodexxx.ui.fragment.PokemonListFragment;
import forest.rice.field.k.barcodexxx.ui.swap.SwapActivity;
import forest.rice.field.k.barcodexxx.ui.webview.WebviewActivity;
import forest.rice.field.k.barcodexxx.util.CaptorUtil;
import forest.rice.field.k.barcodexxx.util.PokemonUtil;

public class ZukanAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final LayoutInflater inflater;
    private final RequestManager glideManager;
    private final FragmentActivity context;

    public ZukanAdapter(FragmentActivity context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Pokemon pokemon;
        if (PokemonMap.POKEMON_MAP.containsKey(Integer.toString(position + 1))) {
            pokemon = PokemonMap.POKEMON_MAP.get(Integer.toString(position + 1));

            holder.noTextView.setText(PokemonUtil.getNoByStringWithFormat(pokemon));

            glideManager
//                .load("http://www.pokemon.jp/zukan/images/l/ff08ec6198db300abc91e69605469427.png")
//                .load(pokemon.getSmallImageUrl())
                    .load(PokemonUtil.getLargeImageUrl(pokemon))
//                .load(pokemon.getLargeImageUrl())
//                .placeholder(android.R.drawable.progress_horizontal)
                    .into(holder.imageView);
            holder.imageView.setVisibility(View.VISIBLE);

            holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    context.startActivity(WebviewActivity.createStartIntent(context, pokemon));
                    return true;
                }
            });

            final Captor captor = CaptorMap.CAPTOR.get(pokemon.getCaptorId());
            if (captor == null) {
                holder.nameTextView.setText(pokemon.getName());
            } else {
                holder.nameTextView.setText(CaptorUtil.getCaptorName(captor) + "の\n" + pokemon.getName());
            }

            // さがせゲーム
            if(PokemonListFragment.strayPokemon != null && pokemon.getNo() == PokemonListFragment.strayPokemon.getNo()) {
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pokemon.setCaptorId(CaptorUtil.MY_CAPTOR_ID);

                        Snackbar.make(context.findViewById(R.id.coordinatorlayout),
                                "ゲットだぜ！",
                                Snackbar.LENGTH_SHORT)
                                .show();

                        PokemonFirebaseDB db = PokemonFirebaseDB.getInstance(context);
                        db.add(pokemon);

                        PokemonListFragment.strayPokemon = null;
                    }
                });

            } else if(captor != null && !CaptorUtil.MY_CAPTOR_ID.equals(captor.getCaptorId())) {
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFragment dialogFragment = new DialogFragment() {
                            @NonNull
                            @Override
                            public Dialog onCreateDialog(Bundle savedInstanceState) {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                CharSequence[] items = {"こうかん"};
                                builder.setItems(items, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        switch (i) {
                                            case 0: {
                                                Intent intent = SwapActivity.createStartIntent(context, PokemonUtil.getNoByString(pokemon));
                                                startActivity(intent);
                                            }
                                            break;
                                            default:
                                                break;
                                        }
                                    }
                                });

                                return builder.create();
                            }
                        };

                        dialogFragment.show(context.getSupportFragmentManager(), "TAG");
                    }
                });
            } else {
                holder.imageView.setOnClickListener(null);
            }
        } else {
            holder.noTextView.setText(PokemonUtil.getNoByStringWithFormat(position + 1));
            holder.nameTextView.setText("???");
            holder.imageView.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
            holder.imageView.setVisibility(View.GONE);
        }
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 719;
    }

}
