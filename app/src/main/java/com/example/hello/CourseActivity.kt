package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.row_courses_item.*
data class Courses(val course_id: Int, val course_name: String, val course_code: Int, val instructor:String, val description:String)


class CoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        val coursesRecyclerViewAdapter = CoursesRecyclerViewAdapter(coursesList = listOf(

            Courses(22,"Hardware Electronics",110,"Semira","Good"),
            Courses(39,"Hardware Design",111,"Sashan","Excellent"),
            Courses(36,"Entreprenuership",112,"Kelsie","Good"),
            Courses(43,"UI/UX Design",113,"Maina","Excellent"),
            Courses(38,"professional development",114,"Ian","Excellent"),
            Courses(50,"python",115,"Wamani","Good"),
            Courses(30,"kotlin",116,"Jeremy","Excellent"),
            Courses(21,"UI/UX Development",117,"Liam","Good"),
            Courses(62,"Javasript",118,"Purity Maina","Excellent"),
            Courses(53,"navigating your journey",119,"Ivy","Good"),
            Courses(84,"node js",120,"Hellen","Excellent")


        ))
        rvCourses.adapter=coursesRecyclerViewAdapter
    }
}