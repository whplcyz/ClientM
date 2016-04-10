package com.mot.dm.clientM;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mot.dm.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by qngv36 on 4/1/2016.
 */
public class RightListViewFragment extends Fragment {
    ListView listView = null;
    List<DeviceInfo> radioList = new ArrayList<DeviceInfo>();
    DeviceListViewAdapter deviceInfoAdapter = null;
    private View view;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onCreate(Bundle savedInstanceBundle)
    {
        super.onCreate(savedInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        view = inflater.inflate(R.layout.fragment_right_list_view, container, false);

        listView = (ListView)view.findViewById(R.id.right_list_view);
        listView.addHeaderView(inflater.inflate(R.layout.device_info_header, container, false));

        updateRadioList("0");
        deviceInfoAdapter = new DeviceListViewAdapter(view.getContext(), R.layout.device_info, radioList);
        listView.setAdapter(deviceInfoAdapter);

        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceBundle)
    {
        super.onActivityCreated(savedInstanceBundle);
    }

    public void updateRadioList(String agencyID)
    {
        Random random = new Random(1000);
        int num = random.nextInt(1000);
        radioList.clear();

        for (int i = 0; i < 5 + num % 5; i++)
        {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.Name = "A_" + agencyID + "_Radio_ID:" + i;
            deviceInfo.TEI = "0ABC110" + i;
            deviceInfo.CodeplugVer = "0524";
            deviceInfo.SoftwareVer = "0728";
            deviceInfo.JobStatus = num % 10 % 3;
            deviceInfo.Type = num % 10 % 3 ;

            radioList.add(deviceInfo);
        }

        if(null != deviceInfoAdapter)
        {
            deviceInfoAdapter.notifyDataSetChanged();
        }
    }

    private String getRadioType(int radioType) {
        switch (radioType) {
            case -1:
                return "Type";
            case 1:
                return "MTP3000";
            case 2:
                return "MTP6000";
            default:
                return "MTP850";
        }
    }

    private String getRadioStatus(int radioStatus) {
        switch (radioStatus) {
            case -1:
                return "Status";
            case 1:
                return "Ready";
            case 2:
                return "Running";
            default:
                return "Unknown";
        }
    }

    private class DeviceListViewAdapter extends ArrayAdapter {

        private LayoutInflater mInflater;
        private List<DeviceInfo> mfilelist;
        private Bitmap mIconCollapse;
        private Bitmap mIconExpand;

        public DeviceListViewAdapter(Context context, int textViewResourceId,
                                     List objects) {
            super(context, textViewResourceId, objects);
            mInflater = LayoutInflater.from(context);
            mfilelist = objects;
            mIconCollapse = BitmapFactory.decodeResource(
                    context.getResources(), R.drawable.outline_list_collapse);
            mIconExpand = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.outline_list_expand);

        }

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
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.device_info, null);
                holder = new ViewHolder();
                holder.radioName = (TextView) convertView.findViewById(R.id.radio_name);
                holder.radioType = (TextView) convertView.findViewById(R.id.radio_type);
                holder.radioJobStatus = (TextView) convertView.findViewById(R.id.radio_job_status);
                holder.radioSoftver = (TextView) convertView.findViewById(R.id.radio_soft_ver);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            DeviceInfo deviceInfo = (DeviceInfo) mfilelist.get(position);
            holder.radioName.setText(String.format("%-18s", deviceInfo.Name));
            holder.radioType.setText(String.format("%-10s", getRadioType(deviceInfo.Type)));
            holder.radioJobStatus.setText(String.format("%-10s", getRadioStatus(deviceInfo.JobStatus)));
            holder.radioSoftver.setText(String.format("%-10s",deviceInfo.SoftwareVer));

            return convertView;
        }
    }

    private class ViewHolder
    {
        TextView radioName;
        TextView radioType;
        TextView radioSoftver;
        TextView radioJobStatus;
    }
}
