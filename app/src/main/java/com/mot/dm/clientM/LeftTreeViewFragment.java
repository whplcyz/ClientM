package com.mot.dm.clientM;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.mot.dm.R;
import com.mot.dm.clientM.AgencyElement;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link LeftTreeViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftTreeViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftTreeViewFragment extends Fragment {
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    private OnFragmentInteractionListener mListener;
    private ArrayList<AgencyElement> mAgenciesCount = new ArrayList<AgencyElement>();
    private ArrayList<AgencyElement> mAgenciesList = new ArrayList<AgencyElement>();
    private TreeViewAdapter treeViewAdapter = null;
    private ListView listView;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_left_tree_view, container, false);
        listView = (ListView)view.findViewById(R.id.left_tree_view);

        initialData();
        treeViewAdapter = new TreeViewAdapter(view.getContext(), R.layout.outline, mAgenciesCount);
        listView.setAdapter(treeViewAdapter);
        if(listView != null)
        {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (mAgenciesCount.get(position).isExpanded()) {
                        mAgenciesCount.get(position).setExpanded(false);
                        AgencyElement agencyElement =mAgenciesCount.get(position);
                        ArrayList<AgencyElement> temp=new ArrayList<AgencyElement>();

                        for (int i = position+1; i < mAgenciesCount.size(); i++) {
                            if (agencyElement.getLevel()>=mAgenciesCount.get(i).getLevel()) {
                                break;
                            }
                            temp.add(mAgenciesCount.get(i));
                        }

                        mAgenciesCount.removeAll(temp);
                        treeViewAdapter.notifyDataSetChanged();
                    } else {
                        mAgenciesCount.get(position).setExpanded(true);
                        int level = mAgenciesCount.get(position).getLevel();
                        int nextLevel = level + 1;

                        for (AgencyElement agencyElement : mAgenciesList) {
                            int j=1;
                            if (agencyElement.getParent()==mAgenciesCount.get(position).getId()) {
                                agencyElement.setLevel(nextLevel);
                                agencyElement.setExpanded(false);
                                mAgenciesCount.add(position+j, agencyElement);
                                j++;
                            }
                        }
                        treeViewAdapter.notifyDataSetChanged();
                    }

                    RightListViewFragment fragment = (RightListViewFragment)getFragmentManager().findFragmentById(R.id.fragment_right_list_view);
                    fragment.updateRadioList("" + position);
                }
            });
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void initialData() {
        AgencyElement agencyElement1 =new AgencyElement("01", "Default", false	, false, "00", 0,false);
        AgencyElement agencyElement2 =new AgencyElement("02", "Agency 1", false	, true, "00", 0,false);
        AgencyElement agencyElement3 =new AgencyElement("03", "Agency 2", false	, true, "00", 0,false);
        AgencyElement agencyElement4 =new AgencyElement("04", "Sub Folder 1", true	, false, "02", 1,false);
        AgencyElement agencyElement5 =new AgencyElement("05", "Sub Folder 2", true	, false, "02", 1,false);
        AgencyElement agencyElement6 =new AgencyElement("06", "Sub Folder 3", true	, false, "02", 1,false);
        AgencyElement agencyElement7 =new AgencyElement("07", "Sub Folder 4", true	, false, "02", 1,false);
        AgencyElement agencyElement8 =new AgencyElement("08", "Sub Folder 5", true	, false, "03", 1,false);
        AgencyElement agencyElement9 =new AgencyElement("09", "Sub Folder", true	, true, "03", 1,false);
        AgencyElement agencyElement10 =new AgencyElement("10", "Sub Folder3", true	, true, "09", 2,false);
        AgencyElement agencyElement11 =new AgencyElement("11", "Sub Folder4", true	, true, "09", 2,false);
        AgencyElement agencyElement12 =new AgencyElement("12", "Sub sub Folder 1", true	, false, "11", 3,false);
        AgencyElement agencyElement13 =new AgencyElement("13", "Sub sub Folder 2", true	, false, "11", 3,false);
        AgencyElement agencyElement14 =new AgencyElement("14", "Sub sub Folder 1", true	, false, "10", 3,false);
        AgencyElement agencyElement15 =new AgencyElement("15", "Sub sub Folder 2", true	, false, "10", 3,false);
        AgencyElement agencyElement16 =new AgencyElement("16", "Sub sub Folder 3", true	, false, "10", 3,false);
        AgencyElement agencyElement17 =new AgencyElement("17", "Sub sub Folder 4", true	, false, "10", 3,false);
        AgencyElement agencyElement18 =new AgencyElement("18", "Sub sub Folder 5", true	, false, "10", 3,false);
        AgencyElement agencyElement19 =new AgencyElement("19", "Sub sub Folder 6", true	, false, "10", 3,false);
        mAgenciesCount.add(agencyElement1);
        mAgenciesCount.add(agencyElement2);
        mAgenciesCount.add(agencyElement3);


        mAgenciesList.add(agencyElement1);
        mAgenciesList.add(agencyElement2);
        mAgenciesList.add(agencyElement4);
        mAgenciesList.add(agencyElement5);
        mAgenciesList.add(agencyElement6);
        mAgenciesList.add(agencyElement7);
        mAgenciesList.add(agencyElement3);
        mAgenciesList.add(agencyElement8);
        mAgenciesList.add(agencyElement9);
        mAgenciesList.add(agencyElement10);
        mAgenciesList.add(agencyElement11);
        mAgenciesList.add(agencyElement12);
        mAgenciesList.add(agencyElement13);
        mAgenciesList.add(agencyElement14);
        mAgenciesList.add(agencyElement15);
        mAgenciesList.add(agencyElement16);
        mAgenciesList.add(agencyElement17);
        mAgenciesList.add(agencyElement18);
        mAgenciesList.add(agencyElement19);
    }

    private class TreeViewAdapter extends ArrayAdapter {

        public TreeViewAdapter(Context context, int textViewResourceId,
                               List objects) {
            super(context, textViewResourceId, objects);
            mInflater = LayoutInflater.from(context);
            mfilelist = objects;
            mIconCollapse = BitmapFactory.decodeResource(
                    context.getResources(), R.drawable.outline_list_collapse);
            mIconExpand = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.outline_list_expand);

        }

        private LayoutInflater mInflater;
        private List<AgencyElement> mfilelist;
        private Bitmap mIconCollapse;
        private Bitmap mIconExpand;


        public int getCount() {
            return mfilelist.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
			//if (convertView == null) {
            convertView = mInflater.inflate(R.layout.outline, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
			/*} else {
				holder = (ViewHolder) convertView.getTag();
			}*/

            int level = mfilelist.get(position).getLevel();
            holder.icon.setPadding(25 * (level + 1), holder.icon
                    .getPaddingTop(), 0, holder.icon.getPaddingBottom());
            holder.text.setText(mfilelist.get(position).getOutlineTitle());
            if (mfilelist.get(position).isMhasChild()
                    && (mfilelist.get(position).isExpanded() == false)) {
                holder.icon.setImageBitmap(mIconCollapse);
            } else if (mfilelist.get(position).isMhasChild()
                    && (mfilelist.get(position).isExpanded() == true)) {
                holder.icon.setImageBitmap(mIconExpand);
            } else if (!mfilelist.get(position).isMhasChild()){
                holder.icon.setImageBitmap(mIconCollapse);
                holder.icon.setVisibility(View.INVISIBLE);
            }

            return convertView;
        }

        class ViewHolder {
            TextView text;
            ImageView icon;

        }
    }
}
