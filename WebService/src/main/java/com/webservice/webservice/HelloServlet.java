package com.webservice.webservice;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <base href=\"<%=basePath%>\">\n" +
                "    <title>个人所得税计算器</title>\n" +
                "\t <script>\n" +
                " \twindow.\tquickRateText = '{\"3\":0,\"10\":2520,\"20\":16920,\"25\":31920,\"30\":52920,\"35\":85920,\"45\":181920}'; \n" +
                " \t//全局变量速算扣除数据\n" +
                "\twindow.quickRate = window.JSON.parse(quickRateText);\n" +
                "\twindow.rateText = '{\"3\":36000,\"10\":144000,\"20\":300000,\"25\":420000,\"30\":660000,\"35\":960000,\"45\":999999999}'; \n" +
                "\t//全局变量税率\n" +
                "\twindow.rate = window.JSON.parse(rateText);\n" +
                "     function cal(){//开始计算\n" +
                "     var arr=new Array();\n" +
                "     arr[0]= document.getElementById(\"TaxSalary\").value;\n" +
                "     arr[1]= document.getElementById(\"Insurance\").value;\n" +
                "     arr[2]= document.getElementById(\"Special\").value;\n" +
                "     arr[3]= document.getElementById(\"Month\").value;\n" +
                "\t  \tfor (var i=0;i<arr.length;i++)\n" +
                "\t  \t{\n" +
                "\t  \t\t\tcheckdata(arr[i]);//数据校验\n" +
                "\t  \t}\n" +
                "\t  \tCheckMonth(arr[3])\t;//月份校验\t\t\t\t\t\t\t\n" +
                "\t  \tvar totalPay=0;//缴税累计金额\n" +
                "\t  \tvar taxPay=0;//本月应缴税额\n" +
                "\t  \tdocument.getElementById(\"result\").innerHTML=\"\";//清空计算结果\n" +
                "\t    for(var i=1;i<=arr[3];i++){\n" +
                "\t   \t\t\tvar TaxMoney=(arr[0]-arr[1]-arr[2]-5000)*i;\t\n" +
                "\t   \t\t\tif(TaxMoney<0){ //不用交税\n" +
                "\t   \t\t\t \tdocument.getElementById(\"result\").innerHTML+=\"<p>恭喜你!!!你不用交税哦!!!!</p>\";\n" +
                "\t   \t\t\t \treturn;\n" +
                "\t   \t\t\t}\n" +
                " \t   \t\t\tvar taxRateString=getTaxRate(TaxMoney);\n" +
                " \t    \t\tvar taxRate=taxRateString/100;\n" +
                "\t        \tvar quickData= quickRate[taxRateString];\n" +
                "\t        \ttotalPay=totalPay+taxPay;\n" +
                "\t            taxPay=TaxMoney*taxRate-quickData-totalPay;\n" +
                " \t   \t\t\tdocument.getElementById(\"result\").innerHTML+=\"<p>第\"+i+\"月: 应交税额: \"+TaxMoney.toFixed(2)+\" 适应税率: \"+taxRate+\" 速算扣除数: \"+quickData+ \" 往月缴税金额: \"+totalPay.toFixed(2)+\" 本月应缴税额: \"+taxPay.toFixed(2)+\"</p>\";\n" +
                "\t   }\n" +
                "\t  \t \n" +
                "      }\n" +
                "function getTaxRate(value) {//根据需要交税的金额获取相应的税率\n" +
                "\tif(value<0) return 0;\n" +
                "\tfor (var prop in rate){  \n" +
                "\t\tif(value<=rate[prop]) \n" +
                "\t\t\treturn    prop ;\n" +
                " \t\t}\n" +
                "\t}\n" +
                "function checkdata(value){\n" +
                "\t\tCheckIsNull(value);\n" +
                "      \tif (isNaN(value)) {//用isNaN开始判断，如果成立就是非数字\n" +
                "    \t\t\talert(\"请输入正确的数值\");\n" +
                "    \t\t\treturn false;\n" +
                "   \t\t\t\t\t\t} \n" +
                "   \t\tvar len1 = value.substr(0,1);\n" +
                "    \tvar len2 = value.substr(1,1);\n" +
                "    \t//如果第一位是0，第二位不是点，就用数字把点替换掉\n" +
                "    \tif(value.length > 1 && len1==0 && len2 != '.'){\n" +
                "    \t\t\talert(\"请输入正确的数值\");\n" +
                "    \t\t\treturn false;\t\n" +
                "     \t}\n" +
                " \t\tif(value<0){\n" +
                " \t\t\t\talert(\"请输入大于或者等于0的正确的数值\");\n" +
                "    \t\t\treturn false;\n" +
                " \t\t}\n" +
                "        }\n" +
                "function checkdata_Onblur(obj){//输入框失去焦点数据校验\n" +
                "\t\tvar value=obj.value;\n" +
                " \t\tCheckIsNull(value);\n" +
                " \t\t if (isNaN(value)) {//用isNaN开始判断，如果成立就是非数字\n" +
                "    \t\t\talert(\"请输入正确的数值\");\n" +
                "    \t\t\treturn false;\n" +
                "   \t\t\t\t\t\t}\n" +
                "\t\n" +
                "    \tvar id= obj.id;\n" +
                "     \tif(id==\"Month\"){\n" +
                "     \t \tCheckMonth(value);\n" +
                "     \t}\n" +
                "     \tvar len1 = value.substr(0,1);\n" +
                "    \tvar len2 = value.substr(1,1);\n" +
                "    \t//如果第一位是0，第二位不是点，就用数字把点替换掉\n" +
                "    \tif(value.length > 1 && len1==0 && len2 != '.'){\n" +
                "    \t\t\talert(\"请输入正确的数值\");\n" +
                "    \t\t\treturn false;\t\n" +
                "     \t}\n" +
                " \t\tif(value<0){\n" +
                " \t\t\t\talert(\"请输入大于或者等于0的正确的数值\");\n" +
                "    \t\t\treturn false;\n" +
                " \t\t}\n" +
                "\t}\t\n" +
                "function CheckMonth(value){//月份校验\n" +
                "\tvar len1 = value.substr(0,1);\n" +
                "  \tif(value.length >= 1 && len1==0 ){\n" +
                "     \t  \talert(\"请输入正确的月份\");\n" +
                "     \t  \treturn false;\n" +
                "     \t  }\n" +
                "    if(value.indexOf(\".\")>0){\n" +
                "     \t  \talert(\"月份不能是小数,请重新输入正确的月份\");\n" +
                "     \t  \treturn false;\n" +
                "     \t  }\n" +
                "    \n" +
                "       \t\t\t\t\t\t}\n" +
                "function CheckIsNull( value){//非空校验\n" +
                "      if (value==null||value==\"\"){\n" +
                "  \t \t\talert( \"数据不能为空!请重新输入\");\n" +
                "  \t \t\treturn false;\n" +
                "  \t \t}\n" +
                "      }\n" +
                "</script>\n" +
                "  </head>\n" +
                "  \n" +
                "  <body>\n" +
                "  \t<div style=\"text-align:center\"><p><span style=\"display:inline-block;width:250px;text-align:right; margin:0px 200px\">个税计算器</span>  </p></div>\n" +
                "  <form action=\"form_action.asp\" method=\"get\">\n" +
                "   <div style=\"text-align:center\"> <p><span style=\"display:inline-block;width:250px;text-align:right;\">每月税前工资:</span><input   id=\"TaxSalary\" onblur=\"checkdata_Onblur(this)\" name=\"TaxSalary\" required /> </p>\n" +
                "   \t<p><span style=\"display:inline-block;width:250px;text-align:right;\">五险一金扣除金额:</span><input   id=\"Insurance\"  onblur=\"checkdata_Onblur(this)\" name=\"Insurance\" /> </p>\n" +
                "   \t<p><span style=\"display:inline-block;width:250px;text-align:right;\">专项扣除金额:</span><input   id=\"Special\"  onblur=\"checkdata_Onblur(this)\" name=\"Special\" /> </p>\n" +
                "   \t<p><span style=\"display:inline-block;width:250px;text-align:right;\">月份(1-12):</span><input   id=\"Month\" onblur=\"checkdata_Onblur(this)\" name=\"Month\" /> </p>\n" +
                "   \t<p><button style=\"margin:100px\"type=\"button\" onclick=\"cal()\">开始计算</button> </p> \n" +
                "   </div>\n" +
                "   \t<div  style=\"text-align:center\" id=\"result\"></div>\n" +
                " </form>\n" +
                "   </body>\n" +
                "</html>\n" +
                "\n");
    }

    public void destroy() {
    }
}