package com.mot.dm.clientM;

import java.util.ArrayList;
import java.util.List;


import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mot.dm.R;


public class TreeView extends ListActivity {
	private ArrayList<AgencyElement> mPdfOutlinesCount = new ArrayList<AgencyElement>();
	private ArrayList<AgencyElement> mPdfOutlines = new ArrayList<AgencyElement>();
	private TreeViewAdapter treeViewAdapter = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initialData();
        treeViewAdapter = new TreeViewAdapter(this, R.layout.outline,
				mPdfOutlinesCount);
		setListAdapter(treeViewAdapter);
		registerForContextMenu(getListView());
    }
    
    private void initialData() {
		AgencyElement agencyElement1 =new AgencyElement("01", "关键类", false	, false, "00", 0,false);
		AgencyElement agencyElement2 =new AgencyElement("02", "应用程序组件", false	, true, "00", 0,false);
		AgencyElement agencyElement3 =new AgencyElement("03", "Activity和任务", false	, true, "00", 0,false);
		AgencyElement agencyElement4 =new AgencyElement("04", "激活组件：intent", true	, false, "02", 1,false);
		AgencyElement agencyElement5 =new AgencyElement("05", "关闭组件", true	, false, "02", 1,false);
		AgencyElement agencyElement6 =new AgencyElement("06", "manifest文件", true	, false, "02", 1,false);
		AgencyElement agencyElement7 =new AgencyElement("07", "Intent过滤器", true	, false, "02", 1,false);
		AgencyElement agencyElement8 =new AgencyElement("08", "Affinity（吸引力）和新任务", true	, false, "03", 1,false);
		AgencyElement agencyElement9 =new AgencyElement("09", "加载模式", true	, true, "03", 1,false);
		AgencyElement agencyElement10 =new AgencyElement("10", "加载模式孩子1", true	, true, "09", 2,false);
		AgencyElement agencyElement11 =new AgencyElement("11", "加载模式孩子2", true	, true, "09", 2,false);
		AgencyElement agencyElement12 =new AgencyElement("12", "加载模式孩子2的孩子1", true	, false, "11", 3,false);
		AgencyElement agencyElement13 =new AgencyElement("13", "加载模式孩子2的孩子2", true	, false, "11", 3,false);
		AgencyElement agencyElement14 =new AgencyElement("14", "加载模式孩子1的孩子1", true	, false, "10", 3,false);
		AgencyElement agencyElement15 =new AgencyElement("15", "加载模式孩子1的孩子2", true	, false, "10", 3,false);
		AgencyElement agencyElement16 =new AgencyElement("16", "加载模式孩子1的孩子3", true	, false, "10", 3,false);
		AgencyElement agencyElement17 =new AgencyElement("17", "加载模式孩子1的孩子4", true	, false, "10", 3,false);
		AgencyElement agencyElement18 =new AgencyElement("18", "加载模式孩子1的孩子5", true	, false, "10", 3,false);
		AgencyElement agencyElement19 =new AgencyElement("19", "加载模式孩子1的孩子6", true	, false, "10", 3,false);
		mPdfOutlinesCount.add(agencyElement1);
		mPdfOutlinesCount.add(agencyElement2);
		mPdfOutlinesCount.add(agencyElement3);
	
		
		mPdfOutlines.add(agencyElement1);
		mPdfOutlines.add(agencyElement2);
		mPdfOutlines.add(agencyElement4);
		mPdfOutlines.add(agencyElement5);
		mPdfOutlines.add(agencyElement6);
		mPdfOutlines.add(agencyElement7);
		mPdfOutlines.add(agencyElement3);
		mPdfOutlines.add(agencyElement8);
		mPdfOutlines.add(agencyElement9);
		mPdfOutlines.add(agencyElement10);
		mPdfOutlines.add(agencyElement11);
		mPdfOutlines.add(agencyElement12);
		mPdfOutlines.add(agencyElement13);
		mPdfOutlines.add(agencyElement14);
		mPdfOutlines.add(agencyElement15);
		mPdfOutlines.add(agencyElement16);
		mPdfOutlines.add(agencyElement17);
		mPdfOutlines.add(agencyElement18);
		mPdfOutlines.add(agencyElement19);
		
		
		
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
			/*if (convertView == null) {*/
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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (!mPdfOutlinesCount.get(position).isMhasChild()) {
			Toast.makeText(this, mPdfOutlinesCount.get(position).getOutlineTitle(), 2000);
			/*int pageNumber;
			Intent i = getIntent();
			AgencyElement element = mPdfOutlinesCount
					.get(position);
			pageNumber = element.getOutlineElement().pageNumber;
			if (pageNumber <= 0) {
				String name = element.getOutlineElement().destName;
				pageNumber = idocviewer.getPageNumberForNameForOutline(name);
				element.getOutlineElement().pageNumber = pageNumber;
				element.getOutlineElement().destName = null;
			}
			i.putExtra("PageNumber", pageNumber);
			setResult(RESULT_OK, i);
			finish();*/

			return;
		}
		

		if (mPdfOutlinesCount.get(position).isExpanded()) {
			mPdfOutlinesCount.get(position).setExpanded(false);
			AgencyElement agencyElement =mPdfOutlinesCount.get(position);
			ArrayList<AgencyElement> temp=new ArrayList<AgencyElement>();
			
			for (int i = position+1; i < mPdfOutlinesCount.size(); i++) {
				if (agencyElement.getLevel()>=mPdfOutlinesCount.get(i).getLevel()) {
					break;
				}
				temp.add(mPdfOutlinesCount.get(i));
			}
			
			mPdfOutlinesCount.removeAll(temp);
			
			treeViewAdapter.notifyDataSetChanged();
			/*fileExploreAdapter = new TreeViewAdapter(this, R.layout.outline,
					mPdfOutlinesCount);*/

			//setListAdapter(fileExploreAdapter);
			
		} else {
			mPdfOutlinesCount.get(position).setExpanded(true);
			int level = mPdfOutlinesCount.get(position).getLevel();
			int nextLevel = level + 1;
			

			for (AgencyElement agencyElement : mPdfOutlines) {
				int j=1;
				if (agencyElement.getParent()==mPdfOutlinesCount.get(position).getId()) {
					agencyElement.setLevel(nextLevel);
					agencyElement.setExpanded(false);
					mPdfOutlinesCount.add(position+j, agencyElement);
					j++;
				}			
			}
			treeViewAdapter.notifyDataSetChanged();
			/*fileExploreAdapter = new TreeViewAdapter(this, R.layout.outline,
					mPdfOutlinesCount);*/

			//setListAdapter(fileExploreAdapter);
		}
	}

}