package python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class MyPython {

    public static void main(String[] args) {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
//        执行python语句
        pythonInterpreter.exec("print('jjaja')");


//       执行python脚本
        pythonInterpreter.execfile("D:\\develop\\ideaworkspeace\\learn\\src\\main\\resources\\python.py");


//    执行python函数
        PyFunction hello = pythonInterpreter.get("hello", PyFunction.class);
        PyObject pyObject = hello.__call__(new PyInteger(1), new PyInteger(2));
        System.out.println("python方法的返回值:" + pyObject);
    }
}
