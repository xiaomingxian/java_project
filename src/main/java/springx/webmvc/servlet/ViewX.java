package springx.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewX {

    public final String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";

    private File templateFile;

    public ViewX(File templateFile) {
        this.templateFile = templateFile;
    }

    public ViewX(){}

    //渲染页面
    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
        StringBuffer stringBuffer = new StringBuffer();
        //Java除了File类之外，还提供了专门处理文件的类，即RandomAccessFile（随机访问文件）类。
        // 该类是Java语言中功能最为丰富的文件访问类，它提供了众多的文件访问方法。
        // RandomAccessFile类支持“随机访问”方式，这里“随机”是指可以跳转到文件的任意位置处读写数据。
        // 在访问一个文件的时候，不必把文件从头读到尾，而是希望像访问一个数据库一样“随心所欲”地访问一个文件的某个部分

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(templateFile, "r");//只读
            String line = null;
            while (null != (line = randomAccessFile.readLine())) {
                line = new String(line.getBytes("ISO-8859-1"), "utf8");
                //X{}自定义标签解析格式 中间不能出现}
                Pattern pattern = Pattern.compile("X\\{[^\\}]\\}", Pattern.CASE_INSENSITIVE);// Pattern.CASE_INSENSITIV逐个匹配
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String paramName = matcher.group();
                    paramName.replaceAll("X\\{|\\}", "");//把标签解析替换掉
                    if (model!=null){
                        Object paramValue = model.get(paramName);
                        if (null == paramValue) continue;
                        line = matcher.replaceFirst(paramValue.toString());
                    }

                    matcher = pattern.matcher(line);
                }

                stringBuffer.append(line);
            }

            response.setCharacterEncoding("utf8");
            response.setContentType(DEFAULT_CONTENT_TYPE);
            response.getWriter().write(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
