package com.example.expandablelistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.expandablelistview.databinding.ListGroupBinding
import com.example.expandablelistview.databinding.ListItemBinding

class CustomExpandableListAdapter internal constructor(
    private val context: Context,
    private val titleList: List<String>,
    private val dataList : HashMap<String, List<String>>
) : BaseExpandableListAdapter(){
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private lateinit var groupBinding: ListGroupBinding
    private lateinit var itemBinding: ListItemBinding
    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.dataList[this.titleList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.dataList[this.titleList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        view : View?,
        parent: ViewGroup?
    ): View {
        var convertView = view
        val holder : GroupViewHolder
        if(convertView == null){
            groupBinding = ListGroupBinding.inflate(inflater)
            convertView = groupBinding.root
            holder = GroupViewHolder()
            holder.label = groupBinding.listTitle
            convertView.tag = holder
        }
        else{
            holder = convertView.tag as GroupViewHolder
        }
        val listTitle = getGroup(groupPosition) as String
        holder.label!!.text = listTitle
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view : View?,
        parent: ViewGroup?
    ): View {
        var convertView = view
        val holder : ItemViewHolder
        if(convertView == null){
            itemBinding = ListItemBinding.inflate(inflater)
            convertView = itemBinding.root
            holder = ItemViewHolder()
            holder.label = itemBinding.expandableListItem
            convertView.tag = holder
        }
        else{
            holder = convertView.tag as ItemViewHolder
        }
        val expandedListText = getChild(groupPosition, childPosition) as String
        holder.label!!.text = expandedListText
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    inner class ItemViewHolder{
        internal var label : TextView? = null
    }
    inner class GroupViewHolder{
        internal var label : TextView? = null
    }
}