package pl.polidea.treeview.demo;

import java.util.Arrays;
import java.util.Set;

import pl.polidea.treeview.AbstractTreeViewAdapter;
import pl.polidea.treeview.TreeNodeInfo;
import pl.polidea.treeview.TreeStateManager;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ordrit.R;
import com.ordrit.activity.DashboardActivity;
import com.ordrit.model.MenuItem;

/**
 * This is a very simple adapter that provides very basic tree view with a
 * checkboxes and simple item description.
 * 
 */
public class SimpleStandardAdapter extends AbstractTreeViewAdapter<Long> {

    private final Set<Long> selected;
    private Activity dashboardActivity;
    private final OnCheckedChangeListener onCheckedChange = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final CompoundButton buttonView,
                final boolean isChecked) {
            final Long id = (Long) buttonView.getTag();
            changeSelected(isChecked, id);
        }

    };

    private void changeSelected(final boolean isChecked, final Long id) {
        if (isChecked) {
            selected.add(id);
        } else {
            selected.remove(id);
        }
    }

    public SimpleStandardAdapter(final Activity dashboardActivity, final Set<Long> selected,final TreeStateManager<Long> treeStateManager,
            final int numberOfLevels) {
        super(dashboardActivity, treeStateManager, numberOfLevels);
        this.selected = selected;
        this.dashboardActivity=dashboardActivity;
    }

	private String getDescription(final long id) {
        final Integer[] hierarchy = getManager().getHierarchyDescription(id);
      MenuItem menuItem=  DashboardActivity.menuHash.get(id);
      return menuItem.getTitle();
     //   return "Node " + id + Arrays.asList(hierarchy);
    }

    @Override
    public View getNewChildView(final TreeNodeInfo<Long> treeNodeInfo) {
        final LinearLayout viewLayout = (LinearLayout) getActivity()
                .getLayoutInflater().inflate(R.layout.tree_list_item, null);
        return updateView(viewLayout, treeNodeInfo);
    }

    @Override
    public LinearLayout updateView(final View view,
            final TreeNodeInfo<Long> treeNodeInfo) {
        final LinearLayout viewLayout = (LinearLayout) view;
        final TextView descriptionView = (TextView) viewLayout
                .findViewById(R.id.demo_list_item_description);
        /*final TextView levelView = (TextView) viewLayout
                .findViewById(R.id.demo_list_item_level);*/
        
        descriptionView.setText(getDescription(treeNodeInfo.getId()));
       // levelView.setText(Integer.toString(treeNodeInfo.getLevel()));
       /* final CheckBox box = (CheckBox) viewLayout
                .findViewById(R.id.demo_list_checkbox);
        box.setTag(treeNodeInfo.getId());
        if (treeNodeInfo.isWithChildren()) {
            box.setVisibility(View.GONE);
        } else {
            box.setVisibility(View.VISIBLE);
            box.setChecked(selected.contains(treeNodeInfo.getId()));
        }
        box.setOnCheckedChangeListener(onCheckedChange);*/
        return viewLayout;
    }

    @Override
    public void handleItemClick(final View view, final Object id) {
        final Long longId = (Long) id;
        final TreeNodeInfo<Long> info = getManager().getNodeInfo(longId);
        if (info.isWithChildren()) {
            super.handleItemClick(view, id);
        } else {
        	
        	/*  	final TextView descriptionView = (TextView) view
                    .findViewById(R.id.demo_list_item_description);
        	descriptionView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(dashboardActivity, ""+longId, 1).show();
					 ((DashboardActivity) dashboardActivity).onClick(DashboardActivity.menuHash.get(longId).getMenuData());
				}
			});*/
            /*final ViewGroup vg = (ViewGroup) view;
            final CheckBox cb = (CheckBox) vg
                    .findViewById(R.id.demo_list_checkbox);
            cb.performClick();*/
        }
    }

    @Override
    public long getItemId(final int position) {
        return getTreeId(position);
    }
}