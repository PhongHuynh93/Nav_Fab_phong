package dhbk.android.nav_fab_phong;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {
    private View container;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private GetNavItemsCallback getNavItemsCallback;

    private List<NavDrawerItem> navItems;
    private RecyclerViewItemClickListener itemClickListener;
    private RecyclerView drawerList;
    private NavDrawerAdapter drawerAdapter;

    public void closeNavDrawer() {
        drawerLayout.closeDrawer(container);
    }


    public interface GetNavItemsCallback {
        List<NavDrawerItem> getNavItems();
        RecyclerViewItemClickListener getItemClickListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;
        if (context instanceof Activity) {
            a = (Activity) context;

            try {
                getNavItemsCallback = (GetNavItemsCallback) a;
                navItems = getNavItemsCallback.getNavItems();
                itemClickListener = getNavItemsCallback.getItemClickListener();
            } catch (ClassCastException e) {
                throw new ClassCastException(a.toString() + " must implement getNavItemsCallback() + getItemClickListener()");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // return root of this fragment = drawer layout
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);

        // declare list view in navigation drawer
        drawerList = (RecyclerView)view.findViewById(R.id.drawerList);
        drawerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        drawerAdapter = new NavDrawerAdapter(getActivity().getBaseContext(), navItems);
        drawerList.setAdapter(drawerAdapter);
        drawerAdapter.setOnItemClickListener(itemClickListener);

        // Inflate the layout for this fragment
        return view;
    }

    // listen open and close event
    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        final Activity activity = getActivity();
        container = activity.findViewById(fragmentId);
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(activity, this.drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                activity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        this.drawerLayout.setDrawerListener(drawerToggle);
        // auto change indicator on the toolbar when open and close navifation drawer
        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

}
