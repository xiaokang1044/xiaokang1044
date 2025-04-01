package customer.test_xb;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;

public class SAPRFCExample {
  public static void main(String[] args) {
    try {
      // 配置 SAP 连接参数
      JCoDestination destination = JCoDestinationManager.getDestination("mySAPSystem");
      JCoRepository repository = destination.getRepository();

      // 获取 RFC 函数
      JCoFunction function = repository.getFunction("Z_MY_RFC_FUNCTION");
      if (function == null) {
        throw new RuntimeException("Z_MY_RFC_FUNCTION not found in SAP.");
      }

      // 设置输入参数
      function.getImportParameterList().setValue("PARAM_NAME", "value");

      // 调用 RFC 函数
      function.execute(destination);

      // 获取输出参数
      String result = function.getExportParameterList().getString("RESULT_PARAM");
      System.out.println("Result: " + result);

    } catch (JCoException e) {
      e.printStackTrace();
    }
  }
}