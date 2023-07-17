package com.example.profileinfo.utils

import android.net.ParseException
import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

class DateUtils : android.text.format.DateUtils() {
    companion object {
        var SQL_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"
        var SQL_DATE_FORMAT_PATTERN = "yyyy-MM-dd"
        var SQL_TIME_FORMAT_PATTERN = "HH:mm:ss"
        var DIS_DATE_FORMAT_PATTERN = "dd MM, yyyy"
        var DIS_DATE_FORMAT_PATTERN1 = "dd MMM, yyyy"
        var DIS_TIME_FORMAT_PATTERN = "HH:mm"
        var DIS_TIME_FORMAT_PATTERN_12 = "hh:mm a"
        var DIS_DATE_WEEK_FORMAT_PATTERN = "dd MMM yyyy, EEE"
        var DIS_DATE_TIME_FORMAT_PATTERN = "dd MMM yyyy, hh:mm a"

        val dateTime: String
            get() {
                val dateFormat = SimpleDateFormat(
                    SQL_DATE_TIME_FORMAT_PATTERN, Locale.getDefault()
                )
                val date = Date()
                return dateFormat.format(date)
            }

        fun getFormattedDate(date: Date, format: String): String? {
            var dateString: String? = null
            try {
                val dateFormat = SimpleDateFormat(format, Locale.getDefault())
                dateFormat.timeZone = TimeZone.getDefault()
                dateString = dateFormat.format(date)
                // Log.v( TAG, "#parseDate dateDT: " + dateDT );
            } catch (e: ParseException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return dateString
        }

        fun getFormattedDate(date: Date, format: String, locale: Locale): String? {
            var dateString: String? = null
            try {
                dateString = SimpleDateFormat(format, locale).format(date)
                // Log.v( TAG, "#parseDate dateDT: " + dateDT );
            } catch (e: ParseException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return dateString
        }

        fun parseDate(date: String): Date? {

            if (TextUtils.isEmpty(date)) {
                return null
            }

            val sbDate = StringBuffer()
            sbDate.append(date)
            var newDate: String? = null
            var dateDT: Date? = null

            try {
                newDate = sbDate.substring(0, 19).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val rDate = newDate!!.replace("T", " ")
            val nDate = rDate.replace("-".toRegex(), "/")

            try {
                dateDT = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).parse(nDate)
                // Log.v( TAG, "#parseDate dateDT: " + dateDT );
            } catch (e: ParseException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return dateDT
        }

        fun parseDate(date: String, format: String): Date? {
            if (TextUtils.isEmpty(date)) {
                return null
            }

            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            try {
                return dateFormat.parse(date)
            } catch (e: java.text.ParseException) {
                e.printStackTrace()
            }

            return null
        }

        fun ParseTime(time: String, format: String): Date? {

            if (TextUtils.isEmpty(time)) {
                return null
            }
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            try {
                val date1: Date
                date1 = sdf.parse(time)
                return date1
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }
    }
}
