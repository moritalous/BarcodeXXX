package forest.rice.field.k.barcodexxx.ui.ranking;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.db.PokemonFirebaseDB;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.entity.Ranking;
import forest.rice.field.k.barcodexxx.entity.RankingList;
import forest.rice.field.k.barcodexxx.util.PokemonUtil;
import forest.rice.field.k.barcodexxx.util.RankingUtil;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * interface.
 */
public class RankinkgFragment extends ListFragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types of parameters
    public static RankinkgFragment newInstance(String param1, String param2) {
        RankinkgFragment fragment = new RankinkgFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RankinkgFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setListAdapter(new MyListArrayAdapter(getActivity(), RankingUtil.getRankingList()));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    class MyListArrayAdapter extends ArrayAdapter<Ranking> {

        private LayoutInflater layoutInflater_;

        public MyListArrayAdapter(Context context, RankingList rankingList) {
            super(context, 0, rankingList);
            layoutInflater_ = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                convertView = layoutInflater_.inflate(
                        R.layout.ranking_row, null);
                holder = new ViewHolder();

                holder.textName = (TextView) convertView.findViewById(R.id.text);
                holder.textCount = (TextView) convertView.findViewById(R.id.text2);
                holder.gridView = (GridView) convertView.findViewById(R.id.grid_view);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Ranking item = getItem(position);

            if (item.getCaptor() != null) {
                holder.textName.setText(item.getCaptor().getCaptorName());
            } else {
                holder.textName.setText("やせいのポケモン");
            }
            holder.textCount.setText(item.getPokemonList().size() + "ひき");

            holder.gridView.setAdapter(new MyGridArrayAdapter(getActivity(), item.getPokemonList()));

            return convertView;
        }
    }

    class ViewHolder {
        public TextView textName;
        public TextView textCount;
        public GridView gridView;
    }


    class MyGridArrayAdapter extends ArrayAdapter<Pokemon> {
        private LayoutInflater layoutInflater_;
        private final RequestManager glideManager;

        public MyGridArrayAdapter(Context context, ArrayList<Pokemon> pokemonList) {
            super(context, 0, pokemonList);
            layoutInflater_ = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            glideManager = Glide.with(context);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final GridViewHolder holder;

            if (convertView == null) {
                convertView = layoutInflater_.inflate(
                        R.layout.ranking_grid_row, null);
                holder = new GridViewHolder();

                holder.imageView = (ImageView) convertView.findViewById(R.id.image);

                convertView.setTag(holder);
            } else {
                holder = (GridViewHolder) convertView.getTag();
            }

            Pokemon item = getItem(position);

            if("".equals(item.getCaptorId())) {
                item.setCaptorId(null);
                PokemonFirebaseDB db = PokemonFirebaseDB.getInstance(getActivity());
                db.add(item);
            }

            glideManager
//                .load("http://www.pokemon.jp/zukan/images/l/ff08ec6198db300abc91e69605469427.png")
//                .load(pokemon.getSmallImageUrl())
                    .load(PokemonUtil.getSmallImageUrl(item))
//                .load(pokemon.getLargeImageUrl())
//                .placeholder(android.R.drawable.progress_horizontal)
                        .into(holder.imageView);
            holder.imageView.setVisibility(View.VISIBLE);


            return convertView;
        }
    }

    class GridViewHolder {
        public ImageView imageView;


    }
}
