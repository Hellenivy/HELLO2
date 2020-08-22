package com.example.hello

class CourseActivity {
}
package ke.co.hello

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_courses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        var courseList = listOf<Course>(
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
        )
        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        rvCourses.adapter = CoursesAdapter(courseList)

        fetchCourses()
    }

    fun fetchCourses() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val accessToken = sharedPreferences.getString("ACCESS_TOKEN_KEY", "")

        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val coursesCall = apiClient.getCourses("Bearer " + accessToken)
        coursesCall.enqueue(object : Callback<CoursesResponse> {
            override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<CoursesResponse>,
                response: Response<CoursesResponse>
            ) {
                if (response.isSuccessful) {
                    var courseList = response.body()?.courses as List<Course>
                    var coursesAdapter = CoursesAdapter(courseList)
                    rvCourses.layoutManager = LinearLayoutManager(baseContext)
                    rvCourses.adapter = coursesAdapter
                } else {
                    Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}