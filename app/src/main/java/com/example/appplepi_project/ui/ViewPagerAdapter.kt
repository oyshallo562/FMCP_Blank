package com.example.appplepi_project.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.appplepi_project.R
import com.example.appplepi_project.model.data.WeatherRes
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ViewPagerAdapter(private val list: ArrayList<WeatherRes>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_weather, container, false)

        //findViewById 로 변수에 저장
        val humidity = view.findViewById<TextView>(R.id.tv_main_humidityvalue) // 습도

        //TODO : 미세먼지, 강수확률, 기온, 뉴스 제목, 뉴스 내용을 표시하는 TextView의 id를 XML에서 찾아 변수에 저장
        /*
        val PM10 = view.findViewById // 미세먼지
        val rain = view.findViewById // 강수확률
        val temp = view.findViewById // 기온
        val newsTitle = view.findViewById // 뉴스 제목
        val newsContent = view.findViewById // 뉴스 내용
        */

        val PM2_5 = view.findViewById<TextView>(R.id.tv_main_PM2_5value) // 자외선
        val dateText = view.findViewById<TextView>(R.id.tv_main_weather) // 오늘/내일/어제 날씨
        val dateNum = view.findViewById<TextView>(R.id.tv_main_date) // 날짜

        val message = view.findViewById<ImageView>(R.id.message)//날씨 상태 메세지
        val weatherWord = view.findViewById<ImageView>(R.id.weather_word)//날씨 상태 문구

        //TODO : 날씨 상태 이미지를 표시하는 ImageView의 id를 XML에서 찾아 변수에 저장
        /*
        val weatherImage = view.findViewById//날씨 상태 이미지
        */





        val help = list[position].rain.toDouble()
        if(help>=60) {
            message.setImageResource(R.drawable.message_bad)
            weatherImage.setImageResource(R.drawable.weather_image_bad)
            weatherWord.setImageResource(R.drawable.weather_word_bad)
        }
        else
        {
            message.setImageResource(R.drawable.message_good)
            weatherImage.setImageResource(R.drawable.weather_image_good)
            weatherWord.setImageResource(R.drawable.weather_word_good)
        }

        humidity.text = list[position].humidity + "%"
        PM10.text = list[position].PM10 + "㎍/m³"
        PM2_5.text = list[position].PM2_5 + "㎍/m³"
        rain.text = list[position].rain + "%"
        temp.text = list[position].temp + "도"
        newsTitle.text = list[position].newsTitle
        //.을 기준으로 뉴스 내용을 나눠서 0번째 인덱스
        newsContent.text = list[position].newsContent.split(".")[0]
        dateNum.text = DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(list[position].dt.toLong())).substring(0 until 10)

        if(list[position].d == -1)
        {
            //TODO : datetext의 내용을 "어제 날씨로 설정"
        }
        else if(list[position].d == 0)
        {
            //TODO : datetext의 내용을 "오늘 날씨로 설정"
        }
        else
        {
            //TODO : datetext의 내용을 "내일 날씨로 설정"
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = list.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

}