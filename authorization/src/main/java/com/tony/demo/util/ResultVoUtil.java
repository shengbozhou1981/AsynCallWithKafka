package com.tony.demo.util;

import com.tony.demo.vo.ResultVo;

public class ResultVoUtil {
    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("success");
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo fail(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("fail");
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo notAuthenticated(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("not login yet, please login first");
        resultVo.setData(null);
        return resultVo;
    }

    public static ResultVo nullPointer(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("null pointer exception");
        resultVo.setData(data);
        return resultVo;
    }


    public static ResultVo notExistException(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("");
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo exception(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("application exception");
        resultVo.setData(data);
        return resultVo;
    }

}