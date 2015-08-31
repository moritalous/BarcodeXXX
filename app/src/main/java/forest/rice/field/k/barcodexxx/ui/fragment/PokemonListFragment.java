package forest.rice.field.k.barcodexxx.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.eventbus.Subscribe;

import java.util.HashMap;

import forest.rice.field.k.barcodexxx.R;
import forest.rice.field.k.barcodexxx.entity.Pokemon;
import forest.rice.field.k.barcodexxx.eventbus.EventBusManager;
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

    private RecyclerView recyclerView;

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
            mPokemonMap = (HashMap<String, Pokemon>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        if (mPokemonMap != null && mPokemonMap.size() > 0) {
            final CaptorResultAdapter adapter = new CaptorResultAdapter(getActivity(), mPokemonMap);
            recyclerView.setAdapter(adapter);
        } else {
            final ZukanAdapter adapter = new ZukanAdapter(getActivity());
            recyclerView.setAdapter(adapter);

            setHasOptionsMenu(true);

        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        recyclerView.getAdapter().notifyDataSetChanged();

        EventBusManager.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBusManager.getEventBus().unregister(this);
    }

    @Subscribe
    public void event(EventBusManager.Event event) {
        if(recyclerView != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void scroll() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuScrollToEnd = menu.add("toEnd");
        menuScrollToEnd.setIcon(android.R.drawable.arrow_down_float);
        MenuItemCompat.setShowAsAction(menuScrollToEnd, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        MenuItem menuScrollToTop = menu.add("toTop");
        menuScrollToTop.setIcon(android.R.drawable.arrow_up_float);
        MenuItemCompat.setShowAsAction(menuScrollToTop, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "toTop":
                recyclerView.smoothScrollToPosition(0);
                break;
            case "toEnd":
                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
                break;

        }

        return true;
    }
}
