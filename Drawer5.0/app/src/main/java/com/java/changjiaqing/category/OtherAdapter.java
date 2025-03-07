package com.java.changjiaqing.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import com.java.changjiaqing.R;

import java.util.List;

public class OtherAdapter extends BaseAdapter {

    private Context context;
    public List<String> channelList;
    private TextView item_text;
    private listdatasave getcategorydata;
    /**
     * 是否可见 在移动动画完毕之前不可见，动画完毕后可见
     */
    boolean isVisible = true;
    /**
     * 要删除的position
     */
    public int remove_position = -1;
    /**
     * 是否是用户频道
     */
    private boolean isUser = false;

    public OtherAdapter(Context context, List<String> channelList, boolean isUser) {
        this.context = context;
        this.channelList = channelList;
        this.isUser = isUser;
        getcategorydata=new listdatasave(context,"drawer");
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public String getItem(int position) {
        if (channelList != null && channelList.size() != 0) {
            return channelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_mygridview_item, null);
        item_text = (TextView) view.findViewById(R.id.text_item);
        String channel = getItem(position);
        item_text.setText(channel);
        if (isUser) {
            if ((position == 0) || (position == 1)) {
                item_text.setEnabled(false);
            }
        }
        if (!isVisible && (position == -1 + channelList.size())) {
            item_text.setText("");
            item_text.setSelected(true);
            item_text.setEnabled(true);
        }
        if (remove_position == position) {
            item_text.setText("");
        }
        return view;
    }

    /**
     * 获取频道列表
     */
    public List<String> getChannnelLst() {
        return channelList;
    }

    /**
     * 添加频道列表
     */
    public void addItem(String channel) {
        channelList.add(channel);
        notifyDataSetChanged();
        if(isUser)
            getcategorydata.setDataList("User",channelList);
        else
            getcategorydata.setDataList("Other",channelList);
    }

    /**
     * 设置删除的position
     */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
        // notifyDataSetChanged();
    }

    /**
     * 删除频道列表
     */
    public void remove() {
        channelList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
        if(isUser)
            getcategorydata.setDataList("User",channelList);
        else
            getcategorydata.setDataList("Other",channelList);
    }

    /**
     * 设置频道列表
     */
    public void setListDate(List<String> list) {
        channelList = list;
    }

    /**
     * 获取是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}