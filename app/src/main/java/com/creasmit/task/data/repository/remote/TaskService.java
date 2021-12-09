package com.creasmit.task.data.repository.remote;


import com.creasmit.task.UrlConfig;
import com.creasmit.task.data.model.Task;
import com.creasmit.task.utils.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskService {

    @GET(UrlConfig.TASK)
    Observable<List<Task>> getList();

    @POST(UrlConfig.TASK)
    Observable<HttpResponse<Task>> add(@Body Task task);

    @DELETE(UrlConfig.TASK + "/{id}")
    Observable<HttpResponse<Task>> delete(@Path("id") long id);

}
