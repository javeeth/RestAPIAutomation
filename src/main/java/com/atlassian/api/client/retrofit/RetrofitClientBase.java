package com.atlassian.api.client.retrofit;

import com.atlassian.api.client.IRestClient;
import com.atlassian.api.entities.*;
import com.google.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitClientBase implements IRestClient {

    @Inject
    RetrofitBuilder retrofitBuilder;

    public RestResponse<GetEmployeesRsp> getEmployeeList() {
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<GetEmployeesRsp> tCall = iRetrofit.getEmployeeList();
        return execute(tCall);
    }

    public RestResponse<EmployeeInfo> getEmployee(String id) {
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<EmployeeInfo> tCall = iRetrofit.getEmployee(id);
        return execute(tCall);
    }

    public RestResponse<PostEmployeeRsp> postEmployee(Employee employee){
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<PostEmployeeRsp> tCall = iRetrofit.postEmployee(employee);
        return execute(tCall);
    }

    public RestResponse<EmployeeInfo> updateEmployee(Integer employeeId, Employee employee){
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<EmployeeInfo> tCall = iRetrofit.updateEmployee(employeeId, employee);
        return execute(tCall);
    }

    public RestResponse<DeleteEmployeeRsp> deleteEmployee(Integer employeeId){
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<DeleteEmployeeRsp> tCall = iRetrofit.deleteEmployee(employeeId);
        return execute(tCall);
    }

   /* public RestResponse<GetEmployeesRsp> getEmployeeWithQueryParams(String employeeId, String name){
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<GetEmployeesRsp> tCall = iRetrofit.getEmployeeWithQueryParams(employeeId, name);
        return execute(tCall);
    }

    public RestResponse<GetEmployeesRsp> getEmployeeWithMultipleQueryParams(String employeeId, String name, String salary){
        Retrofit retrofit = retrofitBuilder.getRetrofit();
        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);
        Call<GetEmployeesRsp> tCall = iRetrofit.getEmployeeWithMultipleQueryParams(employeeId, name, null);
        return execute(tCall);
    }
*/
    private <T> RestResponse<T> execute(Call<T> tCall) {

        RestResponse<T> tRestResponse = new RestResponse<>();
        try{
            Response<T> response = tCall.execute();
            tRestResponse.setApiResponse(response.body());
            tRestResponse.setStatusCode(response.code());
        }
        catch (Exception e){
        }

        return tRestResponse;
    }

    private RestResponse<GetEmployeesRsp> enqueue(Call<GetEmployeesRsp> tCall) {
        RestResponse<GetEmployeesRsp> tRestResponse = new RestResponse<>();
        tCall.enqueue(new Callback<GetEmployeesRsp>() {
            @Override
            public void onResponse(Call<GetEmployeesRsp> call, Response<GetEmployeesRsp> response) {
                if (response.isSuccessful()) {
                    try{
                        Response<GetEmployeesRsp> restResponse = tCall.execute();
                        tRestResponse.setApiResponse(restResponse.body());
                        tRestResponse.setStatusCode(restResponse.code());
                    }
                    catch (Exception e){
                    }
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<GetEmployeesRsp> call, Throwable t) {

            }
        });

        return tRestResponse;
    }
}
