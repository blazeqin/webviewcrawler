package com.lion.webviewcrawlerdemo.utils

import android.os.Environment
import android.util.Log
import runOnIOThread
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

/**
 * a log record util, create logs into local
 */
object LocLog {
    private const val DEFAULT_FILE = "default_name"
    private const val FILE_NUM = 10

    private val logDir: String = Environment.getExternalStorageDirectory().path + "/Log/"
    private val packageName = DEFAULT_FILE
    private val projectLogDir = logDir + packageName + "/"
    private var fileName: String? = null
    //a queue for storing file names
    private var fileQueue: LinkedBlockingQueue<String>
    private val mCurrentLogFile: File

    init {//create directory, create log file name or file size over 10M,clear overflow files, create new file
        fileQueue = LinkedBlockingQueue(FILE_NUM)
        val dir = File(projectLogDir)
        if (dir.exists().not()) {
            dir.mkdirs()
        }
        if (fileName == null || getFileSize(projectLogDir + fileName) > 1024 * 10240) {
            fileName = getLogFileName()
            isFileRollback(fileName)
        }
        mCurrentLogFile = File(projectLogDir + fileName)
        val parentFile: File? = mCurrentLogFile.parentFile
        parentFile?.apply {
            if (exists().not()) mkdirs()
        }
        clearOverFlowFiles(parentFile)
        try {
            mCurrentLogFile.createNewFile()
        } catch (exp: Exception) {
            exp.printStackTrace()
        }
    }

    fun I(tag: String, msg: String) = LOG_I(tag, msg)

    fun action(msg: String) = LOG_I("Action", msg)

    fun E(tag:String, msg: String) = LOG_E(tag, msg)

    /**
     *clear the files number over 9
     */
    private fun clearOverFlowFiles(dir: File?) {
        val lst = dir?.run {
            listFiles(object : FileFilter {
                override fun accept(file: File?): Boolean {
                    return file?.run {
                        if (name.startsWith("MC.") && name.endsWith("txt"))
                            return true
                        false
                    } ?: false
                }
            })
        }
        if (lst == null) return
        Arrays.sort(lst,object :Comparator<File>{
            override fun compare(p0: File?, p1: File?): Int {
                return p0!!.name.compareTo(p1!!.name)
            }
        })
        for (i in 0..lst!!.size - 10) {
            lst[i].delete()
        }
    }

    /**
     *add file name to queue
     */
    private fun isFileRollback(fileName: String?) {
        if (fileQueue.size >= FILE_NUM) {
            val delFile = File(projectLogDir + fileQueue.poll())
            delFile.delete()
        }
        fileQueue.put(fileName)
    }

    private fun getLogFileName(): String? {
        val dateFormat = SimpleDateFormat("yyyyMMddhhmmss", Locale.CHINESE)
        val currentTime = dateFormat.format(Calendar.getInstance().time)
        return "MC." + currentTime + ".txt"
    }

    /**
     *get file size, best to use file.length
     */
    private fun getFileSize(path: String): Long {
        var size: Long = 0
        try {
            val file = File(path)
            if (file.exists() && file.isFile) {
                size = file.length()
            }
        } catch (ex: Exception) {
        }
        return size
    }

    fun LOG_I(tag: String, msg: String): Int {
        writeToFile(tag+":"+msg)
        return Log.i(tag, msg)
    }

    fun LOG_E(tag: String, msg: String): Int {
        writeToFile(tag+":"+msg)
        return Log.e(tag, msg)
    }

    /**
     *write the log to local file
     */
    private fun writeToFile( content: String) {
        runOnIOThread {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ", Locale.CHINESE)
            val currentDate = dateFormat.format(Calendar.getInstance().time)
            val log = "Time: "+ currentDate+" "+ content
            var out:BufferedWriter? = null
            try {
                out = BufferedWriter(OutputStreamWriter(FileOutputStream(mCurrentLogFile, true)))
                out.write("\n")
                out.write(log)
            } catch (exp: Exception) {
                exp.printStackTrace()
            }finally {
                try {
                    out?.close()
                    out = null
                } catch (exp: Exception) {
                    exp.printStackTrace()
                }
            }
        }
    }
}