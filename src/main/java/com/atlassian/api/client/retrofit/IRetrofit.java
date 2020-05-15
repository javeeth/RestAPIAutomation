package com.atlassian.api.client.retrofit;

import com.atlassian.api.entities.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface IRetrofit {

    @GET("/api/v1/employees")
    Call<GetEmployeesRsp> getEmployeeList();

    @GET("/api/v1/employee/{id}")
    Call<EmployeeInfo> getEmployee(@Path("id") String id);

    @POST("/api/v1/create")
    Call<PostEmployeeRsp> postEmployee(@Body Employee employee);

    @PUT("/api/v1/update/{id}")
    Call<EmployeeInfo> updateEmployee(@Path("id") Integer id, @Body Employee employee);

    @DELETE("/api/v1/delete/{id}")
    Call<DeleteEmployeeRsp> deleteEmployee(@Path("id") Integer id);

    @DELETE("/api/v1/delete/{id}")
    Call<Void> deleteEmployeeReturnsNull(@Path("id") Integer id);





    // Passing Query Parameter to the Get Request

    @GET("/api/v1/employee/{id}")
    Call<GetEmployeesRsp> getEmployeeWithQueryParams(@Path("id") String id, @Query("name") String name);


    // Passing Multiple Query Parameter to the Get Request

    @GET("/api/v1/employee/{id}")
    Call<GetEmployeesRsp> getEmployeeWithMultipleQueryParams(@Path("id") String id, @Query("name") String name, @Query("salary") String salary);

    // Passing Multiple Query Parameter to the Get Request of same type

    @GET("/api/v1/employees")
    Call<List<String>> getEmployeeWithMultipleSameQueryParams(@Query("id") List<Long> id);

    // Passing Multiple Query Parameter to the Get Request as a Map

    @GET("/api/v1/employees")
    Call<List<String>> getEmployeeWithQueryMap(@QueryMap Map<String, Object> queryMap);

    // Passing Static Headers to the Get call

    @GET("/api/v1/employees")
    @Headers("Content-Type: application/json;charset=utf-8")
    Call<GetEmployeesRsp> getEmployeeListWithStaticHeader();


    // Passing Multiple Static Headers to Get call

    @GET("/api/v1/employees")
    @Headers({
            "Content-Type: application/json;charset=utf-8",
            "Cache-Control: max-age=31536000"
    })
    Call<GetEmployeesRsp> getEmployeeListWithMultipleStaticHeader();

    // Passing Dynamic Headers to Get call

    @GET("/api/v1/employees")
    Call<GetEmployeesRsp> getEmployeeListWithDynamicHeader(@Header("Authorization") String authToken);

    // Passing Dynamic Headers to Get call as a Map

    @GET("/api/v1/employees")
    Call<GetEmployeesRsp> getEmployeeListWithDynamicHeaderMap(@HeaderMap Map<String, String> headers);

    // Passing Form Data for Post Call using Field

    @FormUrlEncoded
    @POST("/api/v1/create")
    Call<GetEmployeesRsp> postFormDataUsingField(@Field("name") String name);

    // Passing Form Data for Post Call using FieldMap

    @FormUrlEncoded
    @POST("/api/v1/create")
    Call<GetEmployeesRsp> postFormDataUsingFieldMap(@FieldMap Map<String, String> headers);

}