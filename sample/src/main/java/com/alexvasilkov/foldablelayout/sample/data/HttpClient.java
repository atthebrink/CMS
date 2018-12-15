package com.alexvasilkov.foldablelayout.sample.data;

import net.sf.json.JSONObject;
import okhttp3.*;
import java.io.IOException;

public class HttpClient {

    static User user;

    public interface OnDataReceived {
        void callback(Object data);
    }


    public static void main (String[] args){



        HttpClient.getUser(1544704862401l,(data)->{
            if(data==null)
                return;
           User user = (User) data;
            System.out.println(user);

            System.out.println(user.getTags());
        });


        User u = new User();
        u.setUser_name("test");
        u.setMobile_phone("74158654");
        HttpClient.addUser(u,(data)->{
            if(data==null)
                return;
            User user = (User) data;
            System.out.println(user);

            System.out.println(user.getTags());
        });

    }

    public static void getUser(long id, OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/User/" + String.valueOf(id);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get user "+String.valueOf(id));
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                User user = (User) JSONObject.toBean(json,User.class);
                onDataReceived.callback(user);
            }
        });
    }

    public static void addUser(User user,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/User/";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .post(RequestBody.create(mediaType,user.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when add user.");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                User user = (User) JSONObject.toBean(json,User.class);
                onDataReceived.callback(user);
            }
        });
    }

    public static void updateUser(User user,OnDataReceived onDataReceived){
        if(user==null || user.getId()==0){
            onDataReceived.callback(null);
            return;
        }

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/User/"+String.valueOf(user.getId());
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .put(RequestBody.create(mediaType,user.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when update user.");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                User user = (User) JSONObject.toBean(json,User.class);
                onDataReceived.callback(user);
            }
        });
    }

    public static void getTag(long id,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/Tag/" + String.valueOf(id);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get tag "+String.valueOf(id));
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                Tag tag = (Tag) JSONObject.toBean(json,Tag.class);
                onDataReceived.callback(tag);
            }
        });
    }

    public static void addTag(Tag tag,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/Tag/";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .post(RequestBody.create(mediaType,tag.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when add tag. ");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                Tag tag = (Tag) JSONObject.toBean(json,Tag.class);
                onDataReceived.callback(tag);
            }
        });
    }

    public static void updateTag(Tag tag,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/Tag/"+ String.valueOf(tag.getId());;
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .put(RequestBody.create(mediaType,tag.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when add tag. ");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                Tag tag = (Tag) JSONObject.toBean(json,Tag.class);
                onDataReceived.callback(tag);
            }
        });
    }

    public static void getCard(long id,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/Card/" + String.valueOf(id);
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get card "+String.valueOf(id));
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                Card card = (Card) JSONObject.toBean(json,Card.class);
                onDataReceived.callback(card);
            }
        });
    }

    public static void addCard(Card card,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/Card/";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .post(RequestBody.create(mediaType,card.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when add card. ");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                Card card = (Card) JSONObject.toBean(json,Card.class);
                onDataReceived.callback(card);
            }
        });
    }

    public static void updateCard(Card card,OnDataReceived onDataReceived){

        String url = "http://119.23.241.119:8080/Entity/U61e825a37daf4/CMS/Card/"+String.valueOf(card.getId());
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .put(RequestBody.create(mediaType,card.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when update card. ");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                JSONObject json = JSONObject.fromObject(res);
                Card card = (Card) JSONObject.toBean(json,Card.class);
                onDataReceived.callback(card);
            }
        });
    }

}
