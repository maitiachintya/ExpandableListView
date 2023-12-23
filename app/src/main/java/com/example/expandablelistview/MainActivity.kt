package com.example.expandablelistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.Toast
import com.example.expandablelistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var adapter : ExpandableListAdapter? = null
    private var titleList : List<String>? = null
    val data : HashMap<String, List<String>>
    get() {
        val listData = HashMap<String, List<String>>()
        val profileName = ArrayList<String>()
        profileName.add("Suparna Nandi")
        profileName.add("Achintya Maiti")
        profileName.add("Nadira Parvin")
        profileName.add("Mampi Ghoroi")
        profileName.add("Manas Kumar Mondol")
        profileName.add("Deblina Jana")

        val degree = ArrayList<String>()
        degree.add("M.A")
        degree.add("B.Tech")
        degree.add("B.A")
        degree.add("B.Com")
        degree.add("B.Sc")
        degree.add("Ph.D")

        val grade = ArrayList<String>()
        grade.add("AA")
        grade.add("A+")
        grade.add("AA")
        grade.add("A")
        grade.add("B+")
        grade.add("B")

        val location = ArrayList<String>()
        location.add("Ismalichok")
        location.add("Tamluk")
        location.add("Mohisadal")
        location.add("Norghat")
        location.add("Baichard")
        location.add("Maniktola")

        listData["Name"] = profileName
        listData["Degree"] = degree
        listData["Grades"] = grade
        listData["Locations"] = location

        return listData
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupExpandableListView()
    }

    private fun setupExpandableListView() {
        val expandableListView = binding.expandableListView
        val listData = data
        titleList = ArrayList(listData.keys)
        adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, listData)
        expandableListView.setAdapter(adapter)

        expandableListView.setOnGroupExpandListener {
            Toast.makeText(applicationContext, (titleList as ArrayList<String>)[it] +"List Expanded",
                Toast.LENGTH_SHORT).show()
        }

        expandableListView.setOnGroupCollapseListener {
            Toast.makeText(applicationContext, (titleList as ArrayList<String>) [it] + "List Collapsed",
                Toast.LENGTH_SHORT).show()
        }

        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(applicationContext, "Clicked:" + (titleList as ArrayList<String>)
                    [groupPosition] !!.get(childPosition), Toast.LENGTH_SHORT).show()
            false
        }
    }
}