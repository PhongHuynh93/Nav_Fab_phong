package dhbk.android.nav_fab_phong;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DrawerFragment.GetNavItemsCallback{

    private Toolbar toolbar;
    private DrawerFragment drawerFragment;
    private View frameLayout;
    private View drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // area contains all
        drawerLayout = findViewById(R.id.drawer_layout);

        // area that contains appbar + content + FAB
        frameLayout = findViewById(R.id.container);

        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // FAB button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(frameLayout, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 2/2/16  when click action then do what ?
                            }
                        }).show();
            }
        });

        // navigation drawer
        drawerFragment = (DrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        // listen for oepn and close event
        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<NavDrawerItem> getNavItems() {
        List<NavDrawerItem> items = new ArrayList<>();
        items.add(new NavDrawerItem(R.drawable.ic_home, "Home"));
        items.add(new NavDrawerItem(R.drawable.ic_home, "Tab 1"));
        items.add(new NavDrawerItem(R.drawable.ic_home, "Tab 2"));
        items.add(new NavDrawerItem(R.drawable.ic_home, "Tab 3"));
        return items;
    }

    @Override
    public RecyclerViewItemClickListener getItemClickListener() {
        return new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(frameLayout, "Nav Item " + position, Snackbar.LENGTH_SHORT).show();
                drawerFragment.closeNavDrawer();
            }
        };
    }
}
