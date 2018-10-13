package info.kanta.ksouplearning

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(this)
        this.example4()
    }
    fun example1()
    {
        val html="<html><head><title>kantapp.com</title></head><body>Body content</body></html>"
        val doc=Jsoup.parse(html)
        val title=doc.title()
        val bodyContent=doc.body().text()
        ToastLog.show(this,title)
        ToastLog.show(this,bodyContent)
    }

    fun example2()
    {
        val html="<!DOCTYPE html>\n" +
                "        <html>\n" +
                "            <head>\n" +
                "                <title>My title</title>\n" +
                "                <meta charset=\"UTF-8\">\n" +
                "            </head>\n" +
                "            <body>\n" +
                "                <div id=\"mydiv\">Contents of a div element</div>\n" +
                "            </body>\n" +
                "        </html>"
        val doc=Jsoup.parse(html,"utf-8")
        val divTag=doc.getElementById("mydiv")
        ToastLog.show(this,divTag.text())

    }
    fun example3()
    {

        AndroidNetworking.get("https://www.drikpanchang.com/festivals/hindu-festivals.html")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener{
                    override fun onResponse(response: String?) {
                        val doc=Jsoup.parse(response)
                        ToastLog.show(this@MainActivity,doc.title())
                    }

                    override fun onError(anError: ANError?) {
                        ToastLog.show(this@MainActivity,anError!!.message)
                    }

                })
    }

    fun example4()
    {
        AndroidNetworking.get("http://www.jsoup.org")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener{
                    override fun onResponse(response: String?) {
                        val doc=Jsoup.parse(response)
                        val discription=doc.select("meta[name=description]").first().attr("content")
                        val keywords=doc.select("meta[name=keywords]").first().attr("content")
                        ToastLog.show(this@MainActivity,discription)
                        ToastLog.show(this@MainActivity,keywords)
                    }

                    override fun onError(anError: ANError?) {
                        ToastLog.show(this@MainActivity,anError!!.message)
                    }

                })
    }
}
