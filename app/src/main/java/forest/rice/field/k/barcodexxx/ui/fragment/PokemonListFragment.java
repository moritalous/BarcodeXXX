package forest.rice.field.k.barcodexxx.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.ui.recycler.CaptorResultAdapter;
import forest.rice.field.k.barcodexxx.ui.recycler.ZukanAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonListFragment extends Fragment {
    private static final String ARG_PARAM1 = "pokemonMap";

//    private ArrayList<String> mPokemonNoList;
    private HashMap<String, Pokemon> mPokemonMap;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pokemonMap
     * @return A new instance of fragment PokemonListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonListFragment newInstance(HashMap<String, Pokemon> pokemonMap) {
        PokemonListFragment fragment = new PokemonListFragment();

        if (pokemonMap == null) {
            pokemonMap = new HashMap<>();
        }

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, pokemonMap);
        fragment.setArguments(args);

        return fragment;
    }


//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param pokemonNoList
//     * @return A new instance of fragment PokemonListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static PokemonListFragment newInstance(ArrayList<String> pokemonNoList) {
//        PokemonListFragment fragment = new PokemonListFragment();
//
//        if (pokemonNoList == null) {
//            pokemonNoList = new ArrayList<>();
//        }
//
//        Bundle args = new Bundle();
//        args.putStringArrayList(ARG_PARAM1, pokemonNoList);
//        fragment.setArguments(args);
//
//        return fragment;
//    }

    public static PokemonListFragment newInstance() {
        return newInstance(null);
    }

    public PokemonListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mPokemonNoList = getArguments().getStringArrayList(ARG_PARAM1);
            mPokemonMap = (HashMap<String, Pokemon>)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        if(mPokemonMap != null && mPokemonMap.size() > 0) {
            final CaptorResultAdapter adapter = new CaptorResultAdapter(getActivity(), mPokemonMap);
            recyclerView.setAdapter(adapter);
        } else {
            final ZukanAdapter adapter = new ZukanAdapter(getActivity());
            recyclerView.setAdapter(adapter);
        }

//        List<Pokemon> pokemonList = new ArrayList<>();
//
//        if (mPokemonNoList != null && mPokemonNoList.size() > 0) {
//            Pokemon p1 = new Pokemon();
//            p1.setNo(1);
//            p1.setName("フシギダネ");
//            p1.setImageUrl("ff08ec6198db300abc91e69605469427.png");
//            pokemonList.add(p1);
//
//            Pokemon p2 = new Pokemon();
//            p2.setNo(717);
//            p2.setName("イベルタル");
//            p2.setImageUrl("8585e72ad94fb68c414d913437e04eb5.png");
//            pokemonList.add(p2);
//
//            Pokemon p3 = new Pokemon();
//            p3.setNo(719);
//            p3.setName("ディアンシー");
//            p3.setImageUrl("97710b36442c58188f479b4794b2d570.png");
//            pokemonList.add(p3);
//
//            final CaptorResultAdapter adapter = new CaptorResultAdapter(getActivity(), pokemonList);
//            recyclerView.setAdapter(adapter);
//
//        } else {
//            final ZukanAdapter adapter = new ZukanAdapter(getActivity());
//            recyclerView.setAdapter(adapter);
//        }



        return view;
    }


}
